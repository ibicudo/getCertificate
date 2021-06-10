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
public class CertificateServiceImplTest {

    @Autowired
    CertificateService certificateService;

    StudentDTO studentDTO;
    CertificateDTO certificateDTO;

    @BeforeEach
    void setUp(){
        this.studentDTO = new StudentDTO();
        this.certificateDTO = new CertificateDTO();
        SubjectDTO subjectDTOFirst= new SubjectDTO();
        SubjectDTO subjectDTOSecond= new SubjectDTO();
        List<SubjectDTO> subjects = new ArrayList<>();

        subjectDTOFirst.setSubject("matematica");
        subjectDTOFirst.setNote(8.0);
        subjects.add(subjectDTOFirst);
        subjectDTOSecond.setSubject("portugues");
        subjectDTOSecond.setNote(7.0);
        subjects.add(subjectDTOSecond);

        double avarage = (subjectDTOFirst.getNote()+subjectDTOSecond.getNote())/2;

        studentDTO.setName("Ingrid Monalisa");
        studentDTO.setSubjects(subjects);

        certificateDTO.setMessage("Parabens !!");
        certificateDTO.setStudent(studentDTO);
        certificateDTO.setAverage(avarage);

        certificateService= new CertificateServiceImpl();
    }

    @Test
    void shouldGetRightCertificate(){
        //arrange
        CertificateDTO certificateExpeted = this.certificateDTO;
        //act
        CertificateDTO certificatedCalculated = certificateService.analyzeNotes(this.studentDTO);
        //assert
        Assertions.assertEquals(certificateExpeted.getStudent().getName(), certificatedCalculated.getStudent().getName());
        Assertions.assertEquals(certificateExpeted.getAverage(), certificatedCalculated.getAverage());

    }

    @Test
    void shouldGetWrongCertificate(){
        //arrange
        CertificateDTO certificateExpeted = new CertificateDTO();
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setName("Maria");
        certificateExpeted.setAverage(10.0);
        certificateExpeted.setStudent(studentDTO);

        //act
        CertificateDTO certificatedCalculated = certificateService.analyzeNotes(this.studentDTO);
        //assert
        Assertions.assertNotEquals(certificateExpeted.getStudent().getName(), certificatedCalculated.getStudent().getName());
        Assertions.assertNotEquals(certificateExpeted.getAverage(), certificatedCalculated.getAverage());

    }

    @Test
    void shoulcalculateRightAvarage(){
        //arrange
        Double averageExpected = this.certificateDTO.getAverage();
        //act
        Double averageCalculated = certificateService.calculateAverage(this.studentDTO);
        //assert
        Assertions.assertEquals(averageExpected, averageCalculated);

    }

    @Test
    void shoulcalculateWrongAvarage(){
        //arrange
        Double expected= 8.0;
        //act
        Double averageCalculated = certificateService.calculateAverage(this.studentDTO);
        //assertNot
        Assertions.assertNotEquals(expected, averageCalculated);

    }

    @Test
    void shouldWriteRightDiploma(){
        //arrange
        Double average = this.certificateDTO.getAverage();
        String studentName = this.studentDTO.getName();
        String messageExpected = studentName + " usted ha conseguido el promedio de " + average;
        //act
        String  messageCalculated= certificateService.writeDiploma(this.studentDTO);
        //assert
        Assertions.assertEquals(messageExpected, messageCalculated);
    }

    @Test
    void shouldWriteWrongDiploma(){
        //arrange
        String messageExpected = "Joao" + " usted ha conseguido el promedio de " + "8.0";
        //act
        String  messageCalculated= certificateService.writeDiploma(this.studentDTO);
        //assert
        Assertions.assertNotEquals(messageExpected, messageCalculated);
    }

    @Test
    void shouldWriteRightMessageWithHonors(){
        //arrange
        List<SubjectDTO> subjectDTOS= this.studentDTO.getSubjects();
        SubjectDTO subjectDTOFirst = subjectDTOS.get(0);
        SubjectDTO subjectDTOSecond = subjectDTOS.get(1);

        subjectDTOFirst.setNote(10.0);
        subjectDTOS.set(0, subjectDTOFirst);
        subjectDTOSecond.setNote(9.0);
        subjectDTOS.set(1, subjectDTOSecond);

        this.studentDTO.setSubjects(subjectDTOS);
        double avarage = (subjectDTOFirst.getNote()+subjectDTOSecond.getNote())/2;
        this.certificateDTO.setAverage(avarage);

        String messageExpeted = "¡Felicitaciones " + this.studentDTO.getName() + "! Usted tiene el gran promedio de " + this.certificateDTO.getAverage();

        //act
        String messagemCalculated = certificateService.withHonors(this.certificateDTO.getAverage(), this.studentDTO.getName());

        Assertions.assertEquals(messageExpeted, messagemCalculated);
    }

    @Test
    void shouldWriteWrongMessageWithHonors(){
        //arrange
        List<SubjectDTO> subjectDTOS= this.studentDTO.getSubjects();
        SubjectDTO subjectDTOFirst = subjectDTOS.get(0);
        SubjectDTO subjectDTOSecond = subjectDTOS.get(1);

        subjectDTOFirst.setNote(10.0);
        subjectDTOS.set(0, subjectDTOFirst);
        subjectDTOSecond.setNote(9.0);
        subjectDTOS.set(1, subjectDTOSecond);

        this.studentDTO.setSubjects(subjectDTOS);
        double avarage = (subjectDTOFirst.getNote()+subjectDTOSecond.getNote())/2;
        this.certificateDTO.setAverage(avarage);

        String messageExpeted = "¡Felicitaciones " + "Maria" + "! Usted tiene el gran promedio de " + "10.0";

        //act
        String messagemCalculated = certificateService.withHonors(this.certificateDTO.getAverage(), this.studentDTO.getName());

        //assert
        Assertions.assertNotEquals(messageExpeted, messagemCalculated);
    }





}
