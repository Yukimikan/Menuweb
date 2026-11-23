package mappers;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import model.MenuEntity2;

/**
 * MenuMapper
 * Mybatis実行するためのクラス
 * メニュー情報を渡す.
 */
public interface MenuMapper {

  List<MenuEntity2> select(@Param("entity") MenuEntity2 entity);

  int insertMenu(@Param("entity") MenuEntity2 entity);

  // List<MenuEntity> select(@Param("entity") MenuEntity entity);
  // int insertMenu(@Param("entity") MenuEntity entity);

}
