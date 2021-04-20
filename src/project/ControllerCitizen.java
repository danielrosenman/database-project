package project;

import java.sql.SQLException;
import java.util.Scanner;

public class ControllerCitizen implements IController {


  ControllerCitizen(){

  }

  @Override
  public void run(Scanner scan) throws SQLException {

    System.out.println("Please enter your social security number:");
    String ssn = scan.next();
    String username = "";

    Citizen citizen =new Citizen(ssn);



    System.out.println("Enter 1 to log in");
    System.out.println("Enter 2 to sign up");

    int numChoice1 = scan.nextInt();

    if(numChoice1 == 1){
      System.out.println("Please enter your username");
       username = scan.next();
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
       username = scan.next();

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


    while(true){
      System.out.println("press 1 for viewing all available appointment at a clinic");
      System.out.println("press 2 for viewing my appointment");
      System.out.println("press 3 to log out");

      int choice = scan.nextInt();

      if(choice == 1){
       citizen.viewAppointmentsAtClinic();
      }

      if(choice == 2){
        citizen.viewMyAppointments();
      }
      if(choice == 3){
        int session = citizen.getCurrentSession(username);
        citizen.logOut(session);
        System.out.println("Successfully logged Out!");
        break;
      }

      else{
        System.out.println("Invalid choice!");
      }


    }



  }
}
