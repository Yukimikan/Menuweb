package service;

import java.io.IOException;

import form.MenuCsvRegistForm;
import form.MenuCsvRegistResult;

public interface CsvWriteService {

  public MenuCsvRegistResult execute(MenuCsvRegistForm input) throws IOException;

  // public boolean formatCheck(MenuCSV rec);

}
