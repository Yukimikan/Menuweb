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
  private String total;
  private String totalcondition;
  private String message;

  public int getTotal() {
	// ★ int で返す(NULLチェック後)
    return Integer.parseInt(this.total);
  }

  public boolean inputCheck() {
    if (this.total == null
      || this.total.trim().isEmpty()
      || "".equals(this.total)) {
    	// 未入力エラー
      return false;
    } else if (!total.matches("[0-9]+")) {
        // 数値以外
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
