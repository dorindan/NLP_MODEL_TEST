package smart.smartbook.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import smart.smartbook.model.WholeBook;

import java.util.Optional;

public interface WholeBookRepository extends JpaRepository<WholeBook, Integer> {

    Optional<WholeBook> findById(Integer id);

    WholeBook findByName(String bookName);
}
