package ssvv.example;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import ssvv.domain.Nota;
import ssvv.domain.Student;
import ssvv.domain.Tema;
import org.junit.Test;
import ssvv.repository.NotaXMLRepository;
import ssvv.repository.StudentXMLRepository;
import ssvv.repository.TemaXMLRepository;
import ssvv.service.Service;
import ssvv.validation.NotaValidator;
import ssvv.validation.StudentValidator;
import ssvv.validation.TemaValidator;
import ssvv.validation.Validator;

/**
 * Unit test for simple App.
 */
public class BigBang
{
    /**
     * Rigorous Test :-)
     */
    Validator<Student> studentValidator = new StudentValidator();
    Validator<Tema> temaValidator = new TemaValidator();
    Validator<Nota> notaValidator = new NotaValidator();

    StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "studenti.xml");
    TemaXMLRepository fileRepository2 = new TemaXMLRepository(temaValidator, "teme.xml");
    NotaXMLRepository fileRepository3 = new NotaXMLRepository(notaValidator, "note.xml");

    Service service = new Service(fileRepository1, fileRepository2, fileRepository3);




}
