package common;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import model.GlobalConst;
import model.MenuCSV;

/**
 * ファイル操作用サービス.
 * */
public final class CsvUtil {

  /*
   * 参考サイト
   * https://style.potepan.com/articles/18230.html
   * */

  //絶対パスで指定
  public static final String CSV_PATH = "C:\\pleiades\\workspace\\Menuweb\\resource\\";
  public static final int MAX_COUNT = 100;
  // public static final String CSV_NAME = "menu.csv";

  /* callしない */
  //コンストラクタ
  private CsvUtil() {
  }

  /**
   * 読取CSV.
   *
   * @param infilename
   * @return retList
   * @exception FileNotFoundException
   */
  public static List<MenuCSV> read(String infilename)
            throws Exception {
    String infilePath = CSV_PATH + infilename;
    List<MenuCSV> retList = new ArrayList<MenuCSV>();
    // 入力チェック(呼び元で実行)
    /* nothing */

    try {
      // sample1.csvファイルを読み込みます
      // テキスト形式のファイルを読み込む ※3
      int row = 0;
      // Java7
      Path filePath = Paths.get(infilePath); //引数1つ
      List<String> lines = Files.readAllLines(filePath, StandardCharsets.UTF_8);

      if (lines.size() >= MAX_COUNT) {
        // 最大件数超過(byte数判定がいい)
        System.out.println(GlobalConst.MSG_W_FILE_MAXCOUNT_OVER);
      }

      // 拡張for文(lines)に書換 ※4
      for (String currentContent : lines) {
        if (row == 0) {
            // ※5 header
        } else {
          // カラムを分割
          String[] arrayColumnData = currentContent.split(","); // ※6
          //戻り値を追加
          retList.add(new MenuCSV(arrayColumnData));
        }
        row++;
      }
    } catch (FileNotFoundException e) {
      System.out.println(GlobalConst.MSG_W_FILE_NOT_FOUND);
      System.out.println("infile_path：" + infilePath);
      throw e;
    } catch (Exception ex) {
      ex.printStackTrace();
      throw ex;
    } finally {
      //close処理
      /*
      try {
        buffReader.close(); //※9
      } catch (Exception ex) {
        ex.printStackTrace();
      }
      */
    }
    return retList;
  }

  /**
   * 書込み処理.
   *
   * @param rec
   * @return
   */
  public static void write(MenuCSV rec, String outfilename)
            throws Exception {

    BufferedReader buffReader = null;
    BufferedWriter buffWriter = null;
    //String outfile_path = CSV_PATH + "\\output\\" + outfilename;
    String outfilePath = CSV_PATH + outfilename;

    //入力チェック(呼び元で実行)
    /* nothing */

    try {
      File f = new File(outfilePath);
      // 第二引数trueで、追加書き
      FileWriter fw = new FileWriter(f, true);
      buffWriter = new BufferedWriter(fw); // ※3
      String tempRec = rec.returnJoinedString();

      if (f.exists() == false) {
        //ファイル未存在
        //空ファイルなのでヘッダ書込
        buffWriter.write(MenuCSV.CSV_HEADER);
        buffWriter.newLine();
      } else {
        //ヘッダ読込
        FileInputStream fileInput = new FileInputStream(outfilePath); // ※1
        InputStreamReader inputStream = new InputStreamReader(fileInput); // ※2
        buffReader = new BufferedReader(inputStream); // ※3

        String topRec =  buffReader.readLine();
        buffReader.close();

        //読込一行がヘッダーと一致
        if (MenuCSV.CSV_HEADER.equals(topRec)) {
          //ヘッダ存在
        } else {
          //ヘッダ未存在
          //空ファイルなのでヘッダ書込
          buffWriter.write(MenuCSV.CSV_HEADER);
          buffWriter.newLine();
        }
      }
      //データ部書込
      buffWriter.write(tempRec);
    } catch (FileNotFoundException e) {
      System.out.println(GlobalConst.MSG_W_FILE_NOT_FOUND);
      System.out.println("outfile_path：" + outfilePath);
      throw e;
    } catch (Exception ex) {
      ex.printStackTrace();
      throw ex;
    } finally {
      //close処理
      try {
        buffWriter.close(); //※9
      } catch (Exception ex) {
        ex.printStackTrace();
      }
    }
  }
}
