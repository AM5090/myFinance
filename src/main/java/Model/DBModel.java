package Model;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;

public class DBModel {
  public static ObjectMapper mapper;
  public static File jsonFile;

  public DBModel() {
    mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
    jsonFile = Paths.get("DB.json").toFile();
  }

  public ObjectNode getJsonDataTree() {
    try {
      return (ObjectNode) mapper.readTree(jsonFile);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public JsonNode findUser(ObjectNode dataTree, String userName) {
    ArrayNode allUsers = (ArrayNode) dataTree.get("users");

    for (JsonNode userNode : allUsers) {
      String userNodeName = userNode.get("name").asText();
      if (userNodeName.equals(userName)) {
        return userNode;
      }
    }
    return null;
  }

  public ObjectNode addNewUser(ObjectNode dataTree, String name, String password) {
    ArrayNode allUsers = (ArrayNode) dataTree.get("users");
    LinkedHashMap<String, String> newUser = new LinkedHashMap<>();

    newUser.put("id", String.valueOf(allUsers.size() + 1));
    newUser.put("name", name);
    newUser.put("password", password);

    ObjectNode newUserObject = mapper.valueToTree(newUser);
    allUsers.add(newUserObject);

    try {
      mapper.writerWithDefaultPrettyPrinter().writeValue(jsonFile, dataTree);
      return newUserObject;
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public void loginUser(ObjectNode dataTree, ObjectNode newUserInfo, String name, String password) {
//    JsonNode userAuth = dataTree.get("userAuth");

//    System.out.println("userAuth >>> " + userAuth);
//    System.out.println("newUserInfo >>> " + newUserInfo);

    ObjectNode newUserObject = mapper.valueToTree(newUserInfo);

    dataTree.put("userAuth", String.valueOf(newUserObject));

    try {
      mapper.writerWithDefaultPrettyPrinter().writeValue(jsonFile, dataTree);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
