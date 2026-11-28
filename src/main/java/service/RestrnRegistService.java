package service;

import common.MenuWebService;
import form.RestrnRegistForm;

/**
 * RestrnRegistService
 * 店舗登録操作.
 */
public interface RestrnRegistService
    extends MenuWebService<RestrnRegistForm, RestrnRegistServiceResult> {

  public RestrnRegistServiceResult execute(RestrnRegistForm form);

}
