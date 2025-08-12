package cadastro.equipamentos.cadastro.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Table(name = "equipamento")
public class Equipamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, name = "numero_serie")
    @NotBlank(message = "Número de série é obrigatório")
    @Size(min = 3, max = 50, message = "Número de série deve ter entre 3 e 50 caracteres")
    private String numeroSerie;

    @Size(max = 100, message = "Marca deve ter no máximo 100 caracteres")
    private String marca;

    @Size(max = 100, message = "Modelo deve ter no máximo 100 caracteres")
    private String modelo;

    @Column(name = "data_entrega")
    private LocalDate dataEntrega;

    // Construtor padrão
    public Equipamento() {}

    // Construtor com parâmetros
    public Equipamento(String numeroSerie, String marca, String modelo, LocalDate dataEntrega) {
        this.numeroSerie = numeroSerie;
        this.marca = marca;
        this.modelo = modelo;
        this.dataEntrega = dataEntrega;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroSerie() {
        return numeroSerie;
    }

    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public LocalDate getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(LocalDate dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    // toString para debug
    @Override
    public String toString() {
        return "Equipamento{" +
                "id=" + id +
                ", numeroSerie='" + numeroSerie + '\'' +
                ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", dataEntrega=" + dataEntrega +
                '}';
    }
}