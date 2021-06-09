package com.digitalhouse.obtenerdiploma.dto;

import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.*;

@Validated
public class SubjectDTO {

  @NotNull(message = "The subject name is required")
  @Size(min = 8, max = 50, message = "The subject name must be of length 8 and 50")
  private String subject;

  @NotNull(message = "The subject note is required")
  @DecimalMax(value = "10", message = "A nota máxima é 10")
  @Digits(integer = 2, fraction = 2, message = "Máximo 2 inteiros e 2 casas decimais")
  private Double note;

  public String getSubject() {
    return subject;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  public Double getNote() {
    return note;
  }

  public void setNote(Double note) {
    this.note = note;
  }

}
