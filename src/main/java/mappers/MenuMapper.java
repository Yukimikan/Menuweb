package mappers;

import org.apache.ibatis.annotations.Param;

import model.MenuEntity;

public interface MenuMapper {
  int insert(@Param("entity") MenuEntity entity);
}
