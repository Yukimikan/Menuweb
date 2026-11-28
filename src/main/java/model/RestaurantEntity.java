package model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 店舗
 * 登録画面の項目.
 *
 */
@Data
@AllArgsConstructor
public class RestaurantEntity {

  private int restaurantId;
  private String restaurantName;
  private String type;
  private String updateUser;
  // private Date update_date // SQLで発行

}
