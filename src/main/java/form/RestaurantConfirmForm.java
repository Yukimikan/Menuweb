package form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 店舗登録確認画面.
 * 店名,種類
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantConfirmForm {

  private String restaurantName;
  private String type;
  private String message;

}
