package form;

import common.MessageUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * メニューCSV入力画面.
 * 日付,CSV名,種類,店名,単品,メニュー,価格,税,金額,金額(条件)
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CsvSearchForm {

  private String date;
  private String csvName;
  private String type;
  private String restaurantName;
  private String total;
  private String totalCondition;
  private String message;

  /**
   * 合計金額を返す.
   *
   * @return total 合計金額 or 0
   */
  public int getTotal() {
    // ★ int で返す(NULLチェック後)
    if (this.total.isBlank()) {
      return 0;
    } else {
      return Integer.parseInt(this.total);
    }
  }

  /**
   * 入力チェック.
   * 合計金額
   *
   * @return メッセージ
   */
  public String inputCheck() {
    // 数値以外
    if (!(total == null || total.isEmpty())) {
      if (total.matches("[0-9]+")) {
        this.message = MessageUtil.getProperty("msg_w02_csv_input");
      }
    }
    return message;
  }

}
