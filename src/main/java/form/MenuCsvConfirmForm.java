package form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuCsvConfirmForm {

  private String csvName;
  private String type;
  private String restaurantName;
  private String singlemenuFlg;
  private String menu;
  private String price;
  private String tax;
  private String total;
  private String message;

}
