package View;

import Controller.AuthController;
import Shared.ColorPrinter;
import org.w3c.dom.ls.LSOutput;

import java.util.Scanner;

public class UserAction {

  static Scanner scanner = new Scanner(System.in);

  AuthController authController = new AuthController();

  public void start() {
//    while(true) {
      userAuthMenu();
//    }
  }

  private void userAuthMenu() {
    ColorPrinter.println(ColorPrinter.Color.GREEN, "Добро пожаловать. Выберите действие:");
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
//        handleLogin();
        break;
      case "3":
        authController.logout();
        System.out.println("Приложение завершило свою работу!");
        System.exit(0);
        break;
      default:
        System.out.println("Неверный выбор. Попробуйте снова.");
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
}
