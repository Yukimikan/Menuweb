package dao;

import model.MenuEntity;

/**
* MenuDao
* メニューテーブル操作.
*/
public interface MenuDao {

  public String select(String name, String password) throws RuntimeException;

  public int insert(MenuEntity entity) throws RuntimeException;

}
