package ssvv.example;

import org.junit.Before;
import org.junit.Test;
import ssvv.domain.Nota;
import ssvv.domain.Student;
import ssvv.domain.Tema;
import ssvv.repository.NotaXMLRepository;
import ssvv.repository.StudentXMLRepository;
import ssvv.repository.TemaXMLRepository;
import ssvv.service.Service;
import ssvv.validation.NotaValidator;
import ssvv.validation.StudentValidator;
import ssvv.validation.TemaValidator;
import ssvv.validation.Validator;

import static org.junit.Assert.assertEquals;

public class AssignmentTest {

    private Service service;

    @Before
    public void setUp() {
        Validator<Student> vs = new StudentValidator();
        Validator<Nota> ns = new NotaValidator();
        Validator<Tema> ts = new TemaValidator();

        StudentXMLRepository studentXMLRepository = new StudentXMLRepository(vs, "src/test/java/studenti.xml");
        NotaXMLRepository notaXMLRepository = new NotaXMLRepository(ns, "src/test/java/note.xml");
        TemaXMLRepository temaXMLRepository = new TemaXMLRepository(ts, "src/test/java/teme.xml");
        service = new Service(studentXMLRepository, temaXMLRepository, notaXMLRepository);

    }


    @Test
    public void testAddAssignmentSuccess() {
        assertEquals(service.saveTema("1", "Instalati docker fratilor", 2, 1), 1);
    }

    @Test
    public void testAddAssignmentFailure() {
        // Non unique id failure
        assertEquals(service.saveTema("7", "Instalati si Jnekins", 3, 2), 1);
        assertEquals(service.saveTema("7", "Ba m-am razgandit nu mai instalati",3 , 2), 0);
    }
}
