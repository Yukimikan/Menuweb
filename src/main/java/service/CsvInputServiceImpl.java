package service;

import java.util.List;

import common.ConditionTotal;
import common.CsvUtil;
import form.MenuCsvInputForm;
import form.MenuCsvInputResult;
import model.MenuCSV;

/**
 * ファイル読込用サービス.
 * */
public class CsvInputServiceImpl implements CsvInputService {

  /**
   * execute.
   */
  public MenuCsvInputResult execute(MenuCsvInputForm input) {
    try {
      ConditionTotal cond = ConditionTotal.fromCode(input.getTotalcondition());
      int total = input.getTotal();
      List<MenuCSV> list =
    		    CsvUtil.read(input.getCsvName()).stream()
    	        .filter(ret -> cond.match(ret.getPrice(), total))
    	        .toList();

      return new MenuCsvInputResult(
    		  true,
    		  "",
    		  list
    		  );
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

}
