package it.naresh.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import it.naresh.Model.Student;

public interface StudentRepository  extends JpaRepository<Student, Long>{

	
	 Optional<Student> findByEmail(String email);
}
