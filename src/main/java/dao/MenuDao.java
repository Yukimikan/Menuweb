package dao;

import java.util.List;

import model.MenuEntity;

/**
* MenuDao
* メニューテーブル操作.
*/
public interface MenuDao {

  public List<MenuEntity> select(MenuEntity entity, String userName) throws RuntimeException;

  public int insert(MenuEntity entity) throws RuntimeException;

}
