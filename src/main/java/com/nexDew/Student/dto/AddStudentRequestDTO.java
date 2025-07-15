package com.nexDew.Student.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddStudentRequestDTO {
    @NotBlank(message = "Name is Required")
    @Size(min = 3,max = 30,message = "Name should be of Length 0 to 30 characters")
    private String name;
    @Email
    @NotBlank(message = "Email is Required")
    private String email;
    private String phoneNumber;
}