package form;

import common.BaseForm;
import common.MessageUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * メニュー検索画面.
 * 日付,種類,店名,単品,メニュー,価格,税,金額,金額(条件)
 *
 */
@Data
@EqualsAndHashCode(callSuper=true)
@NoArgsConstructor
@AllArgsConstructor
public class MenuSearchForm extends BaseForm{

  private String date;
  private String restaurantId;
  private String restaurantName;
  private String category;
  private String singleMenuFlg;
  private String menuName;
  private String eatInPrice;
  private String takeoutPrice;
  private String eatInCondition;
  private String takeoutCondition;
  private String totalCondition;
  private String userName;
  private String message;

  /**
   * 入力チェック.
   * 合計金額
   *
   * @return メッセージ
   */
  @Override
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
    return message;
  }

}
