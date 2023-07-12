package hw_2023_06_19__3_3.ru.hogwarts.school.service;

import io.micrometer.common.lang.Nullable;
import org.springframework.stereotype.Service;
import hw_2023_06_19__3_3.ru.hogwarts.school.dto.*;
import hw_2023_06_19__3_3.ru.hogwarts.school.entity.*;
import hw_2023_06_19__3_3.ru.hogwarts.school.exception.*;
import hw_2023_06_19__3_3.ru.hogwarts.school.mapper.*;
import hw_2023_06_19__3_3.ru.hogwarts.school.repository.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    public StudentService(StudentRepository studentRepository, StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }

    public StudentDtoOut create(StudentDtoIn studentDtoIn) {
        return studentMapper.toDto(
                studentRepository.save(
                        studentMapper.toEntity(studentDtoIn)));
    }

    public StudentDtoOut update(long id, StudentDtoIn studentDtoIn) {
        return studentRepository.findById(id)
                .map(oldStudent -> {
                    oldStudent.setName(studentDtoIn.getName());
                    oldStudent.setAge(studentDtoIn.getAge());
                    return studentMapper.toDto(studentRepository.save(oldStudent));
                })
                .orElseThrow(() -> new StudentNotFoundException(id));
    }

    public StudentDtoOut delete(long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(id));
        studentRepository.delete(student);
        return studentMapper.toDto(student);
    }

    public StudentDtoOut get(long id) {
        return studentRepository.findById(id)
                .map(studentMapper::toDto)
                .orElseThrow(() -> new StudentNotFoundException(id));
    }

    public List<StudentDtoOut> findAll(@Nullable Integer age) {
        return Optional.ofNullable(age)
                .map(studentRepository::findAllByAge)
                .orElseGet(studentRepository::findAll).stream()
                .map(studentMapper :: toDto)
                .collect(Collectors.toList());
    }
}
