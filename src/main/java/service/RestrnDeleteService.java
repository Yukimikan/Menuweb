package service;

import common.MenuWebService;
import form.RestrnSearchForm;

/**
 * RestrnDeleteService
 * 店舗削除操作.
 */
public interface RestrnDeleteService
    extends MenuWebService<RestrnSearchForm, RestrnDeleteServiceResult> {

  public RestrnDeleteServiceResult execute(RestrnSearchForm form);

}
