package View;

import Controller.AuthController;
import Controller.WalletController;
import Shared.ColorPrinter;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.w3c.dom.ls.LSOutput;

import java.util.Scanner;

public class UserAction {

  static Scanner scanner = new Scanner(System.in);

  AuthController authController = new AuthController();
  WalletController walletController = new WalletController();

  public void start() {
    ColorPrinter.println(ColorPrinter.Color.GREEN, "Добро пожаловать!");

    while(true) {
      if (!authController.userAuth()) {
        userAuthMenu();
      } else {
        financeMenu();
      }
    }
  }

  private void userAuthMenu() {
    ColorPrinter.println(ColorPrinter.Color.GREEN, "Авторизация. Выберите действие:");
    ColorPrinter.println(ColorPrinter.Color.YELLOW, "1. Регистрация");
    ColorPrinter.println(ColorPrinter.Color.YELLOW, "2. Вход");
    ColorPrinter.println(ColorPrinter.Color.YELLOW, "3. Выход");
    ColorPrinter.print(ColorPrinter.Color.GREEN, "> ");
    authMenuAction();
  }

  private void authMenuAction() {
    String choice = scanner.nextLine().trim();

    switch (choice) {
      case "1":
        handleRegistration();
        break;
      case "2":
        handleLogin();
        break;
      case "3":
        handleLogout(true);
        break;
      default:
        ColorPrinter.println(ColorPrinter.Color.RED, "Такого пункта меню не предусмотрено. Выберите другое действие:");
    }
  }

  private void handleRegistration() {
    try {
      ColorPrinter.println(ColorPrinter.Color.GREEN, "Укажите Ваше имя:");
      ColorPrinter.print(ColorPrinter.Color.GREEN, "> ");
      String name = scanner.nextLine().trim();

      ColorPrinter.println(ColorPrinter.Color.GREEN, "Укажите пароль:");
      ColorPrinter.print(ColorPrinter.Color.GREEN, "> ");
      String password = scanner.nextLine().trim();

      boolean registrationStatus = authController.registration(name, password);
      if (registrationStatus) {
        ColorPrinter.println(ColorPrinter.Color.GREEN, "Регистрация успешна!");
      } else {
        ColorPrinter.println(ColorPrinter.Color.RED, "Такой пользователь уже существует!");
      }
    } catch (IllegalArgumentException e) {
      System.out.println("Ошибка регистрации: " + e.getMessage());
    }
  }

  private void handleLogin() {
    try {
      ColorPrinter.println(ColorPrinter.Color.GREEN, "Укажите Ваше имя:");
      ColorPrinter.print(ColorPrinter.Color.GREEN, "> ");
      String name = scanner.nextLine().trim();

      ColorPrinter.println(ColorPrinter.Color.GREEN, "Укажите пароль:");
      ColorPrinter.print(ColorPrinter.Color.GREEN, "> ");
      String password = scanner.nextLine().trim();

      int loginStatus = authController.login(name, password);
      if (loginStatus == 0) {
        ColorPrinter.println(ColorPrinter.Color.GREEN, "Успешная авторизация!");
      } else if (loginStatus == 1) {
        ColorPrinter.println(ColorPrinter.Color.RED, "Не верный логин или пароль!");
      } else {
        ColorPrinter.println(ColorPrinter.Color.RED, "Такого пользователя не существует!");
      }
    } catch (IllegalArgumentException e) {
      System.out.println("Ошибка авторизации: " + e.getMessage());
    }
  }

  private void financeMenu() {
    ColorPrinter.println(ColorPrinter.Color.GREEN, "Операция с финансами. Выберите действие:");
    ColorPrinter.println(ColorPrinter.Color.YELLOW, "1. Добавить доход");
    ColorPrinter.println(ColorPrinter.Color.YELLOW, "2. Добавить расход");
    ColorPrinter.println(ColorPrinter.Color.YELLOW, "3. Установить бюджет");
    ColorPrinter.println(ColorPrinter.Color.YELLOW, "4. Просмотр статистики");
    ColorPrinter.println(ColorPrinter.Color.YELLOW, "5. Выход");
    ColorPrinter.print(ColorPrinter.Color.GREEN, "> ");
    financeMenuAction();
  }


