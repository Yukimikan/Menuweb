package form;

import common.BaseForm;
import common.MessageUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * メニューCSV登録画面.
 *
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class CsvWriteForm extends BaseForm {

  private String csvName;
  private String confirmFlg;
  private String type;
  private String restaurantName;
  private String singleMenuFlg;
  private String menu;
  private String price;
  private String tax;
  private String total;
  private String message;

  /**
   * 共通処理.
   *
   * @return boolean
   */
  @Override
  public String inputCheck() {

    //チェック
    if (this.type == null || this.type.isEmpty()) {
      this.message = MessageUtil.getProperty("msg_w01_csv_regist");
    } else if (this.restaurantName == null || this.restaurantName.isEmpty()) {
      this.message = MessageUtil.getProperty("msg_w01_csv_regist");
    } else if (this.menu == null || this.menu.isEmpty()) {
      this.message = MessageUtil.getProperty("msg_w01_csv_regist");
    }
    if (this.price == null || this.price.isEmpty()) {
      this.message = MessageUtil.getProperty("msg_w01_csv_regist");
    } else {
      // 未入力の場合
      if (!this.price.matches("[0-9]+")) {
        this.message = MessageUtil.getProperty("msg_w02_csv_regist");
      }
    }
    if (this.tax == null || this.tax.isEmpty()) {
      this.message = MessageUtil.getProperty("msg_w01_csv_regist");
    } else {
      // 未入力の場合
      if (!this.tax.matches("[0-9]+")) {
        this.message = MessageUtil.getProperty("msg_w02_csv_regist");
      }
    }
    if (this.total == null || this.total.isEmpty()) {
      this.message = MessageUtil.getProperty("msg_w01_csv_regist");
    } else {
      // 未入力の場合
      if (!this.total.matches("[0-9]+")) {
        this.message = MessageUtil.getProperty("msg_w02_csv_regist");
      }
    }
    return message;
  }
}
