package com.mweb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import beans.copy.*;

class userDao {
    private Connection connection;

	public userDao() {
	        connection = DbConnect.getConnection();
	    }

	 public boolean loginUser(Userbin user) {
		 	try {
	            PreparedStatement preparedStatement = connection.
	            		prepareStatement("select * from users where login=? and password=?");
	            preparedStatement.setString(1, user.getLogin());
	            preparedStatement.setString(2, user.getPassword());
	            ResultSet rs = preparedStatement.executeQuery();
	            if (rs.next()) {
	            	return true;
	            }	            
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
		 	return false;
	    }

	 public boolean userReg(Userbin user) {
		 	try {
	            PreparedStatement preparedStatement = connection.
	            		prepareStatement("select * from users where login=?");
	            preparedStatement.setString(1, user.getLogin());
	            ResultSet rs = preparedStatement.executeQuery();
	            if (rs.next()) {
	            	return false;
	            }else {
	            PreparedStatement preparedStatement2 = connection.prepareStatement("insert into users(login, password, email, qposts) values (?, ?, ?, ? )");
	                preparedStatement2.setString(1, user.getLogin());
	                preparedStatement2.setString(2, user.getPassword());
	                preparedStatement2.setString(3, user.getEmail());
	                preparedStatement2.setInt(4, user.getPosts());
	                preparedStatement2.executeUpdate();
	                return true;
	            }
	        } catch (SQLException e) {
	        	 e.printStackTrace();
	        } 
		 	return true;
	 }
	 
	 public Userbin getUserbyid(String username) {
		 Userbin user = new Userbin(); 
		 	try {
	            PreparedStatement preparedStatement = connection.
	            		prepareStatement("select * from users where login=?");
	            preparedStatement.setString(1, username);
	            ResultSet rs = preparedStatement.executeQuery();
	            if (rs.next()) {
	            	user.setLogin(rs.getString("login"));
	            	user.setPassword(rs.getString("password"));
	            	user.setEmail(rs.getString("email"));
	            }	     
	            preparedStatement = connection.
	            		prepareStatement("select count(postid) from posts where user=?");
	            preparedStatement.setString(1, username);
	            ResultSet rs2 = preparedStatement.executeQuery();
	            rs2.next();
	            user.setPosts(Integer.parseInt(rs2.getString("count(postid)")));	           	 
	            } catch (SQLException e) {
	            e.printStackTrace();
	        }
		 	return user;
	    }

}
