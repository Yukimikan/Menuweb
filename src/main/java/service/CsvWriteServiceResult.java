package service;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.MenuCSV;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CsvWriteServiceResult {
  private boolean success;
  private String message;
  private List<MenuCSV> retList;
}
