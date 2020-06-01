package dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import model.Blog;

public interface BlogDaoInterface {
	
		void insertBlog(Blog blog) throws SQLException, ClassNotFoundException, IOException;
		Blog selectBlog(int blogid);
	List<Blog> selectAllBlogs();
		boolean deleteBlog(int id) throws SQLException;
		boolean updateBlog(Blog blog) throws SQLException, Exception;
	
}
