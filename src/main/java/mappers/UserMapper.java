package mappers;

import org.apache.ibatis.annotations.Param;

/**
 * UserMapper
 * Mybatis実行するためのクラス
 * ユーザ情報を渡す.
 */
public interface UserMapper {
  // @select("select count(*) from test where id = ?")
  String selectUserByKey(
        @Param("name") String name,
        @Param("password") String password);
}
