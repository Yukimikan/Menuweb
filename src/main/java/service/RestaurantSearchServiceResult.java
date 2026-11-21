package service;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.RestaurantEntity;

/**
 * RestaurantSearchServiceResult.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantSearchServiceResult {

  private boolean success;
  private String message;
  private List<RestaurantEntity> restaurantList;

}
