package form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.GlobalConst;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuCsvInputForm {

  private String date;
  private String csvName;
  private int total;
  private String totalcondition;
  private String message;

  public MenuCsvInputForm(String date, String csvName, String total,
      String totalcondition, String message) {
    this.date = date;
    this.csvName = csvName;
    this.total = Integer.parseInt(total);   // ★ 例外発生源の近接化
    this.totalcondition = totalcondition;
    this.message = message;
  }

  public int getTotal() {              // ★ int で返す
    return total;
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
