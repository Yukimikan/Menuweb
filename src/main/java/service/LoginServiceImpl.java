package service;

import common.MessageUtil;
import dao.UserDaoImpl;
import form.LoginForm;

public class LoginServiceImpl implements LoginService{

  public String execute(LoginForm form) {

	String userName;
	System.out.println(form.getName());
	try {
	  UserDaoImpl daoImpl = new UserDaoImpl();
  	  userName = daoImpl.selectUserByKey(form.getName(), form.getPassword());
  	  if (userName == null || userName.isEmpty()) {
  	  	// 認証エラー
 	    String errMessage = MessageUtil.getProperty("msg_w01_login");
        System.out.print(errMessage);
        return "";
      } else {
  	    return userName;
  	  }
    } catch (Exception e) {
	  System.out.print(e.getMessage());
    }
	return null;
  }

}
