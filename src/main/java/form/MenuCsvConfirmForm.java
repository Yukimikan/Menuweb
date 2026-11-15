package form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * メニューCSV登録確認画面.
 * 種類,店名,単品,メニュー,価格,税,金額
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuCsvConfirmForm {

  private String csvName;
  private String type;
  private String restaurantName;
  private String singlemenuFlg;
  private String menu;
  private String price;
  private String tax;
  private String total;
  private String message;

}
