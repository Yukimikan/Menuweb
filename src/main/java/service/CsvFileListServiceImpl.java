package service;

import java.io.IOException;
import java.util.List;

import common.FileControl;

/**
 * ファイル読込用サービス.
 * */
public class CsvFileListServiceImpl implements CsvFileListService {

  //絶対パスで指定
  public static final String CSV_FOLDER_PATH = "C:\\pleiades\\workspace\\Menuweb\\resource\\";
  public static final String EXTENSION_CSV = ".csv";

  /**
   * execute.
   */
  public List<String> execute() throws IOException {

	//CSVファイルパスからCSV名を取得
	return FileControl.getFileNames(CSV_FOLDER_PATH, EXTENSION_CSV);
  }

}
