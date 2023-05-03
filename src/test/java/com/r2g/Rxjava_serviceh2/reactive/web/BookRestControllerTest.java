package com.r2g.Rxjava_serviceh2.reactive.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

@RunWith(SpringRunner.class)
@WebMvcTest(BookRestController.class)
public class BookRestControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private BookService bookService;

    @Test
    public void AddBook_Success_Return201() throws Exception {
        when(bookService.addBook(any(AddBookRequest.class)))
                .thenReturn(Single.just("1"));

        MvcResult mvcResult = mockMvc.perform(post("/api/books")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(new AddBookWebRequest())))
                .andReturn();

        mockMvc.perform(asyncDispatch(mvcResult))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.errorCode", nullValue()))
                .andExpect(jsonPath("$.data", nullValue()));

        verify(bookService, times(1)).addBook(any(AddBookRequest.class));
    }

    @Test
    public void AddBook_Failed_AuthorNotFound_Return404EntityNotFound() throws Exception {
        when(bookService.addBook(any(AddBookRequest.class)))
                .thenReturn(Single.error(new EntityNotFoundException()));

        MvcResult mvcResult = mockMvc.perform(post("/api/books")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(new AddBookWebRequest())))
                .andReturn();

        mockMvc.perform(asyncDispatch(mvcResult))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.errorCode", equalTo(ErrorCode.ENTITY_NOT_FOUND.toString())))
                .andExpect(jsonPath("$.data", nullValue()));

        verify(bookService, times(1)).addBook(any(AddBookRequest.class));
    }

    @Test
    public void UpdateBook_Success_Return200() throws Exception {
        when(bookService.updateBook(any(UpdateBookRequest.class)))
                .thenReturn(Completable.complete());

        MvcResult mvcResult = mockMvc.perform(put("/api/books/id", "123")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(new UpdateBookWebRequest())))
                .andReturn();

        mockMvc.perform(asyncDispatch(mvcResult))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.errorCode", nullValue()))
                .andExpect(jsonPath("$.data", nullValue()));

        verify(bookService, times(1)).updateBook(any(UpdateBookRequest.class));
    }

    @Test
    public void UpdateBook_Failed_BookIdNotFound_Return404EntityNotFound() throws Exception {
        when(bookService.updateBook(any(UpdateBookRequest.class)))
                .thenReturn(Completable.error(new EntityNotFoundException()));

        MvcResult mvcResult = mockMvc.perform(put("/api/books/id", "123")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(new UpdateBookWebRequest())))
                .andReturn();

        mockMvc.perform(asyncDispatch(mvcResult))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.errorCode", equalTo(ErrorCode.ENTITY_NOT_FOUND.toString())))
                .andExpect(jsonPath("$.data", nullValue()));

        verify(bookService, times(1)).updateBook(any(UpdateBookRequest.class));
    }

    @Test
    public void GetAllBooks_LimitAndPageSpecified_Success_Return200WithListOfBookWebResponse() throws Exception {
        when(bookService.getAllBooks(anyInt(), anyInt()))
                .thenReturn(Single.just(Collections.singletonList(new BookResponse("1", "1", "1"))));

        MvcResult mvcResult = mockMvc.perform(get("/api/books?limit=5&page=0")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

        mockMvc.perform(asyncDispatch(mvcResult))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.errorCode", nullValue()))
                .andExpect(jsonPath("$.data[0].id", equalTo("1")));

        verify(bookService, times(1)).getAllBooks(anyInt(), anyInt());
    }

    @Test
    public void GetAllBooks_LimitAndPageNotSpecified_Success_Return200WithListOfBookWebResponse() throws Exception {
        when(bookService.getAllBooks(anyInt(), anyInt()))
                .thenReturn(Single.just(Collections.singletonList(new BookResponse("1", "1", "1"))));

        MvcResult mvcResult = mockMvc.perform(get("/api/books")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

        mockMvc.perform(asyncDispatch(mvcResult))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.errorCode", nullValue()))
                .andExpect(jsonPath("$.data[0].id", equalTo("1")));

        verify(bookService, times(1)).getAllBooks(anyInt(), anyInt());
    }

    @Test
    public void GetBookDetail_Success_Return200WithBookWebResponse() throws Exception {
        when(bookService.getBookDetail(anyString()))
                .thenReturn(Single.just(new BookResponse("1", "1", "1")));

        MvcResult mvcResult = mockMvc.perform(get("/api/books/1")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

        mockMvc.perform(asyncDispatch(mvcResult))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.errorCode", nullValue()))
                .andExpect(jsonPath("$.data.id", equalTo("1")));

        verify(bookService, times(1)).getBookDetail(anyString());
    }

    @Test
    public void GetBookDetail_Failed_BookIdNotFound_Return404EntityNotFound() throws Exception {
        when(bookService.getBookDetail(anyString()))
                .thenReturn(Single.error(new EntityNotFoundException()));

        MvcResult mvcResult = mockMvc.perform(get("/api/books/1")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

        mockMvc.perform(asyncDispatch(mvcResult))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.errorCode", equalTo(ErrorCode.ENTITY_NOT_FOUND.toString())))
                .andExpect(jsonPath("$.data", nullValue()));

        verify(bookService, times(1)).getBookDetail(anyString());
    }

    @Test
    public void DeleteBook_Success_Return200() throws Exception {
        when(bookService.deleteBook(anyString()))
                .thenReturn(Completable.complete());

        MvcResult mvcResult = mockMvc.perform(delete("/api/books/1")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

        mockMvc.perform(asyncDispatch(mvcResult))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.errorCode", nullValue()))
                .andExpect(jsonPath("$.data", nullValue()));

        verify(bookService, times(1)).deleteBook(anyString());
    }

    @Test
    public void DeleteBook_Failed_BookIdNotFound_Return404EntityNotFound() throws Exception {
        when(bookService.deleteBook(anyString()))
                .thenReturn(Completable.error(new EntityNotFoundException()));

        MvcResult mvcResult = mockMvc.perform(delete("/api/books/1")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

        mockMvc.perform(asyncDispatch(mvcResult))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.errorCode", equalTo(ErrorCode.ENTITY_NOT_FOUND.toString())))
                .andExpect(jsonPath("$.data", nullValue()));

        verify(bookService, times(1)).deleteBook(anyString());
    }
}
