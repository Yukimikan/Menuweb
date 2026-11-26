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
public class RestrnDaoImpl implements RestrnDao {

  @Override
  public List<RestaurantEntity> select(String type, String restaurantName) throws RuntimeException {

    try {
      String resource = "mybatis-config.xml";
      InputStream inputStream = Resources.getResourceAsStream(resource);
      SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
      SqlSession session = sqlSessionFactory.openSession();
      RestaurantMapper mapper = session.getMapper(RestaurantMapper.class);
      List<RestaurantEntity> list = mapper.select(type, restaurantName);
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
  public int delete(int restaurantId) throws RuntimeException {

    try {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();
        RestaurantMapper mapper = session.getMapper(RestaurantMapper.class);
        int result = mapper.delete(restaurantId);
        session.commit(); // ここでコミット
        return result;
      } catch (IOException e) {
        System.out.println(e.getStackTrace());
        throw new RuntimeException();
      }
  }

}
