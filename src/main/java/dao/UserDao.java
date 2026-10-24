package dao;

import resource.pdbConnect;

/**
 *
 */
public interface UserDao extends pdbConnect {

//	static final String SelectSQL = "select count(*) from USER";
//	static final String SelectSQLBykey = "select count(*) from USER where id = ?";
	static final String SelectSQLGetUser = "select name from \"USER\" where name = ? and password = ?";
	static final String SelectTableByKey = "select * from \"USER\" where name = ?";
//	static final String InsertSQL = "insert into test(id, text) values(?, ?)";

//	public int selectCountAll() ;

	public String SelectSQLGetUser(String name, String password) ;

//	public int selectCountByKey(int id) ;
//
//	public List<TestEntity> selectTableByKey(int id) ;
//
//	public int InsertOneRow(int p1, String p2);

}
