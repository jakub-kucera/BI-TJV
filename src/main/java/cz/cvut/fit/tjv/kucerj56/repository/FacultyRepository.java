package cz.cvut.fit.tjv.kucerj56.repository;

import cz.cvut.fit.tjv.kucerj56.entity.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FacultyRepository extends JpaRepository<Faculty, Integer> {
    Optional<Faculty> findByName(String name);
}
