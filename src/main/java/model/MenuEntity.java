package model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MenuEntity {

  private String no;
  private String type;
  private String restaurant_name;
  private String singlemenu_flg;
  private String menu;
  private int price;
  private String tax;
  private int total;

}
