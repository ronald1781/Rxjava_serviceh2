package com.r2g.Rxjava_serviceh2.reactive.web;

import com.r2g.Rxjava_serviceh2.web.AuthorRestController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@RunWith(SpringRunner.class)
@WebMvcTest(value = AuthorRestController.class)
public class AuthorRestControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AuthorService authorService;

    @Test
    public void AddAuthor_Success_Return201() throws Exception {
        when(authorService.addAuthor(any(AddAuthorRequest.class)))
                .thenReturn(Single.just("1"));

        MvcResult mvcResult = mockMvc.perform(post("/api/authors")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(new AddAuthorWebRequest("1"))))
                .andReturn();

        mockMvc.perform(asyncDispatch(mvcResult))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.errorCode", nullValue()))
                .andExpect(jsonPath("$.data", nullValue()));

        verify(authorService, times(1)).addAuthor(any(AddAuthorRequest.class));
    }
}
