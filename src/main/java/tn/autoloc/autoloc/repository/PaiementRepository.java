package tn.autoloc.autoloc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.autoloc.autoloc.entity.Paiement;

@Repository
public interface PaiementRepository extends JpaRepository<Paiement, Long> {
}
