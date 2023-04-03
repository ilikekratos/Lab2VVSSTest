package ssvv.example;

import static org.junit.Assert.assertTrue;

import console.UI;
import domain.Nota;
import domain.Student;
import domain.Tema;
import org.junit.Test;
import repository.NotaXMLRepository;
import repository.StudentXMLRepository;
import repository.TemaXMLRepository;
import service.Service;
import validation.NotaValidator;
import validation.StudentValidator;
import validation.TemaValidator;
import validation.Validator;

/**
 * Unit test for simple App.
 */
public class AppTest 
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
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void testAddSuccess(){
        service.deleteStudent("100");
        assertTrue(service.saveStudent("100","Alfonso",314)==1);
    }
    @Test
    public void testAddFail(){
        assertTrue(service.saveStudent("100","Alfonso",314)==0);
    }

    @Test
    public void TC1_BBT_EC() {
        assertTrue(service.saveStudent("0", "Alfonso", 934)== 1);
    }

    @Test
    public void TC2_BBT_EC() {
        assertTrue(service.saveStudent(null, "Alfonso", 934)== 0);
    }

    @Test
    public void TC3_BBT_EC() {
        assertTrue(service.saveStudent("1", "Alfonso", 938)== 0);
    }

    @Test
    public void TC4_BBT_EC() {
        assertTrue(service.saveStudent("2", "Alfonso", 939)== 0);
    }

    @Test
    public void TC5_BBT_BVA() {
        assertTrue(service.saveStudent("3", "Alfonso", 111)== 1);
    }

    @Test
    public void TC6_BBT_BVA() {
        assertTrue(service.saveStudent("4", "Alfonso", 109)== 0);
    }

    @Test
    public void TC7_BBT_BVA() {
        assertTrue(service.saveStudent("5", "Alfonso", 937)== 1);

    }

    @Test
    public void TC8_BBT_BVA() {
        assertTrue(service.saveStudent("6", "Alfonso", 938)== 0);
    }

    @Test
    public void TC9_BBT_BVA() {
        assertTrue(service.saveStudent("7", "Alfonso", 400)== 1);

    }

    @Test
    public void TC10_BBT_BVA() {
        assertTrue(service.saveStudent("8", "Alfonso", -1)== 0);
    }

    @Test
    public void TC11_BBT_BVA() {
        assertTrue(service.saveStudent("9", "akljklfjsf", 123)== 1);
    }

    @Test
    public void TC12_BBT_BVA() {
        assertTrue(service.saveStudent("10", "", 123)== 0);
    }

    @Test
    public void TC13_BBT_BVA() {
        assertTrue(service.saveStudent("11", null, 124)== 0);
    }

}
