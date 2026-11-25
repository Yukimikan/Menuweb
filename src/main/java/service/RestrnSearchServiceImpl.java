package service;

import java.util.Collections;
import java.util.List;

import dao.RestrnDao;
import dao.RestrnDaoImpl;
import form.RestrnSearchForm;
import model.GlobalConst;
import model.RestaurantEntity;

/**
 * ファイル読込用サービス.
 * */
public class RestrnSearchServiceImpl implements RestrnSearchService {

  private RestrnDao restaurantDao;

  public RestrnSearchServiceImpl() {
    restaurantDao = new RestrnDaoImpl();
  }

  /**
   * execute.
   */
  public RestrnSearchServiceResult initSearch(String userName) {

    // 初期化
    RestrnSearchServiceResult result = new RestrnSearchServiceResult();

    // 1. 全件検索
    try {
      List<RestaurantEntity> restaurantList = restaurantDao.selectAll(userName);
      if (restaurantList.size() == 0) {
        // 結果0件
        result.setSuccess(true);
        result.setMessage("0件");
        result.setRestaurantList(Collections.emptyList());
        System.out.print(result.getMessage());
      } else {
        result.setSuccess(true);
        result.setRestaurantList(restaurantList);
      }
      // result
      if (result.isSuccess()) {
        return result;
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return result;
  }


  /**
   * execute.
   */
  @Override
  public RestrnSearchServiceResult execute(RestrnSearchForm form) {

    RestrnSearchServiceResult result = new RestrnSearchServiceResult();

    RestaurantEntity entity = new RestaurantEntity(
            GlobalConst.NUM_CSV検索, // 検索
            form.getRestaurantName(),
            form.getType(),
            form.getUserName());

    try {

      List<RestaurantEntity> tmpList = restaurantDao.select(entity);
      List<RestaurantEntity> restaurantList = tmpList.stream()
          .filter(ret ->
                isMatch(form.getType(), ret.getType())
                && isMatch(form.getRestaurantName(), ret.getRestaurantName())
                )
              .toList();
      if (tmpList.size() == 0) {
        // 結果0件
        result.setSuccess(true);
        result.setMessage("0件");
        result.setRestaurantList(Collections.emptyList());
        System.out.print(result.getMessage());
      } else {
        result.setSuccess(true);
        result.setRestaurantList(restaurantList);
      }
      // result
      if (result.isSuccess()) {
        return result;
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    // return
    return result;
  }

  private boolean isMatch(String condition, String value) {
    return condition == null
            || condition.isEmpty()
            || condition.equals(value);
  }

}
