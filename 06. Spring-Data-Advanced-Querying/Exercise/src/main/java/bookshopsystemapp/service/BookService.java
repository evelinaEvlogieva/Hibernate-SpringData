package bookshopsystemapp.service;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public interface BookService {

    void seedBooks() throws IOException;

    List<String> getAllBooksTitlesAfter();

    Set<String> getAllAuthorsWithBookBefore();

    List<String> getAllBookTitlesWithAgeRestriction();

    List<String> getAllBookTitlesWithLess5000Copies();

    List<String> getAllBooksWithPriceLessThan5AndGreaterThan40();
}
