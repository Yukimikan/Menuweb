package form;

import common.MessageUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * メニュー検索画面.
 * 日付,種類,店名,単品,メニュー,価格,税,金額,金額(条件)
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuSearchForm {

  private String date;
  private String restaurantId;
  private String restaurantName;
  private String category;
  private String singleMenuFlg;
  private String menuName;
  private String eatInPrice;
  private String takeoutPrice;
  private String tax;
  private String total;
  private String eatInCondition;
  private String takeoutCondition;
  private String totalCondition;
  private String message;

  /**
   * 入力チェック.
   * 合計金額
   *
   * @return メッセージ
   */
  public String inputCheck() {
    // 数値以外
    if (!(this.eatInPrice == null || this.eatInPrice.isBlank())
        && !(this.eatInPrice.matches("[0-9]+"))) {
      // 数値エラー
      this.message = MessageUtil.getProperty("msg_w02_csv_input");
    }
    // 数値以外
    if (!(this.takeoutPrice == null || this.takeoutPrice.isBlank())
        && !(this.takeoutPrice.matches("[0-9]+"))) {
      // 数値エラー
      this.message = MessageUtil.getProperty("msg_w02_csv_input");
    }
    // 数値以外
    if (!(this.total == null || this.total.isBlank())
        && !(this.total.matches("[0-9]+"))) {
      // 数値エラー
      this.message = MessageUtil.getProperty("msg_w02_csv_input");
    }
    return message;
  }

}
