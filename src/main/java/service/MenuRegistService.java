package service;

import java.io.IOException;

import form.MenuConfirmForm;
import form.MenuRegistForm;

/**
 * MenuRegistService
 * メニュー登録操作.
 */
public interface MenuRegistService {

  public MenuRegistServiceResult execute(MenuRegistForm form, String userName) throws IOException;

  public MenuRegistServiceResult execute(MenuConfirmForm form, String userName) throws IOException;

  // public boolean formatCheck(MenuCSV rec);

}
