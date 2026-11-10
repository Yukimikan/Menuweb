package form;

import common.MessageUtil;
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
  private String totalCondition;
  private String message;

  public int getTotal() {
	// ★ int で返す(NULLチェック後)
    return Integer.parseInt(this.total);
  }

  public String inputCheck() {
    if (this.total == null
      || this.total.trim().isEmpty()
      || "".equals(this.total)) {
    	// 未入力エラー
      this.message = MessageUtil.getProperty("msg_w01_csv_input");
    } else if (!total.matches("[0-9]+")) {
        // 数値以外
      this.message = MessageUtil.getProperty("msg_w02_csv_input");
    }
    return message;
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
