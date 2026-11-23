package form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * メニュー登録確認画面.
 * 種類,店名,単品,メニュー,価格
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuConfirmForm {

  private String restaurantName;
  private String menuName;
  private String category;
  private String singleMenuFlg;
  private String eatInPrice;
  private String takeoutPrice;
  private String message;

}
