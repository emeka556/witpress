package controllers;

import java.util.List;
import models.Post;
import models.User;
import play.Logger;
import play.mvc.Controller;
import java.util.Collections;
import java.util.ArrayList;

public class Blog  extends Controller
{
  public static void index()
  {
    User user = Accounts.getCurrentUser();
    ArrayList<Post> reversePosts = new ArrayList<Post>(user.posts);
    Collections.reverse(reversePosts);
    render(user, reversePosts);
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