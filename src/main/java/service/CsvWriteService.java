package service;

import java.io.IOException;
import java.util.List;

import form.MenuCsvRegistForm;
import model.MenuCSV;

public interface CsvWriteService {

  public List<MenuCSV> execute(MenuCsvRegistForm input) throws IOException;

  public boolean formatCheck(MenuCSV rec);

}
