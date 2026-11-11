package form;

import common.MessageUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuCsvInputForm {

  private String date;
  private String csvName;
  private String type;
  private String restaurantName;
  private String total;
  private String totalCondition;
  private String message;

  public int getTotal() {
	// ★ int で返す(NULLチェック後)
    return Integer.parseInt(this.total);
  }

  public String inputCheck() {
    if (!total.matches("[0-9]+")) {
        // 数値以外
      this.message = MessageUtil.getProperty("msg_w02_csv_input");
    }
    return message;
  }

}
