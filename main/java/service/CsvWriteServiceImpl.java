package service;

import model.GlobalConst;
import model.MenuCSV;
/**
 * ファイル書込用サービス.
 * */
public class CsvWriteServiceImpl {

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
