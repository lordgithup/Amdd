package it.naresh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.naresh.Model.Student;
import it.naresh.service.IStudentService;

@CrossOrigin("http://localhost:3000")         
@RestController
@RequestMapping("/students")
public class StudentController {
  
  @Autowired
  private IStudentService studentService;
 
  @GetMapping
  public ResponseEntity<List<Student>> getStudents(){
	 return new ResponseEntity<>(studentService.getStudents(),HttpStatus.FOUND);
  }
	
  @PostMapping
  public Student addStudent(@RequestBody Student student) {
	  return studentService.addStudent(student);
  }
	
  @PutMapping("/update/{id}")
  public Student updatestudent(@RequestBody Student student,@PathVariable Long id) {
	  return studentService.updateStudent(student, id);
  }
  
  @DeleteMapping("/delete/{id}")
  public void deleteStudent(@PathVariable Long id) {
	  studentService.deleteStudent(id);
  }
  
  @GetMapping("/student/{id}")
  public Student getStudentById(@PathVariable Long id) {
	  return studentService.getStudentId(id);
  }
}
