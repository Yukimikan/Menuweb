package mappers;

import org.apache.ibatis.annotations.Param;

public interface UserMapper {
  // @select("select count(*) from test where id = ?")
  String selectUserByKey(
	        @Param("name") String name,
	        @Param("password") String password);
}
