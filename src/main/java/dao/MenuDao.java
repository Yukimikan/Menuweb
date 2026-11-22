package dao;

import java.util.List;

import model.MenuEntity;
import model.MenuEntity2;

/**
* MenuDao
* メニューテーブル操作.
*/
public interface MenuDao {

  public List<MenuEntity> select(MenuEntity entity, String userName) throws RuntimeException;

  public List<MenuEntity2> select2(MenuEntity2 entity, String userName) throws RuntimeException;

  public int insert(MenuEntity entity) throws RuntimeException;

  public int insert(MenuEntity2 entity) throws RuntimeException;

}
