package service;

import form.RestaurantSearchForm;

/**
 * MenuRegistService
 * メニュー登録操作.
 */
public interface RestrnDeleteService {

  public RestrnDeleteServiceResult execute(RestaurantSearchForm form, String userName);

}
