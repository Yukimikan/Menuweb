package common;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * フォーム交通項目.
 * ユーザ名
 *
 */
@Data
@NoArgsConstructor
public abstract class BaseForm {

  public abstract String inputCheck();

  /**
   * 認証チェック.
   *
   * @return boolean
   */
  protected boolean authCheck(String userName) {

    if (userName == null || userName.isEmpty()) {
      return false;
    }
    return true;
  }

  /**
   * getMessage.
   *
   * @return message
   */
  protected String getMessage(String key) {
    String message = MessageUtil.getProperty(key);
    return message;
  }

}
