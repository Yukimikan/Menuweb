package service;

import java.io.IOException;
import java.util.List;

import dao.MenuDao;
import dao.MenuDaoImpl;
import dao.RestaurantDao;
import dao.RestaurantDaoImpl;
import form.MenuConfirmForm;
import form.MenuRegistForm;
import model.GlobalConst;
import model.MenuEntity2;
import model.RestaurantEntity;

/**
 * ファイル書込用サービス.
 * */
public class MenuRegistServiceImpl implements MenuRegistService {

  private RestaurantDao restaurantDao;
  private MenuDao menuDao;

  public MenuRegistServiceImpl() {
    restaurantDao = new RestaurantDaoImpl();
    menuDao = new MenuDaoImpl();
  }

  /**
   * execute.
   */
  public MenuRegistServiceResult execute(MenuRegistForm form, String userName) throws IOException {

    // 初期化
    MenuRegistServiceResult result = new MenuRegistServiceResult();
    RestaurantEntity searchEntity = new RestaurantEntity(
          0,
          form.getRestaurantName(),
          "",
          userName
          );
    MenuEntity2 menuEntity = new MenuEntity2(
          GlobalConst.NUM_CSV新規登録, // 新規登録
          0,
          form.getRestaurantName(),
          form.getMenuName(),
          form.getCategory(),
          form.getSingleMenuFlg(),
          Integer.parseInt(form.getEatInPrice()),
          Integer.parseInt(form.getTakeoutPrice()),
          0,
          0,
          userName);

    try {

      // 1.idを取得して設定
      List<RestaurantEntity> resultList =
        restaurantDao.select(searchEntity, userName);
      if (resultList.size() > 0) {
        menuEntity.setRestaurantId(resultList.get(0).getRestaurantId());
      } else {
        menuEntity.setRestaurantId(9999);
      }
      // 2. 新規登録
      int flg = menuDao.insert(menuEntity);
      result.setSuccess(true);
    } catch (Exception e) {
      // 登録失敗
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
    RestaurantEntity searchEntity = new RestaurantEntity(
            0,
            form.getRestaurantName(),
            "",
            userName
            );
    MenuEntity2 menuEntity = new MenuEntity2(
          GlobalConst.NUM_CSV新規登録, // 新規登録
          0,
          form.getRestaurantName(),
          form.getMenuName(),
          form.getCategory(),
          form.getSingleMenuFlg(),
          Integer.parseInt(form.getEatInPrice()),
          Integer.parseInt(form.getTakeoutPrice()),
          0,
          0,
          userName);

    try {
      // 1.idを取得して設定
      List<RestaurantEntity> resultList =
        restaurantDao.select(searchEntity, userName);
      if (resultList.size() > 0) {
        menuEntity.setRestaurantId(resultList.get(0).getRestaurantId());
      } else {
        menuEntity.setRestaurantId(9999);
      }
      // 2. 新規登録
      int flg = menuDao.insert(menuEntity);
      result.setSuccess(true);
    } catch (Exception e) {
      // 登録失敗
      result.setMessage("登録エラー");
      throw new IOException(e.getMessage(), e);
    }
    return result;
  }

}
