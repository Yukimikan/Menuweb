package dao;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import mappers.UserMapper;

/**
 * ユーザ取得.
 */
public class UserDaoImpl implements UserDao {

  /**
   * ユーザ取得.
   */
  public String selectUserByKey(String name, String password)
      throws RuntimeException {

    try {
      String resource = "mybatis-config.xml";
      InputStream inputStream = Resources.getResourceAsStream(resource);
      SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
      SqlSession session = sqlSessionFactory.openSession();
      UserMapper mapper = session.getMapper(UserMapper.class);
      String userName = mapper.selectUserByKey(name, password);
      return userName;

    } catch (IOException e) {
      System.out.println(e.getStackTrace());
      throw new RuntimeException();
    }

  }

}
