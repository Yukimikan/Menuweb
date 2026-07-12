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
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
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
  //Copilotで修正済み。

  //絶対パスで指定
  public static final String CSV_PATH = "C:\\pleiades\\workspace\\Menuweb\\resource\\";
  public static final int MAX_COUNT = 100;
  public static final long MAX_BYTES = 1_000_000; // 1MB
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

    try {
      Path filePath = Paths.get(infilePath);
      long fileSize = Files.size(filePath);
      // 1. 読み込み前に byte 数チェック（例外発生源より前）
      if (fileSize > MAX_BYTES) {
        System.out.println(GlobalConst.MSG_W_FILE_MAXCOUNT_OVER);
        // ただし処理は続行
      }
      // 1. 全行読み込み
      Iterator<String> it = Files.readAllLines(filePath, StandardCharsets.UTF_8)
                                 .iterator();

      // 1. ヘッダー一致チェック
      String header = CsvUtil.readHeader(infilePath);
      if (!MenuCSV.CSV_HEADER.equals(header)) {
        System.out.println("警告：ヘッダーが想定と異なります → " + header);
      }

      // 2. ヘッダー読み飛ばし（CsvUtilで読みつつ、iteratorも進める）
      if (it.hasNext()) {
        it.next(); // iterator の先頭を捨てる
        // CsvUtil.readHeader(infilePath) は品質チェック用に使える
        // 今はヘッダー内容を使わないので捨てるだけ
      }
      // 3. 本体行の処理
      while (it.hasNext()) {
        String currentContent = it.next();
        String[] arrayColumnData = currentContent.split(",");
        try {
            retList.add(new MenuCSV(arrayColumnData));
          } catch (IllegalArgumentException e) {
            System.out.println("警告：不正な行 → " + currentContent);
            System.out.println("理由：" + e.getMessage());
            // 続行
          }
        }
    } catch (NoSuchFileException e) {
      System.out.println(GlobalConst.MSG_W_FILE_NOT_FOUND);
      System.out.println("infile_path：" + infilePath);
      throw e;
    } catch (Exception ex) {
      ex.printStackTrace();
      throw ex;
    } finally {
      // finally は空でOK
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

    String outfilePath = CSV_PATH + outfilename;
    boolean needHeader = false;

    // 1. ヘッダー判定（読み込み側は try-with-resources）
    File f = new File(outfilePath);
    if (!f.exists()) {
      needHeader = true;
    } else {
      String header = CsvUtil.readHeader(outfilePath);

      if (!MenuCSV.CSV_HEADER.equals(header)) {
        System.out.println("警告：ヘッダーが想定と異なります → " + header);
    	needHeader = true;
      }
    }

    // 2. 書き込みストリーム（追記モード）→ try-with-resources に変更
    try (
        FileWriter fw = new FileWriter(outfilePath, true);
        BufferedWriter buffWriter = new BufferedWriter(fw)
    ) {

      if (needHeader) {
        buffWriter.write(MenuCSV.CSV_HEADER);
        buffWriter.newLine();
      }

      buffWriter.write(rec.returnJoinedString());

    } catch (FileNotFoundException e) {
      System.out.println(GlobalConst.MSG_W_FILE_NOT_FOUND);
      System.out.println("outfile_path：" + outfilePath);
      throw e;

    } catch (Exception ex) {
      ex.printStackTrace();
      throw ex;
    }
  }

  /**
   * CsvHeader読み込み.
   *
   * @param rec
   * @return
   */
  // 先頭行（ヘッダー）を読み、BOM を除去して返す
  public static String readHeader(String filePath) throws Exception {
    try (
        FileInputStream fileInput = new FileInputStream(filePath);
        InputStreamReader inputStream = new InputStreamReader(fileInput, StandardCharsets.UTF_8);
        BufferedReader buffReader = new BufferedReader(inputStream)
    ) {
      String header = buffReader.readLine();

      if (header != null && header.startsWith("\uFEFF")) {
        header = header.substring(1);
      }
      return header;

    } catch (Exception e) {
      throw e;
    }
  }

  /**
   * 書込み処理.
   *
   * @param rec
   * @return
   */
  /*
  public static void write_old(MenuCSV rec, String outfilename)
            throws Exception {

    BufferedReader buffReader = null;
    BufferedWriter buffWriter = null;
    //String outfile_path = CSV_PATH + "\\output\\" + outfilename;
    String outfilePath = CSV_PATH + outfilename;

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
  */
}
