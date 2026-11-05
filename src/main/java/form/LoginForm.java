package form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginForm {
  private String name;
  private String password;

  public boolean inputCheck() {
    if (this.name == null || this.name.isEmpty()) {
      return false;
    } else if (this.password == null ||  this.password.isEmpty()) {
      return false;
    }
    return true;
  }
}
