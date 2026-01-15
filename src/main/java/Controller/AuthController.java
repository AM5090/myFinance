package Controller;

import Model.AuthModel;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class AuthController {

  AuthModel authModel = new AuthModel();

  public boolean registration(String name, String password) {
    return authModel.registration(name, password);
  }

  public int login(String name, String password) {
    return authModel.login(name, password);
  }

  public void logout() {
    authModel.logout();
  }

  public boolean userAuth() {
    return authModel.userAuth();
  }


}
