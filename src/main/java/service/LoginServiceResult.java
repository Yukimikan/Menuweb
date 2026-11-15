package service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * LoginServiceResult.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginServiceResult {

  private boolean success;
  private String message;
  private String userName;

}
