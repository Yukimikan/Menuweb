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

  List<RestaurantEntity> select(
      @Param("type") String type,
      @Param("restaurantName") String restaurantName);

  int insertRestaurant(@Param("entity") RestaurantEntity entity);

  int delete(@Param("restaurantId") int restaurantId);

}
