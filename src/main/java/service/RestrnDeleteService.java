package service;

import form.RestrnSearchForm;

/**
 * MenuRegistService
 * メニュー登録操作.
 */
public interface RestrnDeleteService {

  public RestrnDeleteServiceResult execute(RestrnSearchForm form, String userName);

}
