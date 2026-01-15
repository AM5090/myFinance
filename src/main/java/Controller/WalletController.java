package Controller;


import Model.DBModel;
import Model.WalletModel;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class WalletController {

  DBModel dbModel = new DBModel();
  WalletModel walletModel = new WalletModel();

//    public void getUserWallet() {
//      walletModel.getUserWallet();
//      walletModel.getUserBudget();
//      walletModel.getUserExpenses();
//      walletModel.getUserIncome();
//    }

  public ArrayNode getUserIncome() {
    ObjectNode dataTree = dbModel.getJsonDataTree();
    return walletModel.getUserIncome(dataTree);
  }

  public ArrayNode getUserExpenses() {
    ObjectNode dataTree = dbModel.getJsonDataTree();
    return walletModel.getUserExpenses(dataTree);
  }

  public void addNewIncome(String incomeCategory, String incomeAmount) {
    walletModel.addNewIncome(incomeCategory, incomeAmount);
  }

  public void addNewExpenses(String expensesCategory, String expensesAmount) {
    walletModel.addNewExpenses(expensesCategory, expensesAmount);
  }

  public void addNewBudget() {
    walletModel.addNewBudget();
  }
}
