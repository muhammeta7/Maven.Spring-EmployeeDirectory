package io.zipcoder.persistenceapp.controllers;

import io.zipcoder.persistenceapp.models.Employee;
import io.zipcoder.persistenceapp.services.EmployeeService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeControllerTest {

    @MockBean
    private EmployeeService service;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("GET /employees/1 - Found")
    void getEmployeeByIdFoundTest() throws Exception{
        // Set Mocked Service
        Employee mockEmp = new Employee( 1,"Moe", "Aydin", "boss", "765-3933", "moe@gmail.com", "2012-10-10",  3);
        doReturn(Optional.of(mockEmp)).when(service).findEmpById(1);

        // Execute get request
        mockMvc.perform(get("/API/employees/{id}", 1))

                // Validate response code and content type
                .andExpect(status().isOk())
                .andExpect(content().contentType((MediaType.APPLICATION_JSON)))

                // Validate returned fields
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.firstName", is("Moe")))
                .andExpect(jsonPath("$.lastName", is("Aydin")))
                .andExpect(jsonPath("$.title", is("boss")))
                .andExpect(jsonPath("$.phoneNumber", is("765-3933")))
                .andExpect(jsonPath("$.email", is("moe@gmail.com")))
                .andExpect(jsonPath("$.hireDate", is("2012-10-10")))
                .andExpect(jsonPath("$.departmentNumber", is(3)));
    }



}
