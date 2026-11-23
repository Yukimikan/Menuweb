package service;

import common.MenuWebService;
import form.CsvSearchForm;

/**
 * CsvInputService
 * CSVの検索条件入力.
 */
public interface CsvSearchService extends MenuWebService<CsvSearchForm, CsvSearchServiceResult>{

  public CsvSearchServiceResult execute(CsvSearchForm form);
}
