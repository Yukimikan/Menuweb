package service;

import form.RestaurantConfirmForm;
import form.RestaurantRegistForm;

/**
 * MenuRegistService
 * メニュー登録操作.
 */
public interface RestrnRegistService {

  public RestrnRegistServiceResult execute(RestaurantRegistForm form, String userName);

  public RestrnRegistServiceResult execute(RestaurantConfirmForm form, String userName);

}
