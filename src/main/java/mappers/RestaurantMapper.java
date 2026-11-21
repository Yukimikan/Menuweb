package mappers;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import model.RestaurantEntity;

/**
 * RestaurantMapper
 * Mybatis実行するためのクラス
 * メニュー情報を渡す.
 */
public interface RestaurantMapper {

  List<RestaurantEntity> select(@Param("entity") RestaurantEntity entity);

  int insertRestaurant(@Param("entity") RestaurantEntity entity);

}
