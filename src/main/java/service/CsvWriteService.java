package service;

import java.io.IOException;

import form.MenuCsvConfirmForm;
import form.MenuCsvRegistForm;

/**
 * CsvWriteService
 * CSVの書込み.
 */
public interface CsvWriteService {

  public CsvWriteServiceResult execute(MenuCsvRegistForm input) throws IOException;

  public CsvWriteServiceResult execute(MenuCsvConfirmForm input) throws IOException;

  // public boolean formatCheck(MenuCSV rec);

}
