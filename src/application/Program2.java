package application;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entites.Department;

public class Program2 {

	public static void main(String[] args) {
		
		DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
//		
//		System.out.println("\n=== Teste 1: Department insert ===");
//		Department newDepartment = new Department(null, "RH");
//		departmentDao.insert(newDepartment);
//		System.out.println("Inserted! New id = "+ newDepartment.getId());

//		System.out.println("\n=== Teste 2: Department update ===");
//		newDepartment.setName("Test2");
//		departmentDao.update(newDepartment);
//		System.out.println("id = " + newDepartment.getId()+ " , name - " + newDepartment.getName());
//		
		System.out.println("\n=== Teste 3: Department delete ===");
		departmentDao.deleteById(11);
	}

}
