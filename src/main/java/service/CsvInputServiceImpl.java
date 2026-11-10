package service;

import java.util.Collections;
import java.util.List;

import common.ConditionTotal;
import common.CsvUtil;
import form.MenuCsvInputForm;
import model.MenuCSV;

/**
 * ファイル読込用サービス.
 * */
public class CsvInputServiceImpl implements CsvInputService {

  public static final String CSV_PATH = "C:\\pleiades\\workspace\\Menuweb\\resource\\";

  /**
   * execute.
   */
  public CsvInputServiceResult execute(MenuCsvInputForm input) {
    try {
      ConditionTotal cond = ConditionTotal.fromCode(input.getTotalCondition());
      int total = input.getTotal();

      // ヘッダー一致チェック
      if(MenuCSV.readHeader(CSV_PATH + input.getCsvName())) {
        List<MenuCSV> list =
    		    CsvUtil.read(input.getCsvName()).stream()
    	        .filter(ret -> cond.match(ret.getPrice(), total))
    	        .toList();

        return new CsvInputServiceResult(
    	        true,
    		    "",
    		    list
    		    );
      } else {
        return new CsvInputServiceResult(
     	        false,
      		    "エラー：CSVヘッダーが一致しません",
      		    Collections.emptyList()
      		    );

      }
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

}
