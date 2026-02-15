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
  private String errMessage;

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
