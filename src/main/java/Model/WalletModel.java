package Model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;

public class WalletModel {
  DBModel dbModel = new DBModel();


  public ObjectNode getUserWallet(ObjectNode dataTree) {
    return dbModel.getUserWallet(dataTree);
  }

  public ArrayNode getUserBudget(ObjectNode dataTree) {
    ObjectNode wallet = getUserWallet(dataTree);
    ArrayNode budgets = wallet.withArray("budgets");
    System.out.println("budgets >>> " + budgets);
    return budgets;
  }

  public ArrayNode getUserExpenses(ObjectNode dataTree) {
    ObjectNode wallet = getUserWallet(dataTree);
    ArrayNode expenses = wallet.withArray("expenses");
    System.out.println("expenses >>> " + expenses);
    return expenses;
  }

  public ArrayNode getUserIncome(ObjectNode dataTree) {
    ObjectNode wallet = getUserWallet(dataTree);
    return wallet.withArray("income");
  }

  public void addNewIncome(String incomeCategory, String incomeAmount) {
    ObjectNode jsonDataTree = dbModel.getJsonDataTree();
    ObjectMapper mapper = dbModel.getMapper();
    File jsonFile = dbModel.getJsonFile();

    ArrayNode income = getUserIncome(jsonDataTree);

    LinkedHashMap<String, String> newIncome = new LinkedHashMap<>();
    newIncome.put("category", incomeCategory);
    newIncome.put("size", incomeAmount);

    ObjectNode newIncomeObject = mapper.valueToTree(newIncome);
    income.add(newIncomeObject);

    try {
      mapper.writerWithDefaultPrettyPrinter().writeValue(jsonFile, jsonDataTree);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public void addNewExpenses(String expensesCategory, String expensesAmount) {
    ObjectNode jsonDataTree = dbModel.getJsonDataTree();
    ObjectMapper mapper = dbModel.getMapper();
    File jsonFile = dbModel.getJsonFile();

    ArrayNode expenses = getUserExpenses(jsonDataTree);

    LinkedHashMap<String, String> newExpenses = new LinkedHashMap<>();
    newExpenses.put("category", expensesCategory);
    newExpenses.put("size", expensesAmount);

    ObjectNode newExpensesObject = mapper.valueToTree(newExpenses);
    expenses.add(newExpensesObject);

    try {
      mapper.writerWithDefaultPrettyPrinter().writeValue(jsonFile, jsonDataTree);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public void addNewBudget() {

  }

}
