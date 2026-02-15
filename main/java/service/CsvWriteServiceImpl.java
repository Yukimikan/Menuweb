package service;

import java.util.ArrayList;
import java.util.List;

import common.CsvUtil;
import model.GlobalConst;
import model.MenuCSV;
import servlet.CsvWriteServDto;

/**
 * ファイル書込用サービス.
 * */
public class CsvWriteServiceImpl implements CsvWriteService {

  List<MenuCSV> retList = new ArrayList<>();

  /**
   * execute.
   */
  public List<MenuCSV> execute(CsvWriteServDto input) {

    // 入力チェック
    if (this.formatCheck(input.getRec())) {
      try {
        CsvUtil.write(input.getRec(), GlobalConst.CsvName);
        retList = CsvUtil.read(GlobalConst.CsvName);
      } catch (Exception e) {
        e.printStackTrace();
      }
      return retList;
    }
    return null;
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
