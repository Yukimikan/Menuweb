package service;

import model.GlobalConst;
import model.MenuCSV;

public class CsvWriteServiceImpl {

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

  /**
   * フォーマットチェック.
   *
   * @return boolean
   */
  public static boolean formatCheck(MenuCSV rec) {
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
