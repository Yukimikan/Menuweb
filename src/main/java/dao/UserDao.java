package dao;

/**
 *
 */
public interface UserDao{

	public String selectUserByKey(String name, String password) throws RuntimeException;

}
