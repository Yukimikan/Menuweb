package dao;

import java.util.List;

import model.RestaurantEntity;

/**
* RestaurantDao
* 店舗テーブル操作.
*/
public interface RestrnDao {

  public List<RestaurantEntity> select(String type, String restaurantName) throws RuntimeException;

  public int insert(RestaurantEntity entity) throws RuntimeException;

  public int delete(int restaurantId) throws RuntimeException;

}
