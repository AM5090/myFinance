package Controller;

import Model.AuthModel;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class AuthController {

  AuthModel authModel = new AuthModel();

  public boolean registration(String name, String password) {
    ObjectNode registrationData = authModel.registration(name, password);

    if (registrationData != null) {
      login(registrationData, name, password);
      return true;
    }

    return false;
  }

  public void login(ObjectNode newUserInfo, String name, String password) {
    System.out.println("Автоматический вход");
    authModel.login(newUserInfo, name, password);
  }

  public void logout() {

  }
}
