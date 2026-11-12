package model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MenuEntity {

  private int no;
  private String type;
  private String restaurantName;
  private String singleMenuFlg;
  private String menu;
  private int price;
  private int tax;
  private int total;
  private String updateUser;
  // private Date update_date // SQLで発行

}