  private void financeMenuAction() {
    String choice = scanner.nextLine().trim();

    switch (choice) {
      case "1":
        handleAddNewIncome();
        break;
      case "2":
        handleAddNewExpenses();
        break;
      case "3":
        System.out.println(choice);
        break;
      case "4":
        System.out.println(choice);
        break;
      case "5":
        handleLogout(false);
        break;
      default:
        ColorPrinter.println(ColorPrinter.Color.RED, "Такого пункта меню не предусмотрено. Выберите другое действие:");
    }

  }

  private void handleAddNewIncome() {
    try {
      ColorPrinter.println(ColorPrinter.Color.GREEN, "Укажите категорию дохода:");
      ColorPrinter.print(ColorPrinter.Color.GREEN, "> ");
      String category = scanner.nextLine().trim();

      ColorPrinter.println(ColorPrinter.Color.GREEN, "Укажите сумму дохода:");
      ColorPrinter.print(ColorPrinter.Color.GREEN, "> ");
      double amount = Double.parseDouble(scanner.nextLine().trim());

      walletController.addNewIncome(category, String.valueOf(amount));
      ArrayNode allIncomes = walletController.getUserIncome();

      ColorPrinter.println(ColorPrinter.Color.GREEN, "Доход успешно добавлен!");
      showWalletData(allIncomes, "Список всех доходов:");

    } catch (NumberFormatException e) {
      ColorPrinter.println(ColorPrinter.Color.RED, "Требуется ввести числовое значение!");
    } catch (IllegalArgumentException e) {
      ColorPrinter.println(ColorPrinter.Color.RED, "Ошибка: " + e.getMessage());
    }
  }

  private void handleAddNewExpenses() {
    try {
      ColorPrinter.println(ColorPrinter.Color.GREEN, "Укажите категорию расхода:");
      ColorPrinter.print(ColorPrinter.Color.GREEN, "> ");
      String category = scanner.nextLine().trim();

      ColorPrinter.println(ColorPrinter.Color.GREEN, "Укажите сумму расхода:");
      ColorPrinter.print(ColorPrinter.Color.GREEN, "> ");
      double amount = Double.parseDouble(scanner.nextLine().trim());

      walletController.addNewExpenses(category, String.valueOf(amount));
      ArrayNode allExpenses = walletController.getUserExpenses();
      ColorPrinter.println(ColorPrinter.Color.GREEN, "Расход успешно добавлен!");
      showWalletData(allExpenses, "Список всех расходов:");

    } catch (NumberFormatException e) {
      ColorPrinter.println(ColorPrinter.Color.RED, "Требуется ввести числовое значение!");
    } catch (IllegalArgumentException e) {
      ColorPrinter.println(ColorPrinter.Color.RED, "Ошибка: " + e.getMessage());
    }
  }

  private void showWalletData(ArrayNode dataArray, String title) {
    ColorPrinter.println(ColorPrinter.Color.GREEN, title);
    for (JsonNode data : dataArray) {
      ColorPrinter.print(ColorPrinter.Color.WHITE, "Категория: " );
      ColorPrinter.print(ColorPrinter.Color.YELLOW, data.get("category").asText() + " ");
      ColorPrinter.print(ColorPrinter.Color.WHITE, "Сумма: " );

      JsonNode factField = data.get("fact");

      if (factField != null && !factField.asText().isEmpty()) {
        ColorPrinter.print(ColorPrinter.Color.YELLOW, data.get("size").asText() + " ");
        ColorPrinter.print(ColorPrinter.Color.WHITE, "Остаток суммы: " );
        ColorPrinter.println(ColorPrinter.Color.YELLOW, factField.asText());
      } else {
        ColorPrinter.println(ColorPrinter.Color.YELLOW, data.get("size").asText() + " ");
      }
    }
  }

  private void handleLogout(boolean fullExit) {
    authController.logout();
    if (fullExit) {
      ColorPrinter.println(ColorPrinter.Color.GREEN, "Приложение завершило свою работу!");
      System.exit(0);
    }
  }
}
