package com.digitalhouse.obtenerdiploma;

import com.digitalhouse.obtenerdiploma.dto.CertificateDTO;
import com.digitalhouse.obtenerdiploma.dto.StudentDTO;
import com.digitalhouse.obtenerdiploma.dto.SubjectDTO;
import com.digitalhouse.obtenerdiploma.service.CertificateService;
import com.digitalhouse.obtenerdiploma.service.CertificateServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class CertificateServiceTest {

    @Autowired
    CertificateService certificateService;

    StudentDTO studentDTO;
    CertificateDTO certificateDTO;

    @BeforeEach
    void setUp(){
        this.studentDTO = new StudentDTO();
        SubjectDTO subjectDTO1= new SubjectDTO();
        SubjectDTO subjectDTO2= new SubjectDTO();
        this.certificateDTO = new CertificateDTO();
        List<SubjectDTO> subjects = new ArrayList<>();

        subjectDTO1.setSubject("matematica");
        subjectDTO1.setNote(8.0);
        subjects.add(subjectDTO1);
        subjectDTO2.setSubject("portugues");
        subjectDTO2.setNote(7.0);
        subjects.add(subjectDTO1);

        studentDTO.setName("Ingrid Monalisa");
        studentDTO.setSubjects(subjects);

        certificateDTO.setMessage("Parabens !!");
        certificateDTO.setStudent(studentDTO);

        certificateService= new CertificateServiceImpl();
    }

    @Test
    void shoulcalculateRightAvarage(){

        Double averageCalculated = certificateDTO.getAverage();
        Double averageExpected = certificateService.calculateAverage(this.studentDTO);

        Assertions.assertEquals(averageExpected, averageCalculated);

    }

    @Test
    void shoulcalculateWrongAvarage(){

        Double averageExpected = certificateService.calculateAverage(this.studentDTO);

        Assertions.assertEquals(averageExpected, 7);

    }
}
