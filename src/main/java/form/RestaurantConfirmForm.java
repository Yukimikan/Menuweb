package form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 店舗登録確認画面.
 * 店舗ID,店名,種類
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantConfirmForm {

  private String restaurantId;
  private String restaurantName;
  private String type;
  private String message;

}
