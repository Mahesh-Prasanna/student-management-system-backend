package lk.ijse.dep9.studentms.controller;

import lk.ijse.dep9.studentms.dto.ResponseDTO;
import lk.ijse.dep9.studentms.dto.StudentDTO;
import lk.ijse.dep9.studentms.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/student")
public class StudentController {

    @Autowired
    private StudentService studentService;
    private ResponseDTO responseDTO;

    @GetMapping(value = "/getstudent")
    public String getStudent(){
        return "GetStudent()";
    }

    @PostMapping(value = "/saveStudent")
    public ResponseEntity saveStudent(@RequestBody StudentDTO studentDTO){
        try{
            String res = studentService.saveStudent(studentDTO);
            if (res.equals("Duplicated")){
                responseDTO.setCode("00");
                responseDTO.setMessage("Duplcated");
                responseDTO.setContent(studentDTO);
                return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);
            }
            else if (res.equals("Saved")) {
                responseDTO.setCode("01");
                responseDTO.setMessage("Success");
                responseDTO.setContent(studentDTO);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
            }

            else {
                responseDTO.setCode("00");
                responseDTO.setMessage("Bad_Request");
                responseDTO.setContent(null);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);

            }
        }
        catch (Exception e){
            responseDTO.setCode("10");
            responseDTO.setMessage("Faled");
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
