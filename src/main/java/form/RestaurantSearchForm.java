package form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 店舗検索画面.
 * 店名,種類
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantSearchForm {

  // private String restaurantId;
  private String restaurantName;
  private String type;
  private String message;

  /**
   * 入力チェック.
   * 合計金額
   *
   * @return メッセージ
   */
  public String inputCheck() {
    return "";
  }

}
