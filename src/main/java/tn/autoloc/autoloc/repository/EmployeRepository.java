package tn.autoloc.autoloc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.autoloc.autoloc.entity.Employe;

@Repository
public interface EmployeRepository extends JpaRepository<Employe, Long> {
}
