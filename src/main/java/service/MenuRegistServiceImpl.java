package service;

import java.io.IOException;
import java.util.List;

import common.CsvUtil;
import common.FileControl;
import dao.MenuDao;
import dao.MenuDaoImpl;
import form.MenuCsvConfirmForm;
import form.MenuRegistForm;
import model.GlobalConst;
import model.MenuCSV;
import model.MenuEntity;

/**
 * ファイル書込用サービス.
 * */
public class MenuRegistServiceImpl implements MenuRegistService {

  private MenuDao menuDao;

  public MenuRegistServiceImpl() {
    menuDao = new MenuDaoImpl();
  }

  /**
   * execute.
   */
  public MenuRegistServiceResult execute(MenuRegistForm form, String userName) throws IOException{

    MenuRegistServiceResult result = new MenuRegistServiceResult();
    MenuEntity entity = new MenuEntity(
          GlobalConst.NUM_CSV新規登録, // 新規登録
          form.getType(),
          form.getRestaurantName(),
          form.getSingleMenuFlg(),
          form.getMenu(),
          Integer.parseInt(form.getPrice()),
          Integer.parseInt(form.getTax()),
          Integer.parseInt(form.getTotal()),
          userName);

// MenuCSV に渡す(初期化)
//    String[] cols = {
//      "99", // No（新規登録なので空）
//      form.getType(),
//      form.getRestaurantName(),
//      form.getSingleMenuFlg(),
//      form.getMenu(),
//      form.getPrice(),
//      form.getTax(),
//      form.getTotal()
//    };
//    MenuCSV rec = MenuCSV.fromInput(cols);
//	String csvName = form.getCsvName();
    // 1. 新規行だけ書き込む（追記モード）
    try {
      int flg = menuDao.insert(entity);
      result.setSuccess(true);
//      FileControl.append(GlobalConst.CSV_FOLDER_PATH + csvName, rec.returnJoinedString());
      //CsvUtil.append(csvName, rec);
    } catch (Exception e) {
      // CSV書き込みに失敗
      result.setMessage("登録エラー");
      throw new IOException(e.getMessage(), e);
    }
    return result;
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
        FileControl.append(GlobalConst.CSV_FOLDER_PATH + csvName, rec.returnJoinedString());
        //CsvUtil.append(csvName, rec);
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

}
