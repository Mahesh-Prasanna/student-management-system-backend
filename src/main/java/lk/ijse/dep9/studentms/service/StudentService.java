package lk.ijse.dep9.studentms.service;

import lk.ijse.dep9.studentms.dto.StudentDTO;
import lk.ijse.dep9.studentms.entity.Student;
import lk.ijse.dep9.studentms.repo.StudentRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public class StudentService {

    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private ModelMapper modelMapper;

    public String saveStudent(StudentDTO studentDTO){

        if (studentRepo.existsById(studentDTO.getId())){
            return "Duplicated";
        }
        else {
            studentRepo.save(modelMapper.map(studentDTO, Student.class));
            return "Saved";
        }

    }

}
