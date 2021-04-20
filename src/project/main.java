package project;

import java.sql.SQLException;
import java.util.Scanner;

public class main {


  public static void main(String[] args) throws SQLException {


    System.out.println("please select your user type");
    Scanner scan = new Scanner(System.in);
    String type = "";

    while (true) {
      System.out.println("press 1 for Private Citizen");
      System.out.println("press 2 for Clinic Staff Member");
      System.out.println("press 3 for Government Official");
      System.out.println("press 4 for Vaccine Distributor");

      int numChoice = scan.nextInt();
      switch (numChoice) {
        case 1:
          type = "citizen";
          break;
        case 2:
          type = "clinic staff";
          break;
        case 3:
          type = "government official";
          break;
        case 4:
          type = "vaccine provider";
          break;
        default:
          type = "";
      }

      if (!type.equals("")) {
        break;
      }
      System.out.println("Invalid option entered!");

    }

    IController controller = Factory.createController(type);
    controller.run(scan);


  }
}




    /*
    AUser citizen =new Citizen();
    Scanner scan = new Scanner(System.in);

    System.out.println("Enter 1 to log in");
    System.out.println("Enter 2 to sign up");

    int numChoice1 = scan.nextInt();

    if(numChoice1 == 1){
      System.out.println("Please enter your username");
      String username = scan.next();
      System.out.println("Please enter your password");
      String password = scan.next();

      while(!citizen.correctLogInInformation(username,password)){
        System.out.println("username or password are incorrect!");
        System.out.println("Please renter your username");
        username = scan.next();
        System.out.println("Please renter your password");
        password = scan.next();
      }

      System.out.println("Successfuly logged in");
      citizen.startSession(username);
    }

    if(numChoice1 == 2){
      System.out.println("Please enter your desired your username");
      String username = scan.next();

      while(citizen.userExists(username)){
        System.out.println("The username you selected alreasy exists!");
        System.out.println("Please enter a new username");
        username = scan.next();
      }

      System.out.println("Please enter your desired your password");
      String password = scan.next();

      citizen.createAccount(username,password,"citizen");
      citizen.startSession(username);

      System.out.println("Successfuly logged in");

    }


  }
  */

