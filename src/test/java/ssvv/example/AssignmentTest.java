package ssvv.example;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import domain.Nota;
import domain.Student;
import domain.Tema;
import repository.NotaXMLRepository;
import repository.StudentXMLRepository;
import repository.TemaXMLRepository;
import service.Service;
import validation.NotaValidator;
import validation.StudentValidator;
import validation.TemaValidator;
import validation.Validator;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;

public class AssignmentTest {

    private Service service;

    @Before
    public void setUp() {
        Validator<Student> vs = new StudentValidator();
        Validator<Nota> ns = new NotaValidator();
        Validator<Tema> ts = new TemaValidator();

        StudentXMLRepository studentXMLRepository = new StudentXMLRepository(vs, "src/test/java/testing/studenti.xml");
        NotaXMLRepository notaXMLRepository = new NotaXMLRepository(ns, "src/test/java/testing/note.xml");
        TemaXMLRepository temaXMLRepository = new TemaXMLRepository(ts, "src/test/java/testing/teme.xml");
        service = new Service(studentXMLRepository, temaXMLRepository, notaXMLRepository);

    }

    @After
    public void tearDown() {
        try {
            String defaultFileContent = new String(Files.readAllBytes(Paths.get("src/test/java/testing/defaultFile.xml")), StandardCharsets.UTF_8);

            PrintWriter printWriter = new PrintWriter("src/test/java/testing/teme.xml");

            printWriter.print(defaultFileContent);
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
