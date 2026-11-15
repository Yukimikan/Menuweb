package service;

import java.io.IOException;

import form.CsvConfirmForm;
import form.CsvWriteForm;

/**
 * CsvWriteService
 * CSVの書込み.
 */
public interface CsvWriteService {

  public CsvWriteServiceResult execute(CsvWriteForm input) throws IOException;

  public CsvWriteServiceResult execute(CsvConfirmForm input) throws IOException;

  // public boolean formatCheck(MenuCSV rec);

}
