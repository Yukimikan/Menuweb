package servlet;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.MenuCSV;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CsvWriteServDto {

  private MenuCSV rec;
}
