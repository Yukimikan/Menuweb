package service;

import java.io.IOException;

import form.MenuCsvRegistForm;

public interface CsvWriteService {

  public CsvWriteServiceResult execute(MenuCsvRegistForm input) throws IOException;

  // public boolean formatCheck(MenuCSV rec);

}
