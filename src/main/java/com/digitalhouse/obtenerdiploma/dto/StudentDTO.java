package com.digitalhouse.obtenerdiploma.dto;

import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Validated
public class StudentDTO {

    @NotNull(message = "The student name is required")
    @Size(min = 8, max = 50, message = "The student name must be of length between 8 and 50")
    private String name;

    @Valid
    @NotNull(message = "The subject list is required")
    @Size(min = 1, message = "The subject list must have at least 1 element")
    private List<SubjectDTO> subjects;

    public StudentDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SubjectDTO> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<SubjectDTO> subjects) {
        this.subjects = subjects;
    }

}
