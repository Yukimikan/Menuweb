package service;

import java.io.IOException;
import java.util.List;

/**
 * CsvFileListService
 * CSVの一覧をフォルダから取得.
 */
public interface CsvFileListService {

  public List<String> execute() throws IOException;

}
