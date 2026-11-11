package service;

import java.io.IOException;

import form.MenuCsvConfirmForm;
import form.MenuRegistForm;

public interface MenuRegistService {

  public MenuRegistServiceResult execute(MenuRegistForm form) throws IOException;

  public CsvWriteServiceResult execute(MenuCsvConfirmForm input) throws IOException;

  // public boolean formatCheck(MenuCSV rec);

}
