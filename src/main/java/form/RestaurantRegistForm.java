package form;

import common.MessageUtil;
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
  private String restaurantId;
  private String restaurantName;
  private String type;
  private String message;

  /**
   * 共通処理.
   *
   * @return boolean
   */
  public String inputCheck() {

    //チェック
    if (this.restaurantId == null || this.restaurantId.isEmpty()) {
      this.message = MessageUtil.getProperty("msg_w01_restaurant_regist");
    } else if (this.restaurantName == null || this.restaurantName.isEmpty()) {
      this.message = MessageUtil.getProperty("msg_w01_restaurant_regist");
    }
    return message;
  }

}
