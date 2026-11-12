package service;

import java.io.IOException;

import form.MenuConfirmForm;
import form.MenuRegistForm;

public interface MenuRegistService {

  public MenuRegistServiceResult execute(MenuRegistForm form, String userName) throws IOException;

  public MenuRegistServiceResult execute(MenuConfirmForm form) throws IOException;

  // public boolean formatCheck(MenuCSV rec);

}
