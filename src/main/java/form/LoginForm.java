package form;

import common.MessageUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginForm {

  private String name;
  private String password;
  private String message;

  public String inputCheck() {
    if (this.name == null || this.name.isEmpty()) {
      this.message = MessageUtil.getProperty("msg_w02_login");
    } else if (this.password == null ||  this.password.isEmpty()) {
      this.message = MessageUtil.getProperty("msg_w02_login");
    }
    return message;
  }
}
