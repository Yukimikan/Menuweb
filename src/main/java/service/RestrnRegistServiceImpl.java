package service;

import dao.RestaurantDao;
import dao.RestaurantDaoImpl;
import form.RestaurantConfirmForm;
import form.RestaurantRegistForm;
import model.GlobalConst;
import model.RestaurantEntity;

/**
 * ファイル書込用サービス.
 * */
public class RestrnRegistServiceImpl implements RestrnRegistService {

  private RestaurantDao restaurantDao;

  public RestrnRegistServiceImpl() {
    restaurantDao = new RestaurantDaoImpl();
  }

  /**
   * execute.
   */
  public RestrnRegistServiceResult execute(RestaurantRegistForm form, String userName) {

    // 初期化
    RestrnRegistServiceResult result = new RestrnRegistServiceResult();
    RestaurantEntity entity = new RestaurantEntity(
          GlobalConst.NUM_CSV新規登録,
          form.getRestaurantName(),
          form.getType(),
          userName);

    // 1. 新規登録
    try {
      int flg = restaurantDao.insert(entity);
      result.setSuccess(true);
    } catch (Exception e) {
      // CSV書き込みに失敗
      result.setMessage("登録エラー");
      System.out.println(e.getMessage());
    }
    return result;
  }

  /**
   * execute.
   */
  public RestrnRegistServiceResult execute(RestaurantConfirmForm form, String userName) {

    // 初期化
    RestrnRegistServiceResult result = new RestrnRegistServiceResult();
    RestaurantEntity entity = new RestaurantEntity(
          GlobalConst.NUM_CSV新規登録,
          form.getRestaurantName(),
          form.getType(),
          userName);

    // 1. 新規登録
    try {
      int flg = restaurantDao.insert(entity);
      result.setSuccess(true);
    } catch (Exception e) {
      // CSV書き込みに失敗
      result.setMessage("登録エラー");
      System.out.println(e.getMessage());
    }
    return result;
  }

}
