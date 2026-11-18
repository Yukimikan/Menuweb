package service;

import java.util.Collections;
import java.util.List;

import common.ConditionTotal;
import common.CsvUtil;
import common.MessageUtil;
import form.CsvSearchForm;
import model.GlobalConst;
import model.MenuCSV;

/**
 * ファイル読込用サービス.
 * */
public class CsvSearchServiceImpl implements CsvSearchService {

  /**
   * execute.
   */
  public CsvSearchServiceResult execute(CsvSearchForm form) {

    CsvSearchServiceResult result = new CsvSearchServiceResult();

    ConditionTotal cond = ConditionTotal.fromCode(form.getTotalCondition());

    int total = this.convertTotal(form.getTotal());

    try {
      // ヘッダー一致チェック
      if (!MenuCSV.readHeader(GlobalConst.CSV_FOLDER_PATH + form.getCsvName())) {
        result.setSuccess(false);
        result.setMessage(MessageUtil.getProperty("msg_e01_csv_header"));
        result.setRetList(Collections.emptyList());
      } else {
        List<MenuCSV> tmpList = CsvUtil.read(form.getCsvName());
        List<MenuCSV> retList = tmpList.stream()
            .filter(ret ->
                  cond.match(ret.getTotal(), total)
                  && isMatch(form.getType(), ret.getType())
                  && isMatch(form.getRestaurantName(), ret.getRestaurantName())
                  )
              .toList();
        if (tmpList.size() == 0) {
          // 結果0件
          result.setSuccess(true);
          result.setMessage("0件");
          result.setRetList(Collections.emptyList());
          System.out.print(result.getMessage());
        } else {
          result.setSuccess(true);
          result.setRetList(retList);
        }
      }
      // result
      if (result.isSuccess()) {
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

  /**
   * 合計金額を返す.
   *
   * @return total 合計金額 or 0
   */
  public int convertTotal(String total) {
    // ★ int で返す(NULLチェック後)
    if (total == null || total.isBlank()) {
      return 0;
    } else {
      return Integer.parseInt(total);
    }
  }

}
