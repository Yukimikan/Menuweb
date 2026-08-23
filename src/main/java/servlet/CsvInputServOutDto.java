package servlet;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.MenuCSV;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CsvInputServOutDto {

  private List<MenuCSV> retList;
  private String errMessage;
}
