package service;

import common.MessageUtil;
import dao.UserDao;
import dao.UserDaoImpl;
import form.LoginForm;

/*
 * service.
 */
public class LoginServiceImpl implements LoginService{

  private UserDao userDao;

  public LoginServiceImpl(){
    userDao = new UserDaoImpl();
  }

  public LoginServiceResult execute(LoginForm form) {

	LoginServiceResult result = new LoginServiceResult();

	try {
  	  String userName = userDao.selectUserByKey(form.getName(), form.getPassword());
  	  if (userName == null || userName.isEmpty()) {
  	  	// 認証エラー
        result.setSuccess(false);
        result.setMessage(MessageUtil.getProperty("msg_w01_login"));
        System.out.print(result.getMessage());
      } else {
        result.setSuccess(true);
  	  }
  	  // result
  	  if (result.isSuccess()) {
        result.setUserName(userName);
        return result;
  	  }
    } catch (Exception e) {
	  System.out.print(e.getMessage());
    }
    // return
    return result;
  }

}
