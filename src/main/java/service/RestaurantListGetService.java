package service;

import java.io.IOException;

/**
 * RestaurantListGetService
 * メニュー登録操作.
 */
public interface RestaurantListGetService {

  public RestaurantListGetServiceResult execute(String userName) throws IOException;

}
