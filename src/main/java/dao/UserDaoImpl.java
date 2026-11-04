package dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import mappers.UserMapper;

public class UserDaoImpl implements UserDao{

  /**
   * 取得
   * @param int p1
   */
  public String SelectSQLGetUser(String name, String password)
  		throws RuntimeException{

    //test
    try {
      Class.forName("org.postgresql.Driver");

      DriverManager.registerDriver(
        new org.postgresql.Driver());
      Class.forName("org.postgresql.Driver");
      System.out.println("Driver Load OK");
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    }

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
      throw new RuntimeException();
    }
    //エラー
    return "";
  }

  /**
   * 取得
   * @param int p1
   */
  public String selectUserByKey(String name, String password)
  		throws RuntimeException{

    try {
      String resource = "mybatis-config.xml";
      InputStream inputStream = Resources.getResourceAsStream(resource);
      SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
      SqlSession session = sqlSessionFactory.openSession();
      UserMapper mapper = session.getMapper(UserMapper.class);
      String userName = mapper.selectUserByKey(name, password);
	  System.out.println("Username :" + userName);
	  return userName;

	}  catch(IOException e) {
	  System.out.println(e.getStackTrace());
      throw new RuntimeException();
	}

  };

  private ResultSet executeQueryWithParam(PreparedStatement ps, String name, String password) throws SQLException {
    ps.setString(1, name);
    ps.setString(2, password);
    return ps.executeQuery();
}


}
