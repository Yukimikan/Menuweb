package service;

import common.MenuWebService;
import form.CsvWriteForm;

/**
 * CsvWriteService
 * CSVの書込み.
 */
public interface CsvWriteService extends MenuWebService<CsvWriteForm, CsvWriteServiceResult> {

  public CsvWriteServiceResult execute(CsvWriteForm input);

}
