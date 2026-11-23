package service;

import java.util.Collections;
import java.util.List;

import dao.RestaurantDao;
import dao.RestaurantDaoImpl;
import form.RestaurantSearchForm;
import model.GlobalConst;
import model.RestaurantEntity;

/**
 * ファイル読込用サービス.
 * */
public class RestaurantSearchServiceImpl implements RestaurantSearchService {

  private RestaurantDao restaurantDao;

  public RestaurantSearchServiceImpl() {
    restaurantDao = new RestaurantDaoImpl();
  }

  /**
   * execute.
   */
  public RestaurantSearchServiceResult initSearch(String userName) {

    // 初期化
    RestaurantSearchServiceResult result = new RestaurantSearchServiceResult();

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
  public RestaurantSearchServiceResult execute(RestaurantSearchForm form, String userName) {

    RestaurantSearchServiceResult result = new RestaurantSearchServiceResult();

    RestaurantEntity entity = new RestaurantEntity(
            GlobalConst.NUM_CSV検索, // 検索
            form.getRestaurantName(),
            form.getType(),
            userName);

    try {

      List<RestaurantEntity> tmpList = restaurantDao.select(entity, userName);
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
