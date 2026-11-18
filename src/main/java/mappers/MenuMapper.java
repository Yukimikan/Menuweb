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

  List<MenuEntity> select(@Param("entity") MenuEntity entity);

  int insertMenu(@Param("entity") MenuEntity entity);

}
