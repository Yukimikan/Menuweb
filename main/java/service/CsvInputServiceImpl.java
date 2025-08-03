package service;

import model.GlobalConst;

/**
 * ファイル読込用サービス.
 * */
public class CsvInputServiceImpl {

  /**
   * 共通処理.
   *
   * @return boolean
   */
  public static boolean commonCheck(String filename) {
    //チェック
    if (filename == null || filename.isEmpty()) {
      System.out.println(GlobalConst.MSG_W_FILENAME_ERROR);
      System.out.println("filename:" + filename);
      return false;
    }
    return true;
  }

}
