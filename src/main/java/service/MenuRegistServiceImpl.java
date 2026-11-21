package service;

import java.io.IOException;

import dao.MenuDao;
import dao.MenuDaoImpl;
import form.MenuConfirmForm;
import form.MenuRegistForm;
import model.GlobalConst;
import model.MenuEntity2;

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
  public MenuRegistServiceResult execute(MenuRegistForm form, String userName) throws IOException {

    // 初期化
    MenuRegistServiceResult result = new MenuRegistServiceResult();
    MenuEntity2 entity = new MenuEntity2(
          GlobalConst.NUM_CSV新規登録, // 新規登録
          Integer.parseInt(form.getRestaurantId()),
          form.getRestaurantName(),
          form.getMenuName(),
          form.getCategory(),
          form.getSingleMenuFlg(),
          Integer.parseInt(form.getEatInPrice()),
          Integer.parseInt(form.getTakeoutPrice()),
          Integer.parseInt(form.getTax()),
          Integer.parseInt(form.getTotal()),
          userName);

    // 1. 新規行だけ書き込む（追記モード）
    try {
      int flg = menuDao.insert(entity);
      result.setSuccess(true);
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
  public MenuRegistServiceResult execute(MenuConfirmForm form, String userName) throws IOException {

    // 初期化
    MenuRegistServiceResult result = new MenuRegistServiceResult();
    MenuEntity2 entity = new MenuEntity2(
          GlobalConst.NUM_CSV新規登録, // 新規登録
          Integer.parseInt(form.getRestaurantId()),
          form.getRestaurantName(),
          form.getMenuName(),
          form.getCategory(),
          form.getSingleMenuFlg(),
          Integer.parseInt(form.getEatInPrice()),
          Integer.parseInt(form.getTakeoutPrice()),
          Integer.parseInt(form.getTax()),
          Integer.parseInt(form.getTotal()),
          userName);

    // 1. 新規行だけ書き込む（追記モード）
    try {
      int flg = menuDao.insert(entity);
      result.setSuccess(true);
    } catch (Exception e) {
      // CSV書き込みに失敗
      result.setMessage("登録エラー");
      throw new IOException(e.getMessage(), e);
    }
    return result;
  }

}
