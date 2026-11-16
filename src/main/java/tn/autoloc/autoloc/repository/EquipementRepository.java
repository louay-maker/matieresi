package tn.autoloc.autoloc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.autoloc.autoloc.entity.Equipement;

@Repository
public interface EquipementRepository extends JpaRepository<Equipement, Long> {
}

