package service;

import common.MenuWebService;
import form.RestaurantRegistForm;

/**
 * MenuRegistService
 * メニュー登録操作.
 */
public interface RestrnRegistService
    extends MenuWebService<RestaurantRegistForm, RestrnRegistServiceResult> {

  public RestrnRegistServiceResult execute(RestaurantRegistForm form);

}
