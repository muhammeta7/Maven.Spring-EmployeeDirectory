package io.zipcoder.persistenceapp.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.zipcoder.persistenceapp.models.Employee;
import io.zipcoder.persistenceapp.services.EmployeeService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
    @DisplayName("GET /API/employees/1 - Found")
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

    @Test
    @DisplayName("GET  /API/employees/1 - Not Found")
    void testGetEmployeeByIdNotFound() throws Exception {
        //Setup our mocked service
        doReturn(Optional.empty()).when(service).findEmpById(1);

        // Execute the Get request
        mockMvc.perform(get("/API/employees/{id}", 1))

                //Validate that we get a 404 Not Found response
                .andExpect(status().isNotFound());
    }


    @Test
    @DisplayName("POST /API/employees/create - Success")
    void createEmployeeTest() throws Exception {
        // Setup our mocked service
        Employee postEmp = new Employee("Moe", "Aydin", "boss","765-3933", "moe@gmail.com", "2012-10-10",  3);
        Employee mockEmp = new Employee( 1,"Moe", "Aydin", "boss", "765-3933", "moe@gmail.com", "2012-10-10",  3);
        doReturn(mockEmp).when(service).create(any());

        // Execute the Post Request
        mockMvc.perform(post("/API/employees/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(postEmp)))

                // Validate the response code and content type
                .andExpect((status().isCreated()))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))

                // Validate headers
                .andExpect(header().string(HttpHeaders.LOCATION, "/API/employees/create/1"))

                // Validate headers
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.firstName", is("Moe")))
                .andExpect(jsonPath("$.lastName", is("Aydin")))
                .andExpect(jsonPath("$.title", is("boss")))
                .andExpect(jsonPath("$.phoneNumber", is("765-3933")))
                .andExpect(jsonPath("$.email", is("moe@gmail.com")))
                .andExpect(jsonPath("$.hireDate", is("2012-10-10")))
                .andExpect(jsonPath("$.departmentNumber", is(3)));
    }

    @Test
    @DisplayName("PUT /API/employees/updateFirtName/1 - Success")
    public void updateFirstNameTest() throws Exception{
        // Setup our mocked service
        Employee putEmp = new Employee("Jack", "Aydin", "boss","765-3933", "moe@gmail.com", "2012-10-10",  3);
        Employee mockEmp = new Employee( 1,"Moe", "Aydin", "boss", "765-3933", "moe@gmail.com", "2012-10-10",  3);
        doReturn(Optional.of(mockEmp)).when(service).findEmpById(1);
        doReturn(mockEmp).when(service).create(any());

        // Execute the PUT Request
        mockMvc.perform(put("/API/employees/updateFirstName/{id}",1)
                .contentType(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.IF_MATCH,1)
                .param("firstName","Jack")
                .content(asJsonString(putEmp)))

                // Validates that we get a 200 response
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))

                // Validate Headers
                .andExpect(header().string(HttpHeaders.LOCATION, "/API/employees/updateFirstName/1"))

                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.firstName", is("Jack")));
    }

    @Test
    @DisplayName("DELETE /API/employees/updateFirtName/1 - Success")
    public void deleteTest() throws Exception{
        // Setup mock employee
        Employee mockEmp = new Employee( 1,"Moe", "Aydin", "boss", "765-3933", "moe@gmail.com", "2012-10-10",  3);

        // Setup mocked service
        doReturn(Optional.of(mockEmp)).when(service).findEmpById(1);
        doReturn(true).when(service).delete(1);

        // Execute DELETE request
        mockMvc.perform(delete("/API/employees/remove/{id}", 1))
                .andExpect(status().isOk());
    }

    static String asJsonString(final Object obj){
        try{
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

}
