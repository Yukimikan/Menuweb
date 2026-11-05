package form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.GlobalConst;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuCsvRegistForm {

  private String csv_name;
  private String type;
  private String restaurant_name;
  private String singlemenu_flg;
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
  public boolean inputCheck() {

    //チェック
    if (this.type == null || this.type.isEmpty()) {
      return false;
    } else if (this.restaurant_name == null || this.restaurant_name.isEmpty()) {
      return false;
    } else if (this.menu == null || this.menu.isEmpty()) {
      return false;
    } else if (this.price == null || this.price.isEmpty()) {
      return false;
    } else if (this.tax == null || this.tax.isEmpty()) {
      return false;
    } else if (this.total == null || this.total.isEmpty()) {
      return false;
    }
    return true;
  }

  /**
   * 共通処理.
   *
   * @return boolean
   */
  public boolean commonCheck(String filename) {

    //チェック
    if (filename == null || filename.isEmpty()) {
      System.out.println(GlobalConst.MSG_W_FILENAME_ERROR);
      System.out.println("filename:" + filename);
      return false;
    }
    return true;
  }

}
