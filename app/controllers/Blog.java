package controllers;

import java.util.List;
import models.Post;
import models.User;
import play.Logger;
import play.mvc.Before;
import play.mvc.Controller;
import java.util.Collections;
import java.util.ArrayList;

public class Blog  extends Controller
{
	@Before
	  static void checkAuthentification()
	  {
	    if(session.contains("logged_in_userid") == false)
	      Accounts.login();
	  }
	
  public static void index()
  {
    User user = Accounts.getCurrentUser();
    List<Post> reversePosts = new ArrayList<Post>(user.posts);
    
    //Logger.info("test", reversePosts.get(0).content);
    Collections.reverse(reversePosts);
    render("Blog/home.html" , user, reversePosts);
  }

  public static void recentPost(String title, String content)
  {
    User user = Accounts.getCurrentUser();

    Post post = new Post (title, content);
    user.addPost(post);
    user.save();

    Logger.info ("title of Blog:" + title + " content of Blog:" + content);
    index();
  }

  public static void deletePost(Long postid)
  {    
    User user = Accounts.getCurrentUser(); 

    Post post = Post.findById(postid);
    user.posts.remove(post);
    user.save();
    post.delete();

    index();
  }
}