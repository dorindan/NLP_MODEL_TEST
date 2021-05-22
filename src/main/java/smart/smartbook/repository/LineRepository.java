package smart.smartbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import smart.smartbook.model.Line;

public interface LineRepository extends JpaRepository<Line, Integer> {
}
