package service;

import common.MenuWebService;
import form.MenuSearchForm;

/**
 * MenuSearchService
 * メニュー検索条件入力.
 */
public interface MenuSearchService extends MenuWebService<MenuSearchForm, MenuSearchServiceResult> {

  public MenuSearchServiceResult execute(MenuSearchForm form);
}
