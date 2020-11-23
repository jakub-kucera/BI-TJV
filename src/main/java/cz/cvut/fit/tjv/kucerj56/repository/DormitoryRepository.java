package cz.cvut.fit.tjv.kucerj56.repository;

import cz.cvut.fit.tjv.kucerj56.entity.Dormitory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DormitoryRepository extends JpaRepository<Dormitory, Integer> {
    Optional<Dormitory> findByName(String name);
}
