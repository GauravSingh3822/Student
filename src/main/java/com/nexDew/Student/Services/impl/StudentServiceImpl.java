package com.nexDew.Student.Services.impl;

import com.nexDew.Student.Entity.Students;
import com.nexDew.Student.Repository.StudentRepository;
import com.nexDew.Student.Services.StudentServices;
import com.nexDew.Student.dto.AddStudentRequestDTO;
import com.nexDew.Student.dto.StudentDTO;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentServices {
    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<StudentDTO> getAllStudents() {
        List<Students> students = studentRepository.findAll();
//        List<StudentDTO> studentDTOList = students
//                .stream()
//                .map(students1 -> new StudentDTO(students1.getId(),students1.getName(),students1.getEmail(),students1.getPhoneNUmber()))
//                .toList();
//        return studentDTOList;
        return students.stream().map(student -> modelMapper.map(student, StudentDTO.class)).toList();

    }

    @Override
    public StudentDTO getById(Long id) {
        Students student = studentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Student not Find By Id : "+id));
        return modelMapper.map(student, StudentDTO.class);
    }

    @Override
    public StudentDTO createNewStduent(AddStudentRequestDTO addStudentRequestDTO) {
        // Mapping AddStudentRequestDTO to Students' Entity class -- add--> students;
        Students newStudent = modelMapper.map(addStudentRequestDTO, Students.class);
        Students student = studentRepository.save(newStudent);
        // Mapping student Entity to AddStudentRequestDTO class -- add--> students;
        return modelMapper.map(student, StudentDTO.class);
    }

    @Override
    public void deletStudentById(Long id) {
        if(!studentRepository.existsById(id)){
            throw  new IllegalArgumentException("Student does Not Exist by id "+id);
        }
        studentRepository.deleteById(id);
    }

    @Override
    public StudentDTO updateStudent(Long id, AddStudentRequestDTO addStudentRequestDTO) {
        Students student = studentRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Student not Find By Id :"+id));
        modelMapper.map(addStudentRequestDTO, student);
        student = studentRepository.save(student);
        return modelMapper.map(student, StudentDTO.class);

    }

    @Override
    public StudentDTO updatePartialStudent(Long id, Map<String, Object> updates) {
        Students student = studentRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Student not Find By Id :"+id));
        updates.forEach((field,value)->{
            switch(field){
                case "name":student.setName((String) value);
                break;
                case "email":student.setEmail((String) value);
                break;
                case "phoneNumber":student.setPhoneNUmber((String) value);
                break;
                default:
                    throw  new IllegalArgumentException("Field is Not Supported");
            }
        });
        Students savedstudent = studentRepository.save(student);
        return modelMapper.map(savedstudent, StudentDTO.class);
    }
}
