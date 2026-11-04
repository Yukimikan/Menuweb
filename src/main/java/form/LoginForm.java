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
	if (this.name == null) {
		return false;
	} else if (this.password == null) {
		return false;
	}
    return true;
  };
}
