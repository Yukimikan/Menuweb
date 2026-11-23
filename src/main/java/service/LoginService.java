package service;

import common.MenuWebService;
import form.LoginForm;

/**
 * LoginService
 * ログイン操作.
 */
public interface LoginService extends MenuWebService<LoginForm, LoginServiceResult> {

  public LoginServiceResult execute(LoginForm form);
}
