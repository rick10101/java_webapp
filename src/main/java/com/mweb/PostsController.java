package com.mweb;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import beans.copy.*;
/** 
 * Контроллер постов и комментов. Получаем параметр action из jsp, 
 * в соответсвии с чем DAO делает либо выборку, либо вносит изменения в БД.
 * 
 */
@WebServlet("/PostsController")
public class PostsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PostsDao dao;   
	    
    public PostsController() {
        super();
        dao = new PostsDao();
        }

protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		if (session.getAttribute("User_login")==null) response.sendRedirect("Welcome.jsp");
		
		RequestDispatcher view;
		String action = request.getParameter("action");
		String forward="";
		PostBin post;
		int PostId, CommId;
		ServletContext context;
		
	if (action.equalsIgnoreCase("newpost")) {  
	context = request.getSession().getServletContext();
	context.setAttribute("post", null);
	response.sendRedirect("postInsert.jsp");
	return;
	} 
	
	switch (action) {
	    case "delete": 
	    	PostId = Integer.parseInt(request.getParameter("postId"));
            dao.deletePost(PostId);
            request.setAttribute("post", dao.getAllPosts());    
            forward = "Main.jsp";
            break;
        case "edit":  
	    	PostId = Integer.parseInt(request.getParameter("postId"));
            post = dao.editPost(PostId);
            request.setAttribute("post", post); 
            forward = "postInsert.jsp";
            break;
        case "postpage":  
	    	PostId = Integer.parseInt(request.getParameter("postId"));
            post = dao.getPostById(PostId);
            List<commentBin> comm=dao.getCommentsByPostId(PostId);
            request.setAttribute("comm", comm);
            request.setAttribute("post", post); 
            forward = "PostPage.jsp";   
            break;
	    case "deletecomm":  
	    	CommId = Integer.parseInt(request.getParameter("comid"));
		    PostId = Integer.parseInt(request.getParameter("id"));
            dao.deleteComm(CommId);
            request.setAttribute("post", dao.getPostById(PostId));
    	    request.setAttribute("comm", dao.getCommentsByPostId(PostId));  
    	    forward = "PostPage.jsp";
    	    break;
        case "refresh":   
        	request.setAttribute("post", dao.getAllPosts());  
        	forward = "Main.jsp";
            break;         	
	}
	
	view = request.getRequestDispatcher(forward);
	view.forward(request, response);
	return;	
		
}

protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
	response.setContentType("text/html; charset=UTF-8");
    request.setCharacterEncoding("UTF-8"); 
	if (request.getParameter("page").equalsIgnoreCase("comment"))
	{
		commentBin comm = new commentBin();
		comm.setAuthor(request.getParameter("author"));
		comm.setEmail(request.getParameter("email"));
		comm.setText(request.getParameter("text"));
		comm.setPost(Integer.parseInt(request.getParameter("id")));	
		dao.addComment(comm);
		response.setContentType("text/html; charset=UTF-8");
	    request.setCharacterEncoding("UTF-8"); 
	    ServletContext context = request.getSession().getServletContext();
	    context.setAttribute("post", dao.getPostById(comm.getPost()));
	    context.setAttribute("comm", dao.getCommentsByPostId(comm.getPost()));
	    response.sendRedirect("PostPage.jsp");
	    return;
	}else if (request.getParameter("page").equalsIgnoreCase("addpost"))
	{
	PostBin post = new PostBin();
    post.setTheme(request.getParameter("theme"));
    post.setText(request.getParameter("text"));
    post.setUser(request.getParameter("user"));
    post.setTags(request.getParameter("tags"));
    String postid = request.getParameter("id");
    if(postid == null || postid.isEmpty())
    {
        dao.addPost(post);
    }
    else
    {
    	post.setId(Integer.parseInt(request.getParameter("id")));
        dao.updatePost(post);
    }
    ServletContext context = request.getSession().getServletContext();
    context.setAttribute("post", dao.getAllPosts());
     response.sendRedirect("Main.jsp"); 	
    return;
	}
  }

}
