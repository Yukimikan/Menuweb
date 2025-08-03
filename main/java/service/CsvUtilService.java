package service;

import java.util.List;

import model.MenuCSV;

public interface CsvUtilService {

  public List<MenuCSV> read(String infilename) throws Exception;

  public void write(MenuCSV rec, String outfilename) throws Exception;
}
