package hw_2023_06_19__3_3.ru.hogwarts.school.repository;

import hw_2023_06_19__3_3.ru.hogwarts.school.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {
    List<Faculty> findAllByColor(String color);
}
