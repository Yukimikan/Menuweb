package service;

import dao.RestrnDao;
import dao.RestrnDaoImpl;
import form.RestrnRegistForm;
import model.GlobalConst;
import model.RestaurantEntity;

/**
 * ファイル書込用サービス.
 * */
public class RestrnRegistServiceImpl implements RestrnRegistService {

  private RestrnDao restrnDao;

  public RestrnRegistServiceImpl() {
    restrnDao = new RestrnDaoImpl();
  }

  /**
   * execute.
   */
  public RestrnRegistServiceResult execute(RestrnRegistForm form) {

    // 初期化
    RestrnRegistServiceResult result = new RestrnRegistServiceResult();
    RestaurantEntity entity = new RestaurantEntity(
          GlobalConst.NUM_CSV新規登録,
          form.getRestaurantName(),
          form.getType(),
          form.getUserName());

    // 1. 新規登録
    try {
      int flg = restrnDao.insert(entity);
      result.setSuccess(true);
    } catch (Exception e) {
      // CSV書き込みに失敗
      result.setMessage("登録エラー");
      System.out.println(e.getMessage());
    }
    return result;
  }

}
