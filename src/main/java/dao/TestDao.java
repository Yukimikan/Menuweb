package dao;

import java.util.List;

import model.TestEntity;
import resource.pdbConnect;

/**
 *
 */
public interface TestDao extends pdbConnect {

	static final String SelectSQL = "select count(*) from test";
	static final String SelectSQLBykey = "select count(*) from test where id = ?";
	static final String SelectTableByKey = "select * from test where id = ?";
	static final String InsertSQL = "insert into test(id, text) values(?, ?)";

	public int selectCountAll() ;

	public int selectCountByKey(int id) ;

	public List<TestEntity> selectTableByKey(int id) ;

	public int InsertOneRow(int p1, String p2);

}
