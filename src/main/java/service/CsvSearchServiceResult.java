package service;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.MenuCSV;

/**
 * CsvInputServiceResult.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CsvSearchServiceResult {

  private boolean success;
  private String message;
  private List<MenuCSV> retList;

}
