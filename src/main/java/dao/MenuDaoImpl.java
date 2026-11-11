package dao;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import mappers.MenuMapper;
import model.MenuEntity;

public class MenuDaoImpl implements MenuDao {

	@Override
	public String select(String name, String password) throws RuntimeException {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public int insert(MenuEntity entity) throws RuntimeException {

		try {
	        String resource = "mybatis-config.xml";
	        InputStream inputStream = Resources.getResourceAsStream(resource);
	        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
	        SqlSession session = sqlSessionFactory.openSession();
	        MenuMapper mapper = session.getMapper(MenuMapper.class);
	        int result = mapper.insert(entity);
	  	  return result;

	  	}  catch(IOException e) {
	  	  System.out.println(e.getStackTrace());
	        throw new RuntimeException();
	  	}
	}

}
