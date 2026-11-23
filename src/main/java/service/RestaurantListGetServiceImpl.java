package service;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import dao.RestaurantDao;
import dao.RestaurantDaoImpl;
import model.RestaurantEntity;

/**
 * ファイル書込用サービス.
 * */
public class RestaurantListGetServiceImpl implements RestaurantListGetService {

  private RestaurantDao restaurantDao;

  public RestaurantListGetServiceImpl() {
    restaurantDao = new RestaurantDaoImpl();
  }

  /**
   * execute.
   */
  public RestaurantListGetServiceResult execute(String userName) throws IOException {

    // 初期化
    RestaurantListGetServiceResult result = new RestaurantListGetServiceResult();

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

}
