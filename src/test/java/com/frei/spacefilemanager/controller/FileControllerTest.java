package com.frei.spacefilemanager.controller;

import com.frei.spacefilemanager.service.FileService;
import com.frei.spacefilemanager.entities.FileDetail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;


@WebMvcTest(FileController.class)
public class FileControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FileService fileService;

    @BeforeEach
    void setUp() {
        FileDetail detail = new FileDetail("test-file.txt", 12345L, new Date());
        Mockito.when(fileService.listFileDetails()).thenReturn(Arrays.asList(detail));
    }

    @Test
    void listFileDetails_returnsCorrectResponse() throws Exception {
        mockMvc.perform(get("/files/details").with(user("user").password("password").roles("USER")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("test-file.txt"))
                .andExpect(jsonPath("$[0].size").value(12345));
    }
}
