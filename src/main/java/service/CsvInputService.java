package service;

import form.MenuCsvInputForm;
import form.MenuCsvInputResult;

public interface CsvInputService {

  public MenuCsvInputResult execute(MenuCsvInputForm input);
}
