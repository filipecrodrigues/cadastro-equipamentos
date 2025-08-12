package cadastro.equipamentos.cadastro.controllers;

import cadastro.equipamentos.cadastro.model.Equipamento;
import cadastro.equipamentos.cadastro.service.EquipamentoService;
import cadastro.equipamentos.cadastro.util.ExcelExporter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/equipamentos")
public class EquipamentoController {

    private final EquipamentoService service;

    public EquipamentoController(EquipamentoService service) {
        this.service = service;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("equipamentos", service.listarTodos());
        return "equipamentos_list";
    }

    @GetMapping("/novo")
    public String novoForm(Model model) {
        model.addAttribute("equipamento", new Equipamento());
        return "equipamento_form";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid @ModelAttribute Equipamento equipamento,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {

        // Se há erros de validação, retorna ao formulário
        if (bindingResult.hasErrors()) {
            return "equipamento_form";
        }

        try {
            // Validação de número de série único
            Optional<Equipamento> existente = service.buscarPorNumeroSerie(equipamento.getNumeroSerie());
            if (existente.isPresent()) {
                // Se é um equipamento novo ou está tentando usar número de série de outro equipamento
                if (equipamento.getId() == null || !existente.get().getId().equals(equipamento.getId())) {
                    bindingResult.rejectValue("numeroSerie", "error.equipamento",
                            "Número de série já cadastrado para outro equipamento");
                    return "equipamento_form";
                }
            }

            service.salvar(equipamento);

            // Mensagem de sucesso
            String mensagem = equipamento.getId() == null ?
                    "Equipamento cadastrado com sucesso!" :
                    "Equipamento atualizado com sucesso!";
            redirectAttributes.addFlashAttribute("sucesso", mensagem);

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("erro",
                    "Erro ao salvar equipamento: " + e.getMessage());
        }

        return "redirect:/equipamentos";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        Optional<Equipamento> equipamento = service.buscarPorId(id);

        if (equipamento.isEmpty()) {
            redirectAttributes.addFlashAttribute("erro", "Equipamento não encontrado");
            return "redirect:/equipamentos";
        }

        model.addAttribute("equipamento", equipamento.get());
        return "equipamento_form";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            Optional<Equipamento> equipamento = service.buscarPorId(id);
            if (equipamento.isEmpty()) {
                redirectAttributes.addFlashAttribute("erro", "Equipamento não encontrado");
            } else {
                service.excluir(id);
                redirectAttributes.addFlashAttribute("sucesso", "Equipamento excluído com sucesso!");
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("erro",
                    "Erro ao excluir equipamento: " + e.getMessage());
        }

        return "redirect:/equipamentos";
    }

    @GetMapping("/detalhes/{id}")
    public String detalhes(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        Optional<Equipamento> equipamento = service.buscarPorId(id);

        if (equipamento.isEmpty()) {
            redirectAttributes.addFlashAttribute("erro", "Equipamento não encontrado");
            return "redirect:/equipamentos";
        }

        model.addAttribute("equipamento", equipamento.get());
        return "equipamento_detalhes";
    }

    @GetMapping("/exportar/excel")
    public void exportarExcel(HttpServletResponse response) throws IOException {
        try {
            List<Equipamento> lista = service.listarTodos();

            if (lista.isEmpty()) {
                response.sendError(HttpServletResponse.SC_NO_CONTENT, "Nenhum equipamento encontrado para exportar");
                return;
            }

            ExcelExporter exporter = new ExcelExporter();
            exporter.export(lista, response);

        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                    "Erro ao gerar arquivo Excel: " + e.getMessage());
        }
    }

    @ExceptionHandler(RuntimeException.class)
    public String handleRuntimeException(RuntimeException e, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("erro", e.getMessage());
        return "redirect:/equipamentos";
    }
}