package form;

import common.MessageUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuRegistForm {

  private String confirmFlg;
  private String type;
  private String restaurantName;
  private String singleMenuFlg;
  private String menu;
  private String price;
  private String tax;
  private String total;
  private String message;
  /**
   * 共通処理.
   *
   * @return boolean
   */
  public String inputCheck() {

    //チェック
    if (this.type == null || this.type.isEmpty()) {
      this.message = MessageUtil.getProperty("msg_w01_csv_regist");
    } else if (this.restaurantName == null || this.restaurantName.isEmpty()) {
      this.message = MessageUtil.getProperty("msg_w01_csv_regist");
    } else if (this.menu == null || this.menu.isEmpty()) {
      this.message = MessageUtil.getProperty("msg_w01_csv_regist");
    } else if (this.price == null || this.price.isEmpty()) {
      this.message = MessageUtil.getProperty("msg_w01_csv_regist");
    } else if (this.tax == null || this.tax.isEmpty()) {
      this.message = MessageUtil.getProperty("msg_w01_csv_regist");
    } else if (this.total == null || this.total.isEmpty()) {
      this.message = MessageUtil.getProperty("msg_w01_csv_regist");
    }
    return message;
  }
}
