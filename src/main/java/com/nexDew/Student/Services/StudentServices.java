package com.nexDew.Student.Services;

import com.nexDew.Student.dto.AddStudentRequestDTO;
import com.nexDew.Student.dto.StudentDTO;

import java.util.List;
import java.util.Map;

public interface StudentServices {

    List<StudentDTO> getAllStudents();

    StudentDTO getById(Long id);

    StudentDTO createNewStduent(AddStudentRequestDTO addStudentRequestDTO);

    void deletStudentById(Long id);

    StudentDTO updateStudent(Long id, AddStudentRequestDTO addStudentRequestDTO);

    StudentDTO updatePartialStudent(Long id, Map<String, Object> updates);
}
