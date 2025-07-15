package com.nexDew.Student.controllers;

import com.nexDew.Student.Services.StudentServices;
import com.nexDew.Student.dto.AddStudentRequestDTO;
import com.nexDew.Student.dto.StudentDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/Students")
@RequiredArgsConstructor
public class studentControllers {

    private final StudentServices studentServices;

     /*1. Get All Students -------- @GetMapping("/allStudents")
     2. Get Student By ID --------- @GetMapping("/{id}")
     3. Create Students ----------- @PostMapping("/create)
     4. Delete students By id ----- @DeleteMapping
     5. Update Particular Data ---- @Patch("/{id}")
     6. Uodate whole Data --------- @Update

      */

    @GetMapping("/allStudents")
    public ResponseEntity<List<StudentDTO>> getAllStudents() {
        return ResponseEntity.status(HttpStatus.OK).body(studentServices.getAllStudents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> getStudentBYID(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.FOUND).body(studentServices.getById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<StudentDTO> createNewStudent(@RequestBody @Valid AddStudentRequestDTO addStudentRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(studentServices.createNewStduent(addStudentRequestDTO));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudentById(@PathVariable Long id){
        studentServices.deletStudentById(id);
      return ResponseEntity.noContent().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<StudentDTO> updateStudent(@PathVariable Long id,@RequestBody @Valid AddStudentRequestDTO addStudentRequestDTO){
        return ResponseEntity.status(HttpStatus.OK).body(studentServices.updateStudent(id,addStudentRequestDTO));
    }
    @PatchMapping("/{id}")
    public ResponseEntity<StudentDTO> updatePartialStudent(@PathVariable Long id, @RequestBody Map<String,Object> updates){
        return ResponseEntity.status(HttpStatus.OK).body(studentServices.updatePartialStudent(id,updates));
    }

}
