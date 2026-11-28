package mappers;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import model.MenuEntity;

/**
 * MenuMapper
 * Mybatis実行するためのクラス
 * メニュー情報を渡す.
 */
public interface MenuMapper {

  List<MenuEntity> select(
     @Param("restaurantName") String restaurantName,
     @Param("menuName") String menuName,
     @Param("category") String category,
     @Param("singleMenuFlg") String singleMenuFlg);

  int insertMenu(@Param("entity") MenuEntity entity);

}
