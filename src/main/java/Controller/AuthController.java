package Controller;

import Model.AuthModel;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class AuthController {

  AuthModel authModel = new AuthModel();

  public boolean registration(String name, String password) {
    ObjectNode registrationData = authModel.registration(name, password);

    if (registrationData != null) {
      login(registrationData);
      return true;
    }

    return false;
  }

  public void login(ObjectNode newUserInfo) {
    authModel.login(newUserInfo);
  }

  public void logout() {
    authModel.logout();
  }
}
