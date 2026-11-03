package service;

import java.util.List;

import form.MenuCsvInputForm;
import model.MenuCSV;

public interface CsvInputService {

  public List<MenuCSV> execute(MenuCsvInputForm input);

}
