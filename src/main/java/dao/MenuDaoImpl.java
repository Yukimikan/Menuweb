package dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import mappers.MenuMapper;
import model.MenuEntity;

/**
* MenuDao
* メニューテーブル操作.
*/
public class MenuDaoImpl implements MenuDao {

  @Override
  public List<MenuEntity> select(MenuEntity entity, String userName) throws RuntimeException {

    try {
      String resource = "mybatis-config.xml";
      InputStream inputStream = Resources.getResourceAsStream(resource);
      SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
      SqlSession session = sqlSessionFactory.openSession();
      MenuMapper mapper = session.getMapper(MenuMapper.class);
      List<MenuEntity> list = mapper.select(entity);
      return list;

    } catch (IOException e) {
      System.out.println(e.getStackTrace());
      throw new RuntimeException();
    }

  }

  @Override
  public int insert(MenuEntity entity) throws RuntimeException {

    try {
      String resource = "mybatis-config.xml";
      InputStream inputStream = Resources.getResourceAsStream(resource);
      SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
      SqlSession session = sqlSessionFactory.openSession();
      MenuMapper mapper = session.getMapper(MenuMapper.class);
      int result = mapper.insertMenu(entity);
      session.commit(); // ここでコミット
      return result;
    } catch (IOException e) {
      System.out.println(e.getStackTrace());
      throw new RuntimeException();
    }
  }
}
