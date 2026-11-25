package service;

import common.MenuWebService;
import form.MenuRegistForm;

/**
 * MenuRegistService
 * メニュー登録操作.
 */
public interface MenuRegistService extends MenuWebService<MenuRegistForm, MenuRegistServiceResult>{

  public MenuRegistServiceResult execute(MenuRegistForm form);

}
