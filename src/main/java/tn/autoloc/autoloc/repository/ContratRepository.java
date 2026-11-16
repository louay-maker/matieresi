package tn.autoloc.autoloc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.autoloc.autoloc.entity.Contrat;

@Repository
public interface ContratRepository extends JpaRepository<Contrat, Long> {
}
