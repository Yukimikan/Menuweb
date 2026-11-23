package service;

import form.RestaurantSearchForm;

/**
 * CsvInputService
 * CSVの検索条件入力.
 */
public interface RestaurantSearchService {

  public RestaurantSearchServiceResult initSearch(String userName);

  public RestaurantSearchServiceResult execute(RestaurantSearchForm form, String userName);
}
