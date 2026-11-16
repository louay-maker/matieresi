package tn.autoloc.autoloc.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.autoloc.autoloc.entity.Agence;
import tn.autoloc.autoloc.entity.Equipement;
import tn.autoloc.autoloc.entity.Employe;
import tn.autoloc.autoloc.entity.Vehicule;
import tn.autoloc.autoloc.repository.AgenceRepository;
import tn.autoloc.autoloc.repository.EquipementRepository;
import tn.autoloc.autoloc.repository.EmployeRepository;
import tn.autoloc.autoloc.repository.VehiculeRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AffectationServicesTest {

    @Mock
    private EmployeRepository employeRepository;
    @Mock
    private AgenceRepository agenceRepository;
    @Mock
    private VehiculeRepository vehiculeRepository;
    @Mock
    private EquipementRepository equipementRepository;

    @InjectMocks
    private EmployeServiceImpl employeService;

    @InjectMocks
    private AgenceServiceImpl agenceService;

    @InjectMocks
    private VehiculeServiceImpl vehiculeService;

    private Agence agenceSfax;
    private Agence agenceTunis;
    private Employe employe;
    private Vehicule vehicule;
    private Equipement siegeBebe;

    @BeforeEach
    void setUp() {
        agenceSfax = new Agence();
        agenceSfax.setIdAgence(1L);
        agenceSfax.setNom("Agence Sfax");
        agenceSfax.setVille("Sfax");

        agenceTunis = new Agence();
        agenceTunis.setIdAgence(2L);
        agenceTunis.setNom("Agence Tunis");
        agenceTunis.setVille("Tunis");

        employe = new Employe();
        employe.setIdEmploye(10L);
        employe.setNom("Ben Salah");
        employe.setPrenom("Ahmed");
        employe.setAgence(agenceSfax);

        vehicule = new Vehicule();
        vehicule.setIdVehicule(100L);
        vehicule.setImmatriculation("220TUN1234");
        vehicule.setMarque("Renault");
        vehicule.setModele("Clio");
        vehicule.setTarifJournalier(BigDecimal.valueOf(90));
        vehicule.setEquipements(new ArrayList<>());

        siegeBebe = new Equipement();
        siegeBebe.setIdEquipement(50L);
        siegeBebe.setNom("Siège bébé");
        siegeBebe.setDescription("Siège bébé sécurisé Isofix");
        siegeBebe.setPrix(BigDecimal.valueOf(15));
    }

    // ==========================================
    // SCENARIO 1 : MUTATION EMPLOYE (@ManyToOne)
    // ==========================================

    @Test
    @DisplayName("Scénario 1 : Désaffecter un employé de son agence")
    void testDesaffecterEmploye() {
        when(employeRepository.findById(10L)).thenReturn(Optional.of(employe));
        when(employeRepository.save(any(Employe.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Employe result = employeService.desaffecterEmploye(10L);

        assertNotNull(result);
        assertNull(result.getAgence(), "L'employé ne doit plus être affecté à une agence");
        verify(employeRepository, times(1)).save(employe);
    }

    @Test
    @DisplayName("Scénario 1 : Affecter un employé à une nouvelle agence")
    void testAffecterEmploye() {
        when(employeRepository.findById(10L)).thenReturn(Optional.of(employe));
        when(agenceRepository.findById(2L)).thenReturn(Optional.of(agenceTunis));
        when(employeRepository.save(any(Employe.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Employe result = employeService.affecterEmploye(10L, 2L);

        assertNotNull(result);
        assertNotNull(result.getAgence());
        assertEquals(2L, result.getAgence().getIdAgence());
        assertEquals("Agence Tunis", result.getAgence().getNom());
        verify(employeRepository, times(1)).save(employe);
    }

    // =========================================================
    // SCENARIO 2 : AJOUT AGENCE ET AFFECTATION VEHICULES (@ManyToOne)
    // =========================================================

    @Test
    @DisplayName("Scénario 2 : Ajouter une agence avec deux véhicules")
    void testAjouterAgenceEtAffecterVehicules() {
        Agence agenceSousse = new Agence();
        agenceSousse.setNom("Agence Sousse");
        agenceSousse.setVille("Sousse");
        agenceSousse.setAdresse("Boulevard 14 Janvier");
        agenceSousse.setTelephone("73000000");

        Vehicule v1 = new Vehicule();
        v1.setImmatriculation("230TUN1111");
        v1.setMarque("Toyota");
        v1.setModele("Yaris");

        Vehicule v2 = new Vehicule();
        v2.setImmatriculation("230TUN2222");
        v2.setMarque("Hyundai");
        v2.setModele("i20");

        agenceSousse.setVehicules(List.of(v1, v2));

        when(agenceRepository.save(any(Agence.class))).thenAnswer(invocation -> {
            Agence a = invocation.getArgument(0);
            a.setIdAgence(3L);
            return a;
        });

        when(vehiculeRepository.save(any(Vehicule.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Agence result = agenceService.ajouterAgenceEtAffecterVehicules(agenceSousse);

        assertNotNull(result);
        assertEquals(3L, result.getIdAgence());
        assertEquals("Agence Sousse", result.getNom());
        assertNotNull(result.getVehicules());
        assertEquals(2, result.getVehicules().size());

        for (Vehicule v : result.getVehicules()) {
            assertNotNull(v.getAgence(), "Chaque véhicule doit être affecté à la nouvelle agence");
            assertEquals(3L, v.getAgence().getIdAgence());
        }

        verify(agenceRepository, times(1)).save(agenceSousse);
        verify(vehiculeRepository, times(2)).save(any(Vehicule.class));
    }

    // =======================================================
    // SCENARIO 3 : AFFECTATION / DESAFFECTATION EQUIPEMENT (@ManyToMany)
    // =======================================================

    @Test
    @DisplayName("Scénario 3 : Affecter un équipement (siège bébé) à un véhicule")
    void testAffecterEquipement() {
        when(vehiculeRepository.findById(100L)).thenReturn(Optional.of(vehicule));
        when(equipementRepository.findById(50L)).thenReturn(Optional.of(siegeBebe));
        when(vehiculeRepository.save(any(Vehicule.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Vehicule result = vehiculeService.affecterEquipement(100L, 50L);

        assertNotNull(result);
        assertNotNull(result.getEquipements());
        assertTrue(result.getEquipements().contains(siegeBebe), "Le véhicule doit contenir le siège bébé");
        assertEquals(1, result.getEquipements().size());
        verify(vehiculeRepository, times(1)).save(vehicule);
    }

    @Test
    @DisplayName("Scénario 3 : Désaffecter un équipement d'un véhicule")
    void testDesaffecterEquipement() {
        vehicule.getEquipements().add(siegeBebe);

        when(vehiculeRepository.findById(100L)).thenReturn(Optional.of(vehicule));
        when(equipementRepository.findById(50L)).thenReturn(Optional.of(siegeBebe));
        when(vehiculeRepository.save(any(Vehicule.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Vehicule result = vehiculeService.desaffecterEquipement(100L, 50L);

        assertNotNull(result);
        assertFalse(result.getEquipements().contains(siegeBebe), "Le véhicule ne doit plus contenir le siège bébé");
        assertEquals(0, result.getEquipements().size());
        verify(vehiculeRepository, times(1)).save(vehicule);
    }
}

