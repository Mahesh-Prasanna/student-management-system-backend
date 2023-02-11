package lk.ijse.dep9.studentms.service;

import lk.ijse.dep9.studentms.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;

public class StudentService {

    @Autowired
    private StudentRepo studentRepo;
}
