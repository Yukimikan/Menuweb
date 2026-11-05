package service;

import common.MessageUtil;
import dao.UserDaoImpl;
import form.LoginForm;

public class LoginServiceImpl implements LoginService{

  public LoginServiceResult execute(LoginForm form) {
	try {
	  UserDaoImpl daoImpl = new UserDaoImpl();
  	  String userName = daoImpl.selectUserByKey(form.getName(), form.getPassword());
  	  if (userName == null || userName.isEmpty()) {
  	  	// 認証エラー
        System.out.print(MessageUtil.getProperty("msg_w01_login"));
        return new LoginServiceResult
        		(false,
        		MessageUtil.getProperty("msg_w01_login"),
        		"");
      } else {
  	    return new LoginServiceResult
  	    		(true,
  	    		"",
  	    		userName);
  	  }
    } catch (Exception e) {
	  System.out.print(e.getMessage());
    }
	return null;
  }

}
