package service;

import java.util.List;

import common.ConditionTotal;
import common.CsvUtil;
import form.MenuCsvInputForm;
import model.MenuCSV;

/**
 * ファイル読込用サービス.
 * */
public class CsvInputServiceImpl implements CsvInputService {

  /**
   * execute.
   */
  public List<MenuCSV> execute(MenuCsvInputForm input) {
    try {
      ConditionTotal cond = ConditionTotal.fromCode(input.getTotalcondition());
      int total = input.getTotal();

      return CsvUtil.read(input.getCsvName()).stream()
        .filter(ret -> cond.match(ret.getPrice(), total))
        .toList();
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }
}
