package service;

import common.MenuWebService;
import form.RestrnRegistForm;

/**
 * MenuRegistService
 * メニュー登録操作.
 */
public interface RestrnRegistService
    extends MenuWebService<RestrnRegistForm, RestrnRegistServiceResult> {

  public RestrnRegistServiceResult execute(RestrnRegistForm form);

}
