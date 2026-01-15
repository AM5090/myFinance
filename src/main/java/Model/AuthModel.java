package Model;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.Arrays;

public class AuthModel {

  DBModel dbModel = new DBModel();
  
  
  public boolean registration(String name, String password) {

    ObjectNode jsonDataTree = dbModel.getJsonDataTree();
    JsonNode userMatches = dbModel.findUser(jsonDataTree, name);

    if (userMatches != null) {
      return false;
    }

    ObjectNode newUser = dbModel.addNewUser(jsonDataTree, name, password);
    if (newUser != null) {
      autoLoginUser(newUser);
      return true;
    }

    return false;
  }

  public void autoLoginUser(ObjectNode newUserInfo) {
    ObjectNode jsonDataTree = dbModel.getJsonDataTree();
    dbModel.autoLoginUser(jsonDataTree, newUserInfo);
  }

  public int login(String name, String password) {
    ObjectNode jsonDataTree = dbModel.getJsonDataTree();
    JsonNode userMatches = dbModel.findUser(jsonDataTree, name);

    if (userMatches == null) {
      return 2; // такого пользователя не существует
    }

    String userNodePass = userMatches.get("password").asText();

    if (Arrays.equals(userNodePass.toCharArray(), password.toCharArray())) {
      dbModel.autoLoginUser(jsonDataTree, (ObjectNode) userMatches);
      return 0; // пользователь авторизован
    } else {
      return 1; // не верный пароль или логин
    }
  }

  public void logout() {
    ObjectNode jsonDataTree = dbModel.getJsonDataTree();
    dbModel.logoutUser(jsonDataTree);
  }

  public boolean userAuth() {
    ObjectNode jsonDataTree = dbModel.getJsonDataTree();
    return dbModel.checkUserAuth(jsonDataTree);
  }
}
