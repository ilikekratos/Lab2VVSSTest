package ssvv.example;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import ssvv.domain.Pair;
import ssvv.repository.NotaXMLRepository;
import ssvv.repository.StudentXMLRepository;
import ssvv.repository.TemaXMLRepository;
import ssvv.service.Service;
import ssvv.validation.NotaValidator;
import ssvv.validation.StudentValidator;
import ssvv.validation.TemaValidator;

import static org.junit.Assert.assertEquals;

public class ServiceIncrementalIntegrationTest {
    private Service service;

    @Before
    public void setUp() {
        StudentValidator studentValidator = new StudentValidator();
        TemaValidator temaValidator = new TemaValidator();
        NotaValidator notaValidator = new NotaValidator();

        StudentXMLRepository studentXMLRepository = Mockito.mock(StudentXMLRepository.class);
        TemaXMLRepository temaXMLRepository = Mockito.mock(TemaXMLRepository.class);
        NotaXMLRepository notaXMLRepository = Mockito.mock(NotaXMLRepository.class);

        service = new Service(studentXMLRepository, temaXMLRepository, notaXMLRepository);
    }

    @Test
    public void testAddStudent() {
        int result = service.saveStudent("1", "John Doe", 933);
        assertEquals(1, result);
        service.deleteStudent("1");
    }

    @Test
    public void testAddStudentAndAssignment() {
        int addStudentResult = service.saveStudent("1", "John Doe", 933);
        int addAssignmentResult = service.saveTema("1", "Assignment 1", 3, 1);

        assertEquals(1, addStudentResult);
        assertEquals(1, addAssignmentResult);

        service.deleteTema("1");
        service.deleteStudent("1");
    }

    @Test
    public void testAddStudentAssignmentAndGrade() {
        int addStudentResult = service.saveStudent("1", "John Doe", 933);
        int addAssignmentResult = service.saveTema("1", "Assignment 1", 3, 1);
        int addGradeResult = service.saveNota("1", "1", 9.5, 3, "Good job!");

        System.out.println(service.findAllStudents());
        System.out.println(service.findAllTeme());
        System.out.println(service.findAllStudents());

        assertEquals(1, addStudentResult);
        assertEquals(1, addAssignmentResult);
        assertEquals(-1, addGradeResult);

        service.deleteNota(new Pair<>("1", "1"));
        service.deleteTema("1");
        service.deleteStudent("1");
    }
}
