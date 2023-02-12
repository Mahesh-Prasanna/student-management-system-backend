package lk.ijse.dep9.studentms.service;

import lk.ijse.dep9.studentms.dto.StudentDTO;
import lk.ijse.dep9.studentms.entity.Student;
import lk.ijse.dep9.studentms.repo.StudentRepo;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class StudentService {

    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private ModelMapper modelMapper;

    public String saveStudent(StudentDTO studentDTO){

        if (studentRepo.existsById(studentDTO.getId())) {
            return "00";
        }
        else {
            studentRepo.save(modelMapper.map(studentDTO, Student.class));
            return "01";
        }

    }

    public String updateStudent(StudentDTO studentDTO){
        if (studentRepo.existsById(studentDTO.getId())){
            studentRepo.save(modelMapper.map(studentDTO, Student.class));
            return "01";
        }
        else {
            return "00";
        }
    }
    public List<StudentDTO> viewAllStudents(){
         List<Student> studentList = studentRepo.findAll();
         return modelMapper.map(studentList, new TypeToken<ArrayList<StudentDTO>>(){
         }.getType());
    }

    public String deleteStudent(StudentDTO studentDTO){
        if (studentRepo.existsById(studentDTO.getId())){
            studentRepo.deleteById(studentDTO.getId());
            return "00";
        }
        else {
            return "01";
        }

    }

}
