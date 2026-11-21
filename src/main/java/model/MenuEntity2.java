package model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * メニュー
 * 登録画面の項目.
 *
 */
@Data
@AllArgsConstructor
public class MenuEntity2 {

  private int no;
  private int restaurantId;
  private String restaurantName;
  private String menuName;
  private String category;
  private String singleMenuFlg;
  private int eatInPrice;
  private int takeoutPrice;
  private int tax;
  private int total;
  private String updateUser;
  // private Date update_date // SQLで発行

}
