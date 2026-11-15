package service;

import form.MenuSearchForm;

/**
 * CsvInputService
 * CSVの検索条件入力.
 */
public interface MenuSearchService {

  public MenuSearchServiceResult execute(MenuSearchForm form, String userName);
}
