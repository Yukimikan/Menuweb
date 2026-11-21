package service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * MenuRegistServiceResult.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantRegistServiceResult {

  private boolean success;
  private String message;

}
