package cadastro.equipamentos.cadastro.repository;

import cadastro.equipamentos.cadastro.model.Equipamento;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface EquipamentoRepository extends JpaRepository<Equipamento, Long> {
    Optional<Equipamento> findByNumeroSerie(String numeroSerie);
}