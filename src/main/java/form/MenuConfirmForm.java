package form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuConfirmForm {

  private String type;
  private String restaurantName;
  private String singleMenuFlg;
  private String menu;
  private String price;
  private String tax;
  private String total;
  private String userName;
  private String message;

}
