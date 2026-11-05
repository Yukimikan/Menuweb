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
    if (this.csv_name == null || this.csv_name.isEmpty()) {
      System.out.println(GlobalConst.MSG_W_FILENAME_ERROR);
      System.out.println("filename:" + this.csv_name);
      return false;
    }
    return true;
  }
}
