package com.jspiders.manytomanybi.dao;



import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.jspiders.manytomanybi.dto.CourseDTO;
import com.jspiders.manytomanybi.dto.StudentDTO;

public class StudentDAO3 {
	private static EntityManagerFactory entityManagerFactory;
	private static EntityManager entityManager;
	private static EntityTransaction entityTransaction;

public static void main(String[] args) {
	openConnection();
	entityTransaction.begin();
	StudentDTO student=entityManager.find(StudentDTO.class, 2);
	CourseDTO course=entityManager.find(CourseDTO.class, 3);
	student.getCourses().remove(course);
    course.getStudent().remove(student);
    if(course.getStudent().isEmpty()) {
    	entityManager.remove(course);
    }
	if(student.getCourses().isEmpty()) {
		entityManager.remove(student);
	}
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
