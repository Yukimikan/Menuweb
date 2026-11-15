package service;

import form.MenuCsvInputForm;

/**
 * CsvInputService
 * CSVの検索条件入力.
 */
public interface CsvInputService {

  public CsvInputServiceResult execute(MenuCsvInputForm form);
}
