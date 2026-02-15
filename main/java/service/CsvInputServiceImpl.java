package service;

import java.util.ArrayList;
import java.util.List;

import common.CsvUtil;
import form.MenuCsvInputForm;
import model.MenuCSV;

/**
 * ファイル読込用サービス.
 * */
public class CsvInputServiceImpl implements CsvInputService {

  List<MenuCSV> retList = new ArrayList<>();

  /**
   * execute.
   */
  public List<MenuCSV> execute(MenuCsvInputForm input) {

    try {
      retList = CsvUtil.read(input.getCsvName());
    } catch (Exception e) {
      e.printStackTrace();
    }
    return retList;
  }
//
//  /**
//   * 共通処理.
//   *
//   * @return boolean
//   */
//  public boolean commonCheck(String filename) {
//
//	//チェック
//    if (filename == null || filename.isEmpty()) {
//      System.out.println(GlobalConst.MSG_W_FILENAME_ERROR);
//      System.out.println("filename:" + filename);
//      return false;
//    }
//    return true;
//  }

}
