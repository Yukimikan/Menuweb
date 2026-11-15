package dao;

/**
 * UserDao
 * ユーザテーブル操作.
 */
public interface UserDao {

  public String selectUserByKey(String name, String password) throws RuntimeException;

}
