package service;

import form.RestaurantSearchForm;

/**
 * CsvInputService
 * CSVの検索条件入力.
 */
public interface RestrnSearchService {

  public RestrnSearchServiceResult initSearch(String userName);

  public RestrnSearchServiceResult execute(RestaurantSearchForm form, String userName);
}
