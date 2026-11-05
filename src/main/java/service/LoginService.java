package service;

import form.LoginForm;
import form.LoginResult;

public interface LoginService {

  public LoginResult execute(LoginForm form);
}
