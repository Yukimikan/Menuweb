package dao;

import java.util.List;

import model.MenuEntity;

/**
* MenuDao
* メニューテーブル操作.
*/
public interface MenuDao {

  public List<MenuEntity> select(
                                 String restaurantName,
                                 String menuName,
                                 String category,
                                 String singleMenuFlg) throws RuntimeException;

  public int insert(MenuEntity entity) throws RuntimeException;

}
