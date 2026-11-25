package service;

import java.util.Collections;
import java.util.List;

import common.CsvUtil;
import common.FileControl;
import form.CsvWriteForm;
import model.GlobalConst;
import model.MenuCSV;

/**
 * ファイル書込用サービス.
 */
public class CsvWriteServiceImpl implements CsvWriteService {

  /**
   * execute.
   */
  @Override
  public CsvWriteServiceResult execute(CsvWriteForm form) {

    CsvWriteServiceResult result = new CsvWriteServiceResult();
    // MenuCSV に渡す(初期化)
    String[] cols = {
      "99", // No（新規登録なので空）
      form.getType(),
      form.getRestaurantName(),
      form.getSingleMenuFlg(),
      form.getMenu(),
      form.getPrice(),
      form.getTax(),
      form.getTotal()
    };
    MenuCSV rec = MenuCSV.fromInput(cols);
    String csvName = form.getCsvName();
    // 1. 新規行だけ書き込む（追記モード）
    try {
      FileControl.append(GlobalConst.CSV_FOLDER_PATH + csvName, rec.returnJoinedString());
      //CsvUtil.append(csvName, rec);
    } catch (Exception e) {
      // CSV書き込みに失敗
      // throw new IOException(e.getMessage(), e);
      System.out.println(e.getMessage());
      return new CsvWriteServiceResult(
                false,
                "error",
                Collections.emptyList());
    }
    // 2. 書き込み後の確認用に READ（画面表示用）
    List<MenuCSV> list = Collections.emptyList();
    try {
      list = CsvUtil.read(csvName);
    } catch (Exception e) {
      // CSV読み込みに失敗
      // throw new IOException(e.getMessage(), e);
      System.out.println(e.getMessage());
      return new CsvWriteServiceResult(
              false,
              "error",
              Collections.emptyList());
    }
    return new CsvWriteServiceResult(
        true,
        "",
        list);
  }

}
