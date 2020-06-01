package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.Blog;
import utility.ConnectionManager;

public class BlogDaoImpl implements BlogDaoInterface {

	@Override
	public void insertBlog(Blog blog) throws SQLException, ClassNotFoundException, IOException {
		Connection con=ConnectionManager.getConnection();
		String sql="INSERT INTO BLOG(ID,NAME,DESCRIPTION,POSTED_ON)VALUES(?,?,?,?)";
		PreparedStatement smt=con.prepareStatement(sql);
		smt.setLong(1, blog.getBlogId());
		smt.setString(2, blog.getBlogTitle());
		smt.setString(3, blog.getBlogDescription());
		smt.setDate(4, java.sql.Date.valueOf(blog.getPostedOn()));
		smt.executeUpdate();
		con.close();
	}

	@Override
	public Blog selectBlog(int blogid) {
		Blog blog=null;
		try {
			Connection con=ConnectionManager.getConnection();
			PreparedStatement smt=con.prepareStatement("SELECT ID,NAME,DESCRIPTION, POSTED_ON FROM BLOG WHERE ID=?");
			smt.setInt(1, blogid);
			ResultSet rs=smt.executeQuery();
			
			while(rs.next()) {
				int id=rs.getInt("ID");
				String name=rs.getString("NAME");
				String desc=rs.getString("DESCRIPTION");
				LocalDate date=rs.getDate("POSTED_ON").toLocalDate();
				
				blog=new Blog();
				blog.setBlogId(id);
				blog.setBlogTitle(name);
				blog.setBlogDescription(desc);
				blog.setPostedOn(date);
			}
			
			return blog;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public List<Blog> selectAllBlogs() {
		Connection con=null;
		List<Blog> list=new ArrayList<Blog>();
		try {
			con=ConnectionManager.getConnection();
			Statement smt=con.createStatement();
			ResultSet rs=smt.executeQuery("SELECT * FROM BLOG");
			
			while(rs.next()) {
				int id=rs.getInt(1);
				String name=rs.getString(2);
				String desc=rs.getString(3);
				Date d=rs.getDate(4);
				LocalDate postedOn=rs.getDate("POSTED_ON").toLocalDate();
				Blog blog=new Blog();
				blog.setBlogId(id);
				blog.setBlogTitle(name);
				blog.setBlogDescription(desc);
				blog.setPostedOn(postedOn);
				
				list.add(blog);
				
			}
			return list;
		} catch (SQLException | ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public boolean deleteBlog(int id) throws SQLException {
		boolean del;
		try {
			Connection con=ConnectionManager.getConnection();
			PreparedStatement smt=con.prepareStatement("DELETE FROM BLOG WHERE ID=?");
			smt.setInt(1, id);
			
			del=smt.executeUpdate()>0;
			
			return del;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean updateBlog(Blog blog) throws SQLException, Exception {
		boolean update = false;
		try {
			Connection con=ConnectionManager.getConnection();
			PreparedStatement smt=con.prepareStatement("UPDATE BLOG SET NAME=?, DESCRIPTION=?, POSTED_ON=? WHERE ID=?");
			smt.setString(1,blog.getBlogTitle());
			smt.setString(2, blog.getBlogDescription());
			smt.setDate(3, java.sql.Date.valueOf(blog.getPostedOn()));
			smt.setInt(4, blog.getBlogId());
			
			update=smt.executeUpdate()>0;
			
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return update;
	}
	
}