package service;

import form.CsvSearchForm;

/**
 * CsvInputService
 * CSVの検索条件入力.
 */
public interface CsvSearchService {

  public CsvSearchServiceResult execute(CsvSearchForm form);
}
