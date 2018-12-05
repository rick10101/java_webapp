package com.mweb;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import beans.copy.*;
/** 
 * Контроллер пользователей. Получаем параметр action из jsp, 
 * в соответсвии с которым userDAO проверяет пользователя 
 * либо регистрирует, внеся данные в БД.
 * 
 */

@WebServlet("/userController")
public class userController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private userDao userdao;   
	private PostsDao postdao;   
	
    public userController() {
        super();
        userdao = new userDao();
        postdao = new PostsDao();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		if 	(action.equalsIgnoreCase("userpage")){
			   	Userbin user = userdao.getUserbyid(request.getParameter("username"));
	            request.setAttribute("user", user); 
	            RequestDispatcher view = request.getRequestDispatcher("userPage.jsp");
	            view.forward(request, response);  
	            return;
	       } else if (action.equalsIgnoreCase("exit")){   
        	HttpSession session = request.getSession();
        	session.setAttribute("User_login", null);  
        	response.sendRedirect("Welcome.jsp");        	
		}  		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
	    request.setCharacterEncoding("UTF-8"); 	
		Userbin user = new Userbin();
		if (request.getParameter("page").equalsIgnoreCase("login"))
		{
			user.setLogin(request.getParameter("Login"));
			user.setPassword(request.getParameter("Password"));
			if (userdao.loginUser(user)) {
				HttpSession session = request.getSession();
				session.setAttribute("User_login", user.getLogin());
				request.setAttribute("post", postdao.getAllPosts());  
	        	RequestDispatcher view = request.getRequestDispatcher("Main.jsp");
	            view.forward(request, response);	
				return;	            
			}else {
				request.setAttribute("alert", "Такого пользователя не существует");
				request.getRequestDispatcher("Login.jsp").forward(request, response);
				return;
			}
			
		}else if (request.getParameter("page").equalsIgnoreCase("sign")) 
		{
			user.setLogin(request.getParameter("Login"));
			user.setPassword(request.getParameter("Password"));
			if (request.getParameter("Email")==null || request.getParameter("Email").trim().isEmpty()) {
				user.setEmail("");
			}else {
				user.setEmail(request.getParameter("Email"));
			}
			user.setPosts(0);
			
			if (userdao.userReg(user)) {
				HttpSession session = request.getSession();
				session.setAttribute("User_login", user.getLogin());
				request.setAttribute("post", postdao.getAllPosts());  
	        	RequestDispatcher view = request.getRequestDispatcher("Main.jsp");
	            view.forward(request, response);
	            return;
			}else {
				request.setAttribute("alert", "Такой пользователь уже существует");
				request.getRequestDispatcher("Sign.jsp").forward(request, response);
				return;
			} 
		}	
	}
}


