package service;

import java.io.IOException;
import java.util.List;

import common.CsvUtil;
import form.MenuCsvRegistForm;
import model.GlobalConst;
import model.MenuCSV;

/**
 * ファイル書込用サービス.
 * */
public class CsvWriteServiceImpl implements CsvWriteService {

  /**
   * execute.
   */
  public List<MenuCSV> execute(MenuCsvRegistForm form) throws IOException{

    String csvName = form.getCsv_name();
    MenuCSV rec = form.getMenuCsv();
    // 1. 新規行だけ書き込む（追記モード）
    try {
      CsvUtil.append(csvName, rec);
    } catch (Exception e) {
      throw new IOException("CSV書き込みに失敗しました: " + e.getMessage(), e);
    }
    // 2. 書き込み後の確認用に READ（画面表示用）
    List<MenuCSV> list;
    try {
      list = CsvUtil.read(csvName);
    } catch (Exception e) {
      throw new IOException("CSV読み込みに失敗しました: " + e.getMessage(), e);
    }
    return list;
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
      rec.getPrice();
      rec.getTotal();
    } catch (NumberFormatException e) {
      System.out.println(GlobalConst.MSG_W_INDATA_NOT_NUMERIC);
      System.out.println("price：" + rec.getPrice()
          + "total:" + rec.getTotal());
      return false;
    }
    return true;
  }

}
