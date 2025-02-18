package it.naresh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.naresh.Exception.StudentAlreadyExistsException;
import it.naresh.Exception.StudentNotFoundException;
import it.naresh.Model.Student;
import it.naresh.repository.StudentRepository;

@Service
public class StudentService implements IStudentService{

	@Autowired
	private  StudentRepository studentRepository;
	
	@Override
	public Student addStudent(Student student) {
		 if(studentAlreadyExists(student.getEmail())) {
			 throw new StudentAlreadyExistsException(student.getEmail()+ "already Exits");
		 }
		return studentRepository.save(student);
	}

	private boolean studentAlreadyExists(String email) {
		return studentRepository.findByEmail(email).isPresent();
	}

	@Override
	public List<Student> getStudents() {
		// TODO Auto-generated method stub
		return studentRepository.findAll();
	}

	@Override
	public Student updateStudent(Student student, Long id) {
		// TODO Auto-generated method stub
		return studentRepository.findById(id).map(st -> {
			st.setFirstName(student.getFirstName());
			st.setLastName(student.getLastName());
			st.setEmail(student.getEmail());
			st.setDepartment(student.getDepartment());
			return studentRepository.save(st);
		}).orElseThrow(() -> new StudentNotFoundException("sorry, this student could not be found"));
		
	}

	@Override
	public Student getStudentId(Long id) {
		 return studentRepository.findById(id)
				 .orElseThrow(() -> new StudentNotFoundException("Sorry, no student found with id " +id));
		
	}

	@Override
	public void deleteStudent(Long id) {
		
		if(!studentRepository.existsById(id)) {
		throw new StudentNotFoundException("Sorry, no student found with id " +id);
			
	}
                studentRepository.deleteById(id);
	}
}
