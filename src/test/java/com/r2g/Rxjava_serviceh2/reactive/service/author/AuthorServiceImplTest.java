package com.r2g.Rxjava_serviceh2.reactive.service.author;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class AuthorServiceImplTest {
    @Mock
    private AuthorRepository authorRepository;
    @InjectMocks
    private AuthorServiceImpl authorService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void AddAuthor_Success_ReturnSingleOfAddedAuthorId() {
        when(authorRepository.save(any(Author.class)))
                .thenReturn(new Author("1", "Axell"));

        authorService.addAuthor(new AddAuthorRequest("1"))
                .test()
                .assertComplete()
                .assertNoErrors()
                .awaitTerminalEvent();

        verify(authorRepository, times(1)).save(any(Author.class));
    }
}
