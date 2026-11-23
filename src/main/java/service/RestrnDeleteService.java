package service;

import form.RestaurantDeleteForm;
import form.RestaurantRegistForm;

/**
 * MenuRegistService
 * メニュー登録操作.
 */
public interface RestrnDeleteService {

  public RestrnRegistServiceResult execute(RestaurantDeleteForm form, String userName);

}
