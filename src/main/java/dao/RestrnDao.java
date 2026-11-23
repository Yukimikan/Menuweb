package dao;

import java.util.List;

import model.RestaurantEntity;

/**
* RestaurantDao
* 店舗テーブル操作.
*/
public interface RestrnDao {

  public List<RestaurantEntity> selectAll(String userName) throws RuntimeException;

  public List<RestaurantEntity> select(RestaurantEntity entity, String userName) throws RuntimeException;

  public int insert(RestaurantEntity entity) throws RuntimeException;

}
