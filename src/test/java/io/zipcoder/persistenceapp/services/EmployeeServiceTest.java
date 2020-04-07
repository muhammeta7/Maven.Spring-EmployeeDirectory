package io.zipcoder.persistenceapp.services;


import io.zipcoder.persistenceapp.models.Employee;
import io.zipcoder.persistenceapp.repositories.EmployeeRepository;
import io.zipcoder.persistenceapp.services.EmployeeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeServiceTest {

    @Autowired
    private EmployeeService service;

    @MockBean
    private EmployeeRepository repo;

    @Test
    @DisplayName("Test findbyId Success")
    public void findByIdTestSuccessTest(){
        // Set Up mock object and repository
        Employee mockEmp = new Employee( 1,"Moe", "Aydin", "boss", "765-3933", "moe@gmail.com", "2012-10-10",  3);
        doReturn(Optional.of(mockEmp)).when(repo).findById(1);

        // Execute Call
        Optional<Employee> returnEmployee = service.findEmpById(1);

        // Check Assertions
        Assertions.assertTrue(returnEmployee.isPresent(), "No Employee was found");
        Assertions.assertSame(returnEmployee.get(), mockEmp, "Models don't match");
    }

    @Test
    @DisplayName("Test findById Fail")
    public void findByIdFailTest(){
        // Set up mock repo
        doReturn(Optional.empty()).when(repo).findById(1);
        // Execute Call
        Optional<Employee> returnEmployee = service.findEmpById(1);
        // Check assertions
        Assertions.assertFalse(returnEmployee.isPresent(), "Car found when it shouldnt be");

    }

    @Test@DisplayName("Test findAll")
    public  void testFindAll(){
        // Setup mock objects and repo
        Employee mockEmp1 = new Employee( 1,"Moe", "Aydin", "boss", "765-3933", "moe@gmail.com", "2012-10-10",  3);
        Employee mockEmp2 = new Employee( 2,"Moe", "Aydin", "boss", "765-3933", "moe@gmail.com", "2012-10-10",  3);

        doReturn(Arrays.asList(mockEmp1, mockEmp2)).when(repo).findAll();

        // Execute the service call
        List<Employee> returnListEmp = service.findAllEmployees();

        // Check assertions
        Assertions.assertEquals(2, returnListEmp.size(), "findAll should return 2 products");
    }

    @Test
    @DisplayName("Test save product")
    public void createTest(){
        Employee mockEmp = new Employee( 1,"Moe", "Aydin", "boss", "765-3933", "moe@gmail.com", "2012-10-10",  3);
        doReturn(mockEmp).when(repo).save(any());

        Employee returnEmp = service.create(mockEmp);

        Assertions.assertNotNull(returnEmp, "The saved product should not be null");
    }

}
