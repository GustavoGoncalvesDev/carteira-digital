package br.com.gustavogoncalves.carteiradigital.controller;

import br.com.gustavogoncalves.carteiradigital.model.Conta;
import br.com.gustavogoncalves.carteiradigital.service.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contas")
public class ContaController {

    @Autowired
    private ContaService service;

    @GetMapping
    public List<Conta> listarContas() {
        return service.listarTodas();
    }

    @GetMapping("/{cpf}")
    public Conta buscarPorCpf(@PathVariable String cpf) {
        return service.buscarPorCpf(cpf);
    }

    @PostMapping
    public String criarConta(@RequestBody Conta conta) {
        service.salvar(conta);
        return "Conta salva com sucesso!";
    }

    public record PedidoTransferencia(String cpfOrigem, String cpfDestino, double valor) {}

    @PostMapping("/transferencia")
    public String transferirPix(@RequestBody PedidoTransferencia pedido) {
        return service.transferir(pedido.cpfOrigem(), pedido.cpfDestino(), pedido.valor());
    }
}