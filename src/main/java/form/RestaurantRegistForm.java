package form;

import common.MessageUtil;
import common.Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 店舗登録画面.
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantRegistForm {

  private String confirmFlg;
  private String restaurantName;
  private String type;
  private String userName;
  private String message;

  /**
   * 共通処理.
   *
   * @return boolean
   */
  public String inputCheck() {

    //認証・認可チェック
    if (this.userName == null || this.userName.isEmpty()) {
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
