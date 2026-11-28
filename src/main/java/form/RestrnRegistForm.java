package form;

import common.BaseForm;
import common.MessageUtil;
import common.Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 店舗登録画面.
 *
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class RestrnRegistForm extends BaseForm {

  private String confirmFlg;
  private String restaurantName;
  private String type;
  private String userName;
  private String message;

  /**
   * 共通処理.
   *
   * @return message or blank
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
      this.message = MessageUtil.getProperty("msg_w01_restaurant_regist");
    }
    if (!Type.isValid(this.type)) {
      this.message = MessageUtil.getProperty("msg_w02_restaurant_regist");
    }
    return message;
  }

}
