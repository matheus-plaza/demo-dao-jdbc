package application;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entites.Department;
import model.entites.Seller;

public class Program {

	public static void main(String[] args) {
		
		SellerDao sellerDao = DaoFactory.createSellerDao();
		
		
		System.out.println("=== Teste 1: seller findById ===");
		Seller seller = sellerDao.findById(3);
		System.out.println(seller);
		
		System.out.println("\n=== Teste 2: seller findByDepartment ===");
		Department department = new Department(2, null);
		List<Seller> list = sellerDao.findByDepartment(department);
		for(Seller obj : list) {
			System.out.println(obj);
		}
		System.out.println("\n=== Teste 3: seller findAll ===");
		list = sellerDao.findAll();
		for(Seller obj : list) {
			System.out.println(obj);
		}

//		System.out.println("\n=== Teste 4: seller insert ===");
//		Seller newSeller = new Seller(null, "Greg", "greg@gmail.com", new Date(), 4000.0, department);
//		sellerDao.insert(newSeller);
//		System.out.println("Inserted! New id = "+ newSeller.getId());
		
		System.out.println("\n=== Teste 5: seller Update ===");
		seller = sellerDao.findById(1);
		seller.setName("Martha waine");
		sellerDao.update(seller);
		System.out.println(seller);
		
		System.out.println("\n=== Teste 5: seller delete ===");
		sellerDao.deleteById(10);

	}

}
