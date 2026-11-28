package service;

import common.MenuWebService;
import form.RestrnSearchForm;

/**
 * CsvInputService
 * CSVの検索条件入力.
 */
public interface RestrnSearchService
    extends MenuWebService<RestrnSearchForm, RestrnSearchServiceResult> {

  public RestrnSearchServiceResult initSearch(String userName);

  public RestrnSearchServiceResult execute(RestrnSearchForm form);
}
