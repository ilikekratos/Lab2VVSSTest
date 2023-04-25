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
public class WhiteBoxTest
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


    @Test
    public void testAddSuccess(){
        assertEquals(1, service.saveTema("169", "Tema SSVV", 14, 2));
        service.deleteTema("169");
    }
    @Test
    public void testAddFail(){
        service.saveTema("169", "Tema SSVV", 14, 2);
        assertEquals(0, service.saveTema("169", "Tema SSVV", 14, 2));
        service.deleteTema("169");
    }
    @Test
    public void testAddFailValidator1(){
        assertEquals(1, service.saveTema("", "Tema SSVV", 14, 2));
        service.deleteTema("");
    }
    @Test
    public void testAddFailValidator12(){
        assertEquals(1, service.saveTema("169", "", 14, 2));
        service.deleteTema("169");
    }
    @Test
    public void testAddFailValidator3(){
        service.saveTema("169", "Tema SSVV", 15, 0);
        assertEquals(1, service.saveTema("169", "Tema SSVV", 14, 2));
        service.deleteTema("169");
    }
    @Test
    public void testAddFailValidator32(){
        service.saveTema("169", "Tema SSVV", 13, 0);
        assertEquals(1, service.saveTema("169", "Tema SSVV", 14, 2));
        service.deleteTema("169");
    }
    @Test
    public void testAddFailValidator4(){
        service.saveTema("169", "Tema SSVV", 2, 4);
        assertEquals(1, service.saveTema("169", "Tema SSVV", 14, 2));
        service.deleteTema("169");
    }

}
