package controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

import models.Comment;

import models.Post;
import models.User;
import play.Logger;
import play.mvc.Controller;

public class PublicBlog  extends Controller
{

  public static void displayBlogs(Long id)
  {     
	User user = User.findById(id);
    Logger.info("Request to show blogs for user id: " + id);
    
    List<Post> reversePosts  = new ArrayList<Post> () ;

    reversePosts = (user.posts);

    User logged_in_user = null;
    if (session.contains("logged_in_userid"))
    {
      String userId = session.get("logged_in_userid");
      logged_in_user = User.findById(Long.parseLong(userId));
    }
    render(user, logged_in_user, reversePosts);
  } 

    public static void recentComment(Long postid, Long userID, Long logged_in_user, String content)
    {    
      Logger.info("Post ID = " + postid);
      Post post = Post.findById(postid);
      Comment comment = new Comment(content);

      post.addComment(comment);
      post.save();
      displayBlogs(userID);
      
          } 
}