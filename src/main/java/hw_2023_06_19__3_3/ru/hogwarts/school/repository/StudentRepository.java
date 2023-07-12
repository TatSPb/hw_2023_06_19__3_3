package hw_2023_06_19__3_3.ru.hogwarts.school.repository;

import hw_2023_06_19__3_3.ru.hogwarts.school.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findAllByAge(int age);
}
