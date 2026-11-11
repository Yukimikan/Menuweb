package service;

import java.util.Collections;
import java.util.List;

import common.ConditionTotal;
import common.CsvUtil;
import common.MessageUtil;
import form.MenuCsvInputForm;
import model.GlobalConst;
import model.MenuCSV;

/**
 * ファイル読込用サービス.
 * */
public class CsvInputServiceImpl implements CsvInputService {

  /**
   * execute.
   */
  public CsvInputServiceResult execute(MenuCsvInputForm form) {

	CsvInputServiceResult result = new CsvInputServiceResult();

    try {
      ConditionTotal cond = ConditionTotal.fromCode(form.getTotalCondition());
      int total = form.getTotal();
      List<MenuCSV> list = Collections.emptyList();

      // ヘッダー一致チェック
      if(!MenuCSV.readHeader(GlobalConst.CSV_FOLDER_PATH + form.getCsvName())) {
  	   	result.setSuccess(false);
       	result.setMessage(MessageUtil.getProperty("msg_e01_csv_header"));
      } else {
        list = CsvUtil.read(form.getCsvName()).stream()
    	      .filter(ret ->
    	              cond.match(ret.getPrice(), total)
    	              && isMatch(form.getType(), ret.getType())
    	              && isMatch(form.getRestaurantName(), ret.getRestaurantName())
    	              )
      	      .toList();
        result.setSuccess(true);
      }
  	  // result
  	  if (result.isSuccess()) {
        result.setRetList(list);
        return result;
  	  }
    } catch (Exception e) {
      e.printStackTrace();
    }
    // return
    return result;
  }

  private boolean isMatch(String condition, String value) {
	    return condition == null
	            || condition.isEmpty()
	            || condition.equals(value);
	}

}
