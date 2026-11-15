package mappers;

import org.apache.ibatis.annotations.Param;

import model.MenuEntity;

/**
 * MenuMapper
 * Mybatis実行するためのクラス
 * メニュー情報を渡す.
 */
public interface MenuMapper {
  int insertMenu(@Param("entity") MenuEntity entity);
}
