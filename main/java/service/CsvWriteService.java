package service;

import java.util.List;

import model.MenuCSV;
import servlet.CsvWriteServDto;

public interface CsvWriteService {

  public List<MenuCSV> execute(CsvWriteServDto input);

  public boolean formatCheck(MenuCSV rec);

}
