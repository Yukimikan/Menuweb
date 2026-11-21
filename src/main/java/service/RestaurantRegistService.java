package service;

import java.io.IOException;

import form.RestaurantConfirmForm;
import form.RestaurantRegistForm;

/**
 * MenuRegistService
 * メニュー登録操作.
 */
public interface RestaurantRegistService {

  public RestaurantRegistServiceResult execute(RestaurantRegistForm form, String userName) throws IOException;

  public RestaurantRegistServiceResult execute(RestaurantConfirmForm form, String userName) throws IOException;

}
