package form;

import common.BaseForm;
import common.MessageUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import model.WebConst;

/**
 * 店舗検索画面.
 * 店名,種類
 *
 */
@Data
@EqualsAndHashCode(callSuper=true)
@NoArgsConstructor
@AllArgsConstructor
public class RestrnSearchForm extends BaseForm{

  private String action;
  private String restaurantName;
  private String type;
  private String selectedRow;
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
	// 認証
    if (WebConst.Action_削除.equals(this.action)) {
      //認証・認可チェック
      if (super.authCheck(this.userName) == false) {
        // error
        return MessageUtil.getProperty("msg_e02_no_login");
      }
      if (this.selectedRow == null || this.selectedRow.isEmpty()) {
        this.message = MessageUtil.getProperty("msg_w02_restaurant_input");
      }
    } else {
      // no check
    }
    return message;
  }

}
