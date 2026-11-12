package service;

import java.io.IOException;

import dao.MenuDao;
import dao.MenuDaoImpl;
import form.MenuConfirmForm;
import form.MenuRegistForm;
import model.GlobalConst;
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

	// 初期化
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
  public MenuRegistServiceResult execute(MenuConfirmForm form) throws IOException{

	// 初期化
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
          form.getUserName());

    // 1. 新規行だけ書き込む（追記モード）
    try {
      int flg = menuDao.insert(entity);
      result.setSuccess(true);
//	      FileControl.append(GlobalConst.CSV_FOLDER_PATH + csvName, rec.returnJoinedString());
      //CsvUtil.append(csvName, rec);
    } catch (Exception e) {
      // CSV書き込みに失敗
      result.setMessage("登録エラー");
      throw new IOException(e.getMessage(), e);
    }
    return result;
  }

}
