package cadastro.equipamentos.cadastro.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

public class HomerController {
    @GetMapping("/")
    public String home() {
        return "redirect:/equipamentos";
    }

    @GetMapping("/home")
    public String homePage() {
        return "redirect:/equipamentos";
    }
}
