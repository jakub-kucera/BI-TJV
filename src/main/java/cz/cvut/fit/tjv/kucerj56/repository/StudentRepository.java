package cz.cvut.fit.tjv.kucerj56.repository;

import cz.cvut.fit.tjv.kucerj56.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {
}
