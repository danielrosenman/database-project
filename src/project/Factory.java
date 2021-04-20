package project;

public class Factory {



  Factory(){

  }

  static IController createController(String type) {
    if (type.equals("citizen")) {
      return new ControllerCitizen();
    } else if (type.equals("clinic staff")) {
      return new ControllerClinic();
    } else if (type.equals("government official")) {
      return new ControllerGov();
    } else if (type.equals("vaccine provider")) {
      return new ControllerDistributor();
    } else {
      throw new IllegalArgumentException("unknown user type");
    }
  }

}
