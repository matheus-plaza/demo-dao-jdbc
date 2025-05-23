package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

import db.DB;
import db.DbException;
import model.dao.DepartmentDao;
import model.entites.Department;

public class DepartmentDaoJDBC implements DepartmentDao {
	private Connection conn;

	public DepartmentDaoJDBC(Connection conn) {
		this.conn = conn;
	}

//	PreparedStatement st = null;
//	ResultSet rs = null;
//	try {
//		st = conn.prepareStatement(
//				);
//		
//	}catch(SQLException e) {
//		throw new DbException(e.getMessage());
//	}finally {
//		DB.closeStatement(st);
//		DB.closeResultSet(rs);
//	}	
	
	@Override
	public void insert(Department obj) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"INSERT INTO department "
					+ "(Name) "
					+ "VALUES "
					+ "(?)",
					Statement.RETURN_GENERATED_KEYS);
			
			st.setString(1, obj.getName());
					
			int rowsAffected = st.executeUpdate();
			rs = st.getGeneratedKeys();
			
			if(rowsAffected > 0) {
				if(rs.next()) {
					obj.setId(rs.getInt(1));
				}
				System.out.println("Department inserted");
			}
			
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
		
	}

	@Override
	public void update(Department obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"update department "
					+"set name = ? "
					+"where id = ? ");
			
			st.setString(1, obj.getName());
			st.setInt(2, obj.getId());
			
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public void deleteById(Integer id) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"delete from department where id = ?");
			
			st.setInt(1, id);
			int rowsAffected = st.executeUpdate();
			
			if(rowsAffected > 0) {
				System.out.println("Department deleted!");
			}
			
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(st);
		}
	}
	
	private Department instantiateDepartmente(ResultSet rs) throws SQLException {
		Department dep = new Department();
		dep.setId(rs.getInt("Id"));
		dep.setName(rs.getNString("Name"));
		return dep;
	}
	
	@Override
	public Department findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"select * from department where id = ?");
			st.setInt(1, id);
			rs = st.executeQuery();
						
			if(rs.next()) {
				Department dep = instantiateDepartmente(rs);
				return dep;
			}
			return null;
			
			
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}	
	}

	@Override
	public List<Department> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT * from department");
			
			rs = st.executeQuery();
			List<Department> list = new ArrayList<>();
			
			while(rs.next()) {
				Department dep = instantiateDepartmente(rs);
				list.add(dep);
			}
			return list;
			
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

}
