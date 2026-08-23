package servlet;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CsvInputServDto {

  private String date;
  private String csvName;
  private String errMessage;
}
