package controllers;

import models.Post;
import models.User;
import models.Comment;
import play.Logger;
import play.mvc.Before;
import play.mvc.Controller;

public class BlogPost extends Controller
{
	@Before
	  static void checkAuthentification()
	  {
	    if(session.contains("logged_in_userid") == false)
	      Accounts.login();
	  }
  public static void display(Long postid)
  {
    Logger.info("Post ID = " + postid);
    Post post = Post.findById(postid);
    render ("BlogPost/display.html", post);
  }

  public static void recentComment(Long postid, String content)
  {    
    Logger.info("Post ID = " + postid);
    Post post = Post.findById(postid);
    Comment comment = new Comment(content);

    post.addComment(comment);
    post.save();
    display(postid);
  }

  public static void deleteComment(Long postid, Long commentid)
  {    
    Logger.info("Post ID = " + postid);
    Comment comment = Comment.findById(commentid);
    Post post = Post.findById(postid);
    post.comments.remove(comment);
    post.save();
    display(postid);
  }
}