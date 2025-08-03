package service;

import java.util.ArrayList;
import java.util.List;

import model.GlobalConst;
import model.MenuCSV;
import servlet.CsvInputServDto;

/**
 * ファイル読込用サービス.
 * */
public class CsvInputServiceImpl implements CsvInputService {

  CsvUtilService service = new CsvUtilServiceImpl();
  List<MenuCSV> retList = new ArrayList<>();

  /**
   * execute.
   */
  public List<MenuCSV> execute(CsvInputServDto input) {

    // 入力チェック
    if (this.commonCheck(input.getCsvName())) {
      try {
        retList = service.read(input.getCsvName());
      } catch (Exception e) {
        e.printStackTrace();
      }
      return retList;
    }
    return null;
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
