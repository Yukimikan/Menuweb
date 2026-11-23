package form;

import common.MessageUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.WebConst;

/**
 * 店舗検索画面.
 * 店名,種類
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantSearchForm {

  private String action;
  private String restaurantName;
  private String type;
  private String selectedRow;
  private String message;

  /**
   * 入力チェック.
   * 合計金額
   *
   * @return メッセージ
   */
  public String inputCheck() {
    //チェック
    if (WebConst.Action_削除.equals(this.action)) {
      if (this.selectedRow == null || this.selectedRow.isEmpty()) {
        this.message = MessageUtil.getProperty("msg_w02_restaurant_input");
      }
    }
    return message;
  }

}
