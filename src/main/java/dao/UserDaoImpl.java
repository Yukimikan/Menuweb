package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import model.TestEntity;

public class UserDaoImpl implements UserDao{

	/**
	 * 取得
	 * @param int p1
	 */
	public String SelectSQLGetUser(String name, String password) {

		//test
		try {
			Class.forName("org.postgresql.Driver");

			DriverManager.registerDriver(
			    new org.postgresql.Driver());
		    Class.forName("org.postgresql.Driver");
		    System.out.println("Driver Load OK");
		} catch (ClassNotFoundException e ) {
		    e.printStackTrace();
		} catch (SQLException e ) {
		    e.printStackTrace();
		}
		//test end

		try (
	        Connection con = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
		    PreparedStatement ps = con.prepareStatement(SelectSQLGetUser);
	        ResultSet result = executeQueryWithParam(ps, name, password);
	    ) {
	        // SQL結果取得(件数)
			if (result.next()) {
			    return result.getString(1);
			}
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
		//エラー
        return "";
	}

	/**
	 * 取得
	 * @param int p1
	 */
	public List<TestEntity> selectTableByKey(int id) {

	    List<TestEntity> list = new ArrayList<>();

	    try (
	        Connection con = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
	        PreparedStatement ps = con.prepareStatement(SelectTableByKey);
	        ResultSet result = executeQueryWithParam(ps, id);
	    ) {
	        while (result.next()) {
	            TestEntity ent = new TestEntity();
	            ent.setId(result.getInt(1));
	            ent.setText(result.getString(2));
	            list.add(ent);
	        }

	        return list;

	    } catch (SQLException e) {
	        e.printStackTrace();
	        return Collections.emptyList();   // 異常時は空リスト返却
	    }
	}

	private ResultSet executeQueryWithParam(PreparedStatement ps, int id) throws SQLException {
	    ps.setInt(1, id);
	    return ps.executeQuery();
	}

	private ResultSet executeQueryWithParam(PreparedStatement ps, String name, String password) throws SQLException {
	    ps.setString(1, name);
	    ps.setString(2, password);
	    return ps.executeQuery();
	}


}
