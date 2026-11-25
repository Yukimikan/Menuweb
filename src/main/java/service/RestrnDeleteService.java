package service;

import common.MenuWebService;
import form.RestrnSearchForm;

/**
 * MenuRegistService
 * メニュー登録操作.
 */
public interface RestrnDeleteService extends MenuWebService<RestrnSearchForm, RestrnDeleteServiceResult>{

  public RestrnDeleteServiceResult execute(RestrnSearchForm form);

}
