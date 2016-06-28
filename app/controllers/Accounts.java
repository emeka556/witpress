package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

public class Accounts extends Controller
{

  public static void login()
  {
    render();
  }

  public static void Signup()
  {
    render();
  }

  
/**
 * clears session info logged
 */
  public static void Logout()
  {
    session.clear();
    login();
    
  }
/**
 *method to save user information
 *firstname, lastname, email and password in database
 */
  public static void register(String firstName, String lastName, String email, String password)
  {
    Logger.info(firstName + " " + lastName + " " + email + " " + password);
    User user = new User(firstName, lastName, email, password);
    user.save();
    login();
    
    

  }
  
  public static void authenticate(String email, String password)
  {
    Logger.info("Attempting to authenticate with " + email + ":" + password);
    User user = User.findByEmail(email);
    if ((user != null) && (user.checkPassword(password) == true))
    {
      Logger.info("Successful authentication of  " + user.firstName + " " + user.lastName + " ");
      session.put("logged_in_userid", user.id);
      login();
    }
    else
    {
      Logger.info("Authentication failed");
      login();
    }
  }

  public static User getCurrentUser()
  {
    String userId = session.get("logged_in_userid");
    if (userId == null)
    {
      return null;
    }
    User logged_in_user = User.findById(Long.parseLong(userId));
    Logger.info("Logged in User: " + logged_in_user.firstName);
    return logged_in_user;
  }

}
