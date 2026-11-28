package service;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.MenuEntity;

/**
 * MenuSearchServiceResult.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuSearchServiceResult {

  private boolean success;
  private String message;
  private List<MenuEntity> menuList;

}
