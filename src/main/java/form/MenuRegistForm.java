package form;

import common.MessageUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * メニュー登録画面.
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuRegistForm {

  private String confirmFlg;
  // private String type;
  private String restaurantId;
  private String restaurantName;
  private String menuName;
  private String category;
  private String singleMenuFlg;
  private String eatInPrice;
  private String takeoutPrice;
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
    if (this.restaurantId == null || this.restaurantId.isEmpty()) {
      this.message = MessageUtil.getProperty("msg_w01_csv_regist");
    } else if (this.restaurantName == null || this.restaurantName.isEmpty()) {
      this.message = MessageUtil.getProperty("msg_w01_csv_regist");
    } else if (this.menuName == null || this.menuName.isEmpty()) {
      this.message = MessageUtil.getProperty("msg_w01_csv_regist");
    } else if (this.category == null || this.category.isEmpty()) {
      this.message = MessageUtil.getProperty("msg_w01_csv_regist");
    }
    if (this.eatInPrice == null || this.eatInPrice.isEmpty()) {
      this.message = MessageUtil.getProperty("msg_w01_csv_regist");
    } else {
      // 未入力の場合
      if (!eatInPrice.matches("[0-9]+")) {
        this.message = MessageUtil.getProperty("msg_w02_csv_regist");
      }
    }
    if (this.takeoutPrice == null || this.takeoutPrice.isEmpty()) {
        this.message = MessageUtil.getProperty("msg_w01_csv_regist");
      } else {
        // 未入力の場合
        if (!takeoutPrice.matches("[0-9]+")) {
          this.message = MessageUtil.getProperty("msg_w02_csv_regist");
        }
      }
    if (this.tax == null || this.tax.isEmpty()) {
      this.message = MessageUtil.getProperty("msg_w01_csv_regist");
    } else {
      // 未入力の場合
      if (!tax.matches("[0-9]+")) {
        this.message = MessageUtil.getProperty("msg_w02_csv_regist");
      }
    }
    if (this.total == null || this.total.isEmpty()) {
      this.message = MessageUtil.getProperty("msg_w01_csv_regist");
    } else {
      // 未入力の場合
      if (!total.matches("[0-9]+")) {
        this.message = MessageUtil.getProperty("msg_w02_csv_regist");
      }
    }
    return message;
  }

}
