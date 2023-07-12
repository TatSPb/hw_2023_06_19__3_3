package hw_2023_06_19__3_3.ru.hogwarts.school.mapper;

import org.springframework.stereotype.Component;
import hw_2023_06_19__3_3.ru.hogwarts.school.dto.*;
import hw_2023_06_19__3_3.ru.hogwarts.school.entity.*;

@Component
public class StudentMapper {
    public StudentDtoOut toDto (Student student){
        StudentDtoOut studentDtoOut = new StudentDtoOut();
        studentDtoOut.setId(student.getId());
        studentDtoOut.setName(student.getName());
        studentDtoOut.setAge(student.getAge());
        return studentDtoOut;
    }
    public Student toEntity (StudentDtoIn studentDtoIn){
        Student student = new Student();
        student.setAge(studentDtoIn.getAge());
        student.setName(studentDtoIn.getName());
        return student;
    }
}
