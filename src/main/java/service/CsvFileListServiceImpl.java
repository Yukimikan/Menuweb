package service;

import java.io.IOException;
import java.util.List;

import common.FileControl;
import model.GlobalConst;

/**
 * ファイル読込用サービス.
 */
public class CsvFileListServiceImpl implements CsvFileListService {

  /**
   * execute.
   */
  public List<String> getList() throws IOException {

    //CSVファイルパスからCSV名を取得
    return FileControl.getFileNames(GlobalConst.CSV_FOLDER_PATH, GlobalConst.EXTENSION_CSV);
  }

}
