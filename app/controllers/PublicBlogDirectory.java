package controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

import models.Comment;
import models.Post;
import models.User;
import play.Logger;
import play.mvc.Controller;

public class PublicBlogDirectory extends Controller
{
  public static void directory()
  {
    ArrayList<User> BlogsUsers = new ArrayList<User>();

    List<User> users = User.findAll();
    for (User user : users)
    {
      if (user.posts.size() > 0)
      {
    	  BlogsUsers.add(user);
      }
    }
    render("PublicBlogDirecory/directory.html", BlogsUsers);
  }
}