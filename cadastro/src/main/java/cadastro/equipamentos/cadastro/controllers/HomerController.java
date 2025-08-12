package cadastro.equipamentos.cadastro.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

public class HomerController {
    @GetMapping("/equipamentos")
    public String home() {
        return "redirect:/equipamentos";
    }

    @GetMapping("/home")
    public String homePage() {
        return "redirect:/equipamentos";
    }

    @GetMapping("/index")
    public String index() {
        return "redirect:/equipamentos";
    }
}
