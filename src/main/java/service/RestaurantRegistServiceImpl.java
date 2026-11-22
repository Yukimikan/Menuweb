package service;

import java.io.IOException;

import dao.RestaurantDao;
import dao.RestaurantDaoImpl;
import form.RestaurantConfirmForm;
import form.RestaurantRegistForm;
import model.GlobalConst;
import model.RestaurantEntity;

/**
 * ファイル書込用サービス.
 * */
public class RestaurantRegistServiceImpl implements RestaurantRegistService {

  private RestaurantDao restaurantDao;

  public RestaurantRegistServiceImpl() {
    restaurantDao = new RestaurantDaoImpl();
  }

  /**
   * execute.
   */
  public RestaurantRegistServiceResult execute(RestaurantRegistForm form, String userName) throws IOException {

    // 初期化
    RestaurantRegistServiceResult result = new RestaurantRegistServiceResult();
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
      throw new IOException(e.getMessage(), e);
    }
    return result;
  }

  /**
   * execute.
   */
  public RestaurantRegistServiceResult execute(RestaurantConfirmForm form, String userName) throws IOException {

    // 初期化
    RestaurantRegistServiceResult result = new RestaurantRegistServiceResult();
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
      throw new IOException(e.getMessage(), e);
    }
    return result;
  }

}
