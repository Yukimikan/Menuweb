package service;

import form.MenuCsvInputForm;

/**
 * CsvInputService
 * CSVの検索条件入力.
 */
public interface CsvSearchService {

  public CsvSearchServiceResult execute(MenuCsvInputForm form);
}
