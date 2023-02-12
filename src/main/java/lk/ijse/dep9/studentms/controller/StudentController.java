package lk.ijse.dep9.studentms.controller;

import lk.ijse.dep9.studentms.dto.ResponseDTO;
import lk.ijse.dep9.studentms.dto.StudentDTO;
import lk.ijse.dep9.studentms.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/api/v1/student")
public class StudentController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private ResponseDTO responseDTO;

    @GetMapping(value = "/getstudent")
    public String getStudent(){
        return "GetStudent()";
    }

    @PostMapping(value = "/saveStudent")
    public ResponseEntity saveStudent(@RequestBody StudentDTO studentDTO){
        try{
            String res = studentService.saveStudent(studentDTO);
            if (res.equals("00")){
                responseDTO.setCode("00");
                responseDTO.setMessage("Duplcated");
                responseDTO.setContent(studentDTO);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
            }
            else if (res.equals("01")) {
                responseDTO.setCode("01");
                responseDTO.setMessage("Success");
                responseDTO.setContent(studentDTO);
                return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);
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
            responseDTO.setContent(e.getMessage());
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping(value = "/updateStudent")
    public ResponseEntity updateStudent(@RequestBody StudentDTO studentDTO){
        try{
            String res = studentService.updateStudent(studentDTO);
            if (res.equals("01")){
                responseDTO.setCode("00");
                responseDTO.setMessage("Updated");
                responseDTO.setContent(studentDTO);
                return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);
            }
            else {
                responseDTO.setCode("01");
                responseDTO.setMessage("Updated Failed");
                responseDTO.setContent(null);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
            }
        }
        catch (Exception e){
            responseDTO.setCode("02");
            responseDTO.setMessage("FAiled");
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @GetMapping(value = "/getAllStudents")
    public ResponseEntity viewAllStudents(){
        try {
            List<StudentDTO> studentDTOList = studentService.viewAllStudents();
            responseDTO.setCode("02");
            responseDTO.setMessage("Success");
            responseDTO.setContent(studentDTOList);
            return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);

        }
        catch (Exception e){
            responseDTO.setCode("02");
            responseDTO.setMessage(e.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @DeleteMapping(value = "/deleteStudent")
    public ResponseEntity deleteStudent(@RequestBody StudentDTO studentDTO){
        try {
            if (studentService.deleteStudent(studentDTO).equals("00")){
                responseDTO.setCode("00");
                responseDTO.setMessage("Deleted");
                responseDTO.setContent(studentDTO);
                return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);
            }
            else {
                responseDTO.setCode("01");
                responseDTO.setMessage("Not Found");
                responseDTO.setContent(studentDTO);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
            }

        }
        catch (Exception e){
            responseDTO.setCode("01");
            responseDTO.setMessage(e.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
