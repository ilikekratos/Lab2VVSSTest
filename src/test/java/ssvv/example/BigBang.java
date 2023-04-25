package ssvv.example;

import org.junit.Before;
import org.junit.Test;
import ssvv.domain.Pair;
import ssvv.repository.NotaXMLRepository;
import ssvv.repository.StudentXMLRepository;
import ssvv.repository.TemaXMLRepository;
import ssvv.service.Service;
import ssvv.validation.NotaValidator;
import ssvv.validation.StudentValidator;
import ssvv.validation.TemaValidator;

import static org.junit.Assert.assertEquals;

public class BigBang {
    private Service service;

    @Before
    public void setUp() {
        StudentValidator vs = new StudentValidator();
        TemaValidator ts = new TemaValidator();
        NotaValidator ns = new NotaValidator();

        StudentXMLRepository studentXMLRepository = new StudentXMLRepository(vs, "src/test/java/studenti.xml");
        NotaXMLRepository notaXMLRepository = new NotaXMLRepository(ns, "src/test/java/note.xml");
        TemaXMLRepository temaXMLRepository = new TemaXMLRepository(ts, "src/test/java/teme.xml");

        service = new Service(studentXMLRepository, temaXMLRepository, notaXMLRepository);
    }

    @Test
    public void testAddStudent() {
        int result = service.saveStudent("1", "John Doe", 933);
        assertEquals(1, result);
        service.deleteStudent("1");
    }

    @Test
    public void testAddAssignment() {
        int result = service.saveTema("1", "Assignment 1", 3, 1);
        assertEquals(1, result);
        service.deleteTema("1");
    }

    @Test
    public void testAddGrade() {
        service.saveStudent("1", "John Doe", 933);
        service.saveTema("1", "Assignment 1", 3, 1);
        int result = service.saveNota("1", "1", 9.5, 3, "Good job!");
        assertEquals(1, result);
        service.deleteTema("1");
        service.deleteStudent("1");
        service.deleteNota(new Pair<>("1", "1"));
    }

    @Test
    public void testBigBangIntegration() {
        int addStudentResult = service.saveStudent("1", "John Doe", 933);
        int addAssignmentResult = service.saveTema("1", "Assignment 1", 3, 1);
        int addGradeResult = service.saveNota("1", "1", 9.5, 3, "Good job!");

        assertEquals(1, addStudentResult);
        assertEquals(1, addAssignmentResult);
        assertEquals(1, addGradeResult);

        service.deleteTema("1");
        service.deleteStudent("1");
        service.deleteNota(new Pair<>("1", "1"));
    }
}
