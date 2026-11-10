package service;

import java.io.IOException;
import java.util.List;

import common.CsvUtil;
import form.MenuCsvConfirmForm;
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
  public CsvWriteServiceResult execute(MenuCsvRegistForm form) throws IOException{
   // MenuCSV に渡す(初期化)
    String[] cols = {
      "99", // No（新規登録なので空）
      form.getType(),
      form.getRestaurantName(),
      form.getSinglemenuFlg(),
      form.getMenu(),
      form.getPrice(),
      form.getTax(),
      form.getTotal()
    };
    MenuCSV rec = MenuCSV.fromInput(cols);
	String csvName = form.getCsvName();
    // 1. 新規行だけ書き込む（追記モード）
    try {
      CsvUtil.append(csvName, rec);
    } catch (Exception e) {
      // CSV書き込みに失敗
      throw new IOException(e.getMessage(), e);
    }
    // 2. 書き込み後の確認用に READ（画面表示用）
    List<MenuCSV> list;
    try {
      list = CsvUtil.read(csvName);
    } catch (Exception e) {
      // CSV読み込みに失敗
      throw new IOException(e.getMessage(), e);
    }
    return new CsvWriteServiceResult(
    		true
    		,""
    		,list);
  }

  /**
   * execute.
   */
  public CsvWriteServiceResult execute(MenuCsvConfirmForm form) throws IOException{
   // MenuCSV に渡す(初期化)
    String[] cols = {
      "99", // No（新規登録なので空）
      form.getType(),
      form.getRestaurantName(),
      form.getSinglemenuFlg(),
      form.getMenu(),
      form.getPrice(),
      form.getTax(),
      form.getTotal()
    };
    MenuCSV rec = MenuCSV.fromInput(cols);
	String csvName = form.getCsvName();
    // 1. 新規行だけ書き込む（追記モード）
    try {
      CsvUtil.append(csvName, rec);
    } catch (Exception e) {
      // CSV書き込みに失敗
      throw new IOException(e.getMessage(), e);
    }
    // 2. 書き込み後の確認用に READ（画面表示用）
    List<MenuCSV> list;
    try {
      list = CsvUtil.read(csvName);
    } catch (Exception e) {
      // CSV読み込みに失敗
      throw new IOException(e.getMessage(), e);
    }
    return new CsvWriteServiceResult(
    		true
    		,""
    		,list);
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
