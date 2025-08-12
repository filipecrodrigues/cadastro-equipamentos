package cadastro.equipamentos.cadastro.service;

import cadastro.equipamentos.cadastro.model.Equipamento;
import cadastro.equipamentos.cadastro.repository.EquipamentoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EquipamentoService {

    private final EquipamentoRepository repo;

    public EquipamentoService(EquipamentoRepository repo) {
        this.repo = repo;
    }

    public List<Equipamento> listarTodos() { return repo.findAll(); }

    public Optional<Equipamento> buscarPorId(Long id) { return repo.findById(id); }

    public Equipamento salvar(Equipamento p) { return repo.save(p); }

    public void excluir(Long id) { repo.deleteById(id); }

    public Optional<Equipamento> buscarPorNumeroSerie(String ns) { return repo.findByNumeroSerie(ns); }
}
