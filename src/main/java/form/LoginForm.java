package form;

import common.BaseForm;
import common.MessageUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * ログイン画面.
 * ユーザ名
 * パスワード
 *
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class LoginForm extends BaseForm {

  private String name;
  private String password;
  private String message;

  /**
   * 共通処理.
   *
   * @return boolean
   */
  @Override
  public String inputCheck() {
    if (this.name == null || this.name.isEmpty()) {
      this.message = MessageUtil.getProperty("msg_w02_login");
    } else if (this.password == null ||  this.password.isEmpty()) {
      this.message = MessageUtil.getProperty("msg_w02_login");
    }
    return message;
  }
}
