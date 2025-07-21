package service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import model.GlobalConst;
import model.MenuCSV;

/**
 * ファイル操作用サービス.
 * */
public class CsvUtilServiceImpl {

  /*
   * 参考サイト
   * https://style.potepan.com/articles/18230.html
   * */

  //絶対パスで指定
  public static final String CSV_PATH = "C:\\pleiades\\workspace\\Menuweb\\resource\\";
  public static final String CSV_NAME = "menu.csv";
  public static final int MAX_COUNT = 100;

  //コンストラクタ
  public CsvUtilServiceImpl() {
  }

  /**
   * 読取CSV.
   *
   * @param infilename
   * @return retList
   */
  public List<MenuCSV> read(String infilename)
            throws Exception {
    BufferedReader buffReader = null;
    String infilePath = CSV_PATH + infilename;
    MenuCSV inCsv = new MenuCSV();
    List<MenuCSV> retList = new ArrayList<MenuCSV>();

    // 入力チェック
    if (commonCheck(infilename) == false) {
      return null;
    }

    try {
      // sample1.csvファイルを読み込みます
      FileInputStream fileInput = new FileInputStream(infilePath); // ※1
      // バイトストリームをテキスト形式に変換
      InputStreamReader inputStream = new InputStreamReader(fileInput); // ※2
      // テキスト形式のファイルを読み込む
      buffReader = new BufferedReader(inputStream); // ※3
      String currentContent;
      int row = 0;
      while ((currentContent = buffReader.readLine()) != null) { // ※4

        // 最大件数超過
        if (row == MAX_COUNT) {
          System.out.println(GlobalConst.MSG_W_FILE_MAXCOUNT_OVER);
          break;
        }

        //初期化
        inCsv = new MenuCSV();

        if (row == 0) {
            // ※5 header
        } else {
          // カラムを分割
          String[] arrayColumnData = currentContent.split(","); // ※6
          //戻り値を追加
          inCsv.setAllColumns(arrayColumnData);
          retList.add(inCsv);
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
      try {
        buffReader.close(); //※9
      } catch (Exception ex) {
        ex.printStackTrace();
      }
    }
    return retList;
  }

  /**
   * 書込み処理.
   *
   * @param rec
   * @return retList
   */
  public List<MenuCSV> write(MenuCSV rec, String outfilename)
            throws Exception {

    BufferedReader buffReader = null;
    BufferedWriter buffWriter = null;
    //String outfile_path = CSV_PATH + "\\output\\" + outfilename;
    String outfilePath = CSV_PATH + outfilename;
    List<MenuCSV> retList = new ArrayList<MenuCSV>();

    //入力チェック
    if (commonCheck(outfilename) == false
        || formatCheck(rec) == false) {
      return null;
    }

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
    //再読み込み
    retList = read(outfilename);
    return retList;
  }

  /**
   * 共通処理.
   *
   * @return boolean
   */
  public boolean commonCheck(String filename) {
    //チェック
    if (filename == null || filename.isEmpty()) {
      System.out.println(GlobalConst.MSG_W_FILENAME_ERROR);
      System.out.println("filename:" + filename);
      return false;
    }
    return true;
  }

  /**
   * フォーマットチェック.
   *
   * @return boolean
   */
  public boolean formatCheck(MenuCSV rec) {
    //チェック
    if (rec == null) {
      System.out.println(GlobalConst.MSG_W_INDATA_ISNULL);
      return false;
    }
    //チェック
    try {
      Integer.parseInt(rec.getPrice());
      Integer.parseInt(rec.getTotal());
    } catch (NumberFormatException e) {
      System.out.println(GlobalConst.MSG_W_INDATA_NOT_NUMERIC);
      System.out.println("price：" + rec.getPrice()
          + "total:" + rec.getTotal());
      return false;
    }
    return true;
  }

}
