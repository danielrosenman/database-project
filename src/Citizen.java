import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public final class Citizen extends AUser {

  Connection connect;
  
  Citizen(){
    super();
  }

  //not finished will fix tomorrow
  public void viewAppointmentsAtClinic(String clinic) throws SQLException {
    ArrayList<String> characterNames = new ArrayList<>();
    Statement stmt1 = connect.createStatement();
    ResultSet rs= stmt1.executeQuery("select character_name from lotr_character");


    System.out.println("Character names: ");

    while(rs.next()) {
      String str = rs.getString(1);
      System.out.println(str);
      characterNames.add(str);
    }

  }


  public static void main(String[] args) throws SQLException {
  }
  }
