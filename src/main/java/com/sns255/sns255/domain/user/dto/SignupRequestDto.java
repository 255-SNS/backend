package com.sns255.sns255.domain.user.dto;

public class SignupRequestDto {
    private final String name;
    private final int studentId;
    private final String anonymousName;
    private final String email;
    private final String password;
    private final String department;

    public SignupRequestDto(String name, int studentId, String anonymousName, String email, String password, String department) {
        this.name = name;
        this.studentId = studentId;
        this.anonymousName = anonymousName;
        this.email = email;
        this.password = password;
        this.department = department;
    }

    public String getName() {
        return name;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getAnonymousName() {
        return anonymousName;
    }

    public String getEmail() {
        return email;
    }

    public int getPassword() {
        return password;
    }

    public String getDepartment() {
        return department;
    }
}