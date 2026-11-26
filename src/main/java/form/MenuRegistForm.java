package form;

import common.BaseForm;
import common.MessageUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * メニュー登録画面.
 *
 */
@Data
@EqualsAndHashCode(callSuper=true)
@NoArgsConstructor
@AllArgsConstructor
public class MenuRegistForm extends BaseForm{

  private String confirmFlg;
  private String restaurantName;
  private String menuName;
  private String category;
  private String singleMenuFlg;
  private String eatInPrice;
  private String takeoutPrice;
  private String userName;
  private String message;

  /**
   * 共通処理.
   *
   * @return boolean
   */
  @Override
  public String inputCheck() {
    //認証・認可チェック
    if (super.authCheck(this.userName) == false) {
      // error
      return MessageUtil.getProperty("msg_e02_no_login");
    }
    //チェック
    if (this.restaurantName == null || this.restaurantName.isEmpty()) {
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
    return message;
  }

}
