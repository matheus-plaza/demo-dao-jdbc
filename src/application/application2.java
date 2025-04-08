package application;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entites.Department;

public class application2 {

	public static void main(String[] args) {
		
		DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
		
		System.out.println("\n=== Teste 1: Department insert ===");
		Department newDepartment = new Department(null, "Test");
		departmentDao.insert(newDepartment);
		System.out.println("Inserted! New id = "+ newDepartment.getId());

	}

}
