package service;

import java.io.IOException;
import java.util.List;

import form.MenuCsvInputForm;

public interface CsvInputGetService {

  public List<String> execute(MenuCsvInputForm input) throws IOException;

}
