import Model.DBModel;
import View.UserAction;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;

public class Main {
  public static void main(String[] args) throws IOException {

//    DBModel dbModel = new DBModel();
//    ObjectNode jsonDataTree = dbModel.getJsonDataTree();
//
//    System.out.println("data 111 " + jsonDataTree);

    UserAction userAction = new UserAction();

    userAction.start();

  }
}