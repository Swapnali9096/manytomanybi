package com.jspiders.manytomanybi.dao;


import java.util.Arrays;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.jspiders.manytomanybi.dto.CourseDTO;
import com.jspiders.manytomanybi.dto.StudentDTO;

public class StudentDAO2 {
	private static EntityManagerFactory entityManagerFactory;
	private static EntityManager entityManager;
	private static EntityTransaction entityTransaction;

public static void main(String[] args) {
	openConnection();
	entityTransaction.begin();
	StudentDTO student=entityManager.find(StudentDTO.class, 2);
	student.setName("Vijay");
	student.setEmail("vijay@gmail.com");
	student.setMobile(9878675645l);
	
	CourseDTO course=entityManager.find(CourseDTO.class, 1);
	course.setName("MYSQL");
	course.setFees(25000.00);
	entityManager.persist(course);
	entityTransaction.commit();
	closeConnection();
}
	private static void openConnection() {
		entityManagerFactory=Persistence.createEntityManagerFactory("student");
		entityManager=entityManagerFactory.createEntityManager();
		entityTransaction=entityManager.getTransaction();
	}
	private static void closeConnection() {
		if(entityManagerFactory!=null) {
			entityManagerFactory.close();
		}
		if(entityManager!=null) {
			entityManager.close();
		}
		if(entityTransaction!=null) {
			if(entityTransaction.isActive()) {
				entityTransaction.rollback();
			}
		}
	}


}
