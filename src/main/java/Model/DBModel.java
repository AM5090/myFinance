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

  public void loginUser(ObjectNode dataTree, ObjectNode newUserInfo) {

    ObjectNode userAuthNode = mapper.createObjectNode();
    userAuthNode.put("id", newUserInfo.get("id").asText());
    userAuthNode.put("name", newUserInfo.get("name").asText());

    dataTree.set("userAuth", userAuthNode);

    try {
      mapper.writerWithDefaultPrettyPrinter().writeValue(jsonFile, dataTree);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public void logoutUser(ObjectNode dataTree) {
    dataTree.set("userAuth", null);

    try {
      mapper.writerWithDefaultPrettyPrinter().writeValue(jsonFile, dataTree);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
