package tn.autoloc.autoloc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.autoloc.autoloc.entity.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
}
