package com.mweb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;
import java.sql.ResultSet;
import beans.copy.*;

class PostsDao {
    private Connection connection;

	public PostsDao() {
	        connection = DbConnect.getConnection();
	    }
		
	
    public void deletePost(int PostId) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("delete from posts where postid=?");
            preparedStatement.setInt(1, PostId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public PostBin editPost(int PostId) {
    	   PostBin post = new PostBin();
    	        try {
    	            PreparedStatement preparedStatement = connection.
    	            		prepareStatement("select * from posts where postid=?");
    	            preparedStatement.setInt(1, PostId);
    	            ResultSet rs = preparedStatement.executeQuery();

    	            if (rs.next()) {
    	            	post.setId(rs.getInt("postid"));
    	                post.setTheme(rs.getString("theme"));
    	                post.setText(rs.getString("text"));
    	                post.setUser(rs.getString("user"));
    	                post.setTags(rs.getString("tags"));
    	            }
    	        } catch (SQLException e) {
    	            e.printStackTrace();
    	        }
    	        return post;
    }
        
    public List<PostBin> getAllPosts() {
        List<PostBin> posts = new ArrayList<PostBin>();
        try {
        	Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from posts");
            while (rs.next()) {
            	PostBin post = new PostBin();
                post.setId(rs.getInt("postid"));
                post.setTheme(rs.getString("theme"));
                post.setText(rs.getString("text"));
                post.setUser(rs.getString("user"));
                post.setTags(rs.getString("tags"));
                posts.add(post);
            }
        	}catch (SQLException e) {
            e.printStackTrace();
        }

        return posts;
    }

    public void updatePost(PostBin post) {
        try {
            PreparedStatement preparedStatement = connection
            		.prepareStatement("update posts set theme=?, text=?, user=?, tags=? where postid=?");
            preparedStatement.setString(1, post.getTheme());
            preparedStatement.setString(2, post.getText());
            preparedStatement.setString(3, post.getUser());
            preparedStatement.setString(4, post.getTags());
            preparedStatement.setInt(5, post.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void addPost(PostBin post) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("insert into posts(theme, text, user, tags) values (?, ?, ?, ? )");
            preparedStatement.setString(1, post.getTheme());
            preparedStatement.setString(2, post.getText());
            preparedStatement.setString(3, post.getUser());
            preparedStatement.setString(4, post.getTags());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public PostBin getPostById(int postId) {
        PostBin post = new PostBin();
        try {
            PreparedStatement preparedStatement = connection.
            		prepareStatement("select * from posts where postid=?");
            preparedStatement.setInt(1, postId);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
            	post.setId(rs.getInt("postid"));
                post.setTheme(rs.getString("theme"));
                post.setText(rs.getString("text"));
                post.setUser(rs.getString("user"));
                post.setTags(rs.getString("tags"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return post;
    }
    
    public void addComment(commentBin comm) {
        try {
        	PreparedStatement preparedStatement = connection
            		.prepareStatement("insert into comments(author, email, text, post) values (?, ?, ?, ? )");
            preparedStatement.setString(1, comm.getAuthor());
            preparedStatement.setString(2, comm.getEmail());
            preparedStatement.setString(3, comm.getText());
            preparedStatement.setInt(4, comm.getPost());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<commentBin> getCommentsByPostId(int postId) {
        List<commentBin> comm = new ArrayList<commentBin>();
        try {
        	PreparedStatement preparedStatement = connection.
        			prepareStatement("select * from comments where post=?");
            preparedStatement.setInt(1, postId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
            	commentBin comment = new commentBin();
            	comment.setId(rs.getInt("id"));
            	comment.setAuthor(rs.getString("author"));
            	comment.setEmail(rs.getString("email"));
            	comment.setText(rs.getString("text"));
            	comment.setPost(rs.getInt("post"));
                comm.add(comment);
            }
        	}catch (SQLException e) {
            e.printStackTrace();
        }

        return comm;
    }
  
    public void deleteComm(int CommId) {
        try {
            PreparedStatement preparedStatement = connection
            		.prepareStatement("delete from comments where id=?");
            preparedStatement.setInt(1, CommId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}