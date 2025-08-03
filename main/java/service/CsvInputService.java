package service;

import java.util.List;

import model.MenuCSV;
import servlet.CsvInputServDto;

public interface CsvInputService {

  public List<MenuCSV> execute(CsvInputServDto input);

}
