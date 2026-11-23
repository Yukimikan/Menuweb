package service;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.RestaurantEntity;

/**
 * RestaurantListGetServiceResult.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantListGetServiceResult {

  private boolean success;
  private List<RestaurantEntity> restaurantList;
  private String message;

}
