package com.wps.poc.ged.simpleged.web.rest;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.wps.poc.ged.simpleged.model.SGDocument;
import com.wps.poc.ged.simpleged.service.SGDocumentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = FileUploadController.class, secure = false)
public class FileUploadControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private SGDocumentService sgDocumentService;

    @Test
    public void shouldUploadFileWithSuccess() throws Exception {
        SGDocument sgDocument = new SGDocument();
        sgDocument.setId(UUID.randomUUID().toString());
        sgDocument.setName("test.txt");
        MockMultipartFile multipartFile = new MockMultipartFile("file", "test.txt", "text/plain", "Ceci est un fichier de test".getBytes());
        BDDMockito.given(sgDocumentService.create(Mockito.any(SGDocument.class), Mockito.any(MockMultipartFile.class))).willReturn(sgDocument);
        mockMvc.perform((RequestBuilder) fileUpload("/documents/").file(multipartFile).merge(post("/documents/").content(mapper.writeValueAsBytes(sgDocument))))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "/documents/" + sgDocument.getId()));
        BDDMockito.then(sgDocumentService).should().create(Mockito.any(SGDocument.class), Mockito.any(MockMultipartFile.class));
    }
}
