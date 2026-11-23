package service;

import form.RestaurantDeleteForm;
import form.RestaurantRegistForm;

/**
 * MenuRegistService
 * メニュー登録操作.
 */
public interface RestrnDeleteService {

  public RestrnDeleteServiceResult execute(RestaurantDeleteForm form, String userName);

}
