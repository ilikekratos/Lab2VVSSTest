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
        assertEquals(1, service.saveStudent("100", "Alfonso", 314));
    }
    @Test
    public void testAddFail(){
        service.saveStudent("100", "Alfonso", 314);
        assertEquals(0, service.saveStudent("100", "Alfonso", 314));
    }

    @Test
    public void TC1_BBT_EC() {
        assertEquals(1, service.saveStudent("0", "Alfonso", 934));
    }

    @Test
    public void TC2_BBT_EC() {
        assertEquals(1, service.saveStudent(null, "Alfonso", 934));
    }

    @Test
    public void TC3_BBT_EC() {
        assertEquals(1, service.saveStudent("1", "Alfonso", 938));
    }

    @Test
    public void TC4_BBT_EC() {
        assertEquals(1, service.saveStudent("2", "Alfonso", 939));
    }

    @Test
    public void TC5_BBT_BVA() {
        assertEquals(1, service.saveStudent("3", "Alfonso", 111));
    }

    @Test
    public void TC6_BBT_BVA() {
        assertEquals(1, service.saveStudent("4", "Alfonso", 109));
    }

    @Test
    public void TC7_BBT_BVA() {
        assertEquals(1, service.saveStudent("5", "Alfonso", 937));

    }

    @Test
    public void TC8_BBT_BVA() {
        assertEquals(1, service.saveStudent("6", "Alfonso", 938));
    }

    @Test
    public void TC9_BBT_BVA() {
        assertEquals(1, service.saveStudent("7", "Alfonso", 400));

    }

    @Test
    public void TC10_BBT_BVA() {
        assertEquals(1, service.saveStudent("8", "Alfonso", -1));
    }

    @Test
    public void TC11_BBT_BVA() {
        assertEquals(1, service.saveStudent("9", "akljklfjsf", 123));
    }

    @Test
    public void TC12_BBT_BVA() {
        assertEquals(1, service.saveStudent("10", "", 123));
    }

    @Test
    public void TC13_BBT_BVA() {
        assertEquals( service.saveStudent("11", null, 124),1);
    }

}
