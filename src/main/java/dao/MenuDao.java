package dao;

import model.MenuEntity;

/**
*
*/
public interface MenuDao {

  public String select(String name, String password) throws RuntimeException;
  public int insert(MenuEntity entity) throws RuntimeException;

}
