package Model;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class AuthModel {

  DBModel dbModel = new DBModel();
  
  
  public ObjectNode registration(String name, String password) {

    ObjectNode jsonDataTree = dbModel.getJsonDataTree();
    JsonNode userMatches = dbModel.findUser(jsonDataTree, name);

    if (userMatches != null) {
      return null;
    }

    return dbModel.addNewUser(jsonDataTree, name, password);
  }

  public void login(ObjectNode newUserInfo) {
    ObjectNode jsonDataTree = dbModel.getJsonDataTree();
    dbModel.loginUser(jsonDataTree, newUserInfo);
  }

  public void logout() {
    ObjectNode jsonDataTree = dbModel.getJsonDataTree();
    dbModel.logoutUser(jsonDataTree);
  }
}
