package br.com.gustavogoncalves.carteiradigital;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/contas")
public class TesteController {

    @Autowired
    private ContaRepository repository;

    @GetMapping
    public List<Conta> listarContas() {
        return repository.findAll();
    }

    @PostMapping
    public String criarConta(@RequestBody Conta novaConta) {
        repository.save(novaConta);
        return "Conta salva no BANCO DE DADOS com sucesso! Titular: " + novaConta.getTitular();
    }

    @GetMapping("/{cpfUsuario}")
    public Conta buscarPorCpf(@PathVariable String cpfUsuario) {
        return repository.findById(cpfUsuario).orElse(null);
    }
}