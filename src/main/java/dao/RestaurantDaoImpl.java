package dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import mappers.RestaurantMapper;
import model.RestaurantEntity;

/**
* RestaurantDao
* 店舗テーブル操作.
*/
public class RestaurantDaoImpl implements RestaurantDao {

  @Override
  public List<RestaurantEntity> selectAll(String userName) throws RuntimeException {

    try {
      String resource = "mybatis-config.xml";
      InputStream inputStream = Resources.getResourceAsStream(resource);
      SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
      SqlSession session = sqlSessionFactory.openSession();
      RestaurantMapper mapper = session.getMapper(RestaurantMapper.class);
      List<RestaurantEntity> list = mapper.selectAll(userName);
      return list;

    } catch (IOException e) {
      System.out.println(e.getStackTrace());
      throw new RuntimeException();
    }

  }

  @Override
  public List<RestaurantEntity> select(RestaurantEntity entity, String userName) throws RuntimeException {

    try {
      String resource = "mybatis-config.xml";
      InputStream inputStream = Resources.getResourceAsStream(resource);
      SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
      SqlSession session = sqlSessionFactory.openSession();
      RestaurantMapper mapper = session.getMapper(RestaurantMapper.class);
      List<RestaurantEntity> list = mapper.select(entity);
      return list;

    } catch (IOException e) {
      System.out.println(e.getStackTrace());
      throw new RuntimeException();
    }

  }

  @Override
  public int insert(RestaurantEntity entity) throws RuntimeException {

    try {
      String resource = "mybatis-config.xml";
      InputStream inputStream = Resources.getResourceAsStream(resource);
      SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
      SqlSession session = sqlSessionFactory.openSession();
      RestaurantMapper mapper = session.getMapper(RestaurantMapper.class);
      int result = mapper.insertRestaurant(entity);
      session.commit(); // ここでコミット
      return result;
    } catch (IOException e) {
      System.out.println(e.getStackTrace());
      throw new RuntimeException();
    }
  }

}
