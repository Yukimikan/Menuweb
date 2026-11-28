package service;

import java.util.List;

import dao.MenuDao;
import dao.MenuDaoImpl;
import dao.RestrnDao;
import dao.RestrnDaoImpl;
import form.MenuRegistForm;
import model.GlobalConst;
import model.MenuEntity;
import model.RestaurantEntity;

/**
 * MenuRegistService
 * メニュー登録操作.
 */
public class MenuRegistServiceImpl implements MenuRegistService {

  private RestrnDao restrnDao;
  private MenuDao menuDao;

  public MenuRegistServiceImpl() {
    restrnDao = new RestrnDaoImpl();
    menuDao = new MenuDaoImpl();
  }

  /**
   * execute.
   */
  @Override
  public MenuRegistServiceResult execute(MenuRegistForm form) {

    // 初期化
    MenuRegistServiceResult result = new MenuRegistServiceResult();
    String userName = form.getUserName();
    MenuEntity menuEntity = new MenuEntity(
          GlobalConst.NUM_CSV新規登録, // 新規登録
          GlobalConst.NUM_CSV新規登録,
          form.getRestaurantName(),
          form.getMenuName(),
          form.getCategory(),
          form.getSingleMenuFlg(),
          Integer.parseInt(form.getEatInPrice()),
          Integer.parseInt(form.getTakeoutPrice()),
          userName);

    try {

      // 1.idを取得して設定
      List<RestaurantEntity> resultList =
          restrnDao.select(null, form.getRestaurantName());
      if (resultList.size() > 0) {
        menuEntity.setRestaurantId(resultList.get(0).getRestaurantId());
      } else {
        menuEntity.setRestaurantId(9999);
      }
      // 2. 新規登録
      int count = menuDao.insert(menuEntity);
      if (count == 1) {
        result.setSuccess(true);
      } else {
        result.setSuccess(false);
        result.setMessage("登録が完了しませんでした（登録0件）");
      }
    } catch (Exception e) {
      // 登録失敗
      result.setMessage("登録エラー");
      System.out.println(e.getMessage());

    }
    return result;
  }

}
