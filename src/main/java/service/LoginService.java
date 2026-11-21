package service;

import form.LoginForm;

/**
 * LoginService
 * ログイン操作.
 */
public interface LoginService {

  public LoginServiceResult execute(LoginForm form);
}
