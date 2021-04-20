import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;

public abstract class  AUser implements IUser{
  Connection connect;
  int session;
  String username;
  String password;


  //creates an account
  @Override
  public void createAccount(String username, String password) throws SQLException {
    String query = "{CALL add_user(?, ?)}";
    CallableStatement createStmt = connect.prepareCall(query);
    createStmt.setString(1, username);
    createStmt.setString(2, password);
    createStmt.executeQuery();
  }

  //starting the user session
  @Override
  public void startSession(String username) throws SQLException {
    CallableStatement addStmt = connect.prepareCall("{CALL add_user_session(?, ?)}");
    addStmt.setString(1, username);
    Date d1 =new Date(System.currentTimeMillis());
    addStmt.setDate(2, d1);

    addStmt.execute();
  }

  //makes sure the login username and password are correct.
  @Override
  public boolean correctLogInInformation(String username,String password) throws SQLException {
    CallableStatement correctStmt = connect.prepareCall("{? = CALL passwordMatches(?, ?)}");

    correctStmt.registerOutParameter(1, Types.BOOLEAN);
    correctStmt.setString(2, username);
    correctStmt.setString(3, password);

    correctStmt.execute();
    Boolean output = correctStmt.getBoolean(1);
    return output;
  }


  //Connects the program tothe vaccine distribution database

  @Override
  public void connect(String serverName, String password, String URL) {
    try{

      Connection con= DriverManager.getConnection (
          "jdbc:mysql://"
              + "127.0.0.1:3306" + "/" + "vaccine_distribution" + "?allowPublicKeyRetrieval=true&character"
              + "Encoding=UTF-8&useSSL=false",serverName,password);
    }catch(Exception e){ System.out.println(e);}
  }

  @Override
  public boolean userExists(String username) throws SQLException {
    CallableStatement existStmt = connect.prepareCall("{? = CALL userExists(?)}");
    existStmt.registerOutParameter(1, Types.BOOLEAN);
    existStmt.setString(2, username);
    existStmt.execute();
    Boolean output = existStmt.getBoolean(1);
    return output;
  }

  @Override
  public void logOut(int session) throws SQLException {
    String query = "{CALL update_user_session(?, ?)}";
    CallableStatement endStmt = connect.prepareCall(query);
    endStmt.setInt(1, session);
    Date d1 =new Date(System.currentTimeMillis());
    endStmt.setDate(2, d1);
    endStmt.execute();
    connect.close();
    System.exit(0);
  }

  @Override
  public int getCurrentSession(String username) throws SQLException {
    CallableStatement existStmt = connect.prepareCall("{? = CALL userExists(?)}");
    existStmt.registerOutParameter(1, Types.INTEGER);
    existStmt.setString(2, username);
    existStmt.execute();
    int currentSession = existStmt.getInt(1);
    return currentSession;
  }


}
