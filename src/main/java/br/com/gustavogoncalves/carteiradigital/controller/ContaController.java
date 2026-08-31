package br.com.gustavogoncalves.carteiradigital.controller;

import br.com.gustavogoncalves.carteiradigital.model.Conta;
import br.com.gustavogoncalves.carteiradigital.service.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/contas")
public class ContaController {

    @Autowired
    private ContaService service;

    @GetMapping
    public ResponseEntity<List<Conta>> listarContas() {
        return ResponseEntity.ok(service.listarTodas());
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<Conta> buscarPorCpf(@PathVariable String cpf) {
        Conta conta = service.buscarPorCpf(cpf);
        if (conta == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(conta);
    }

    @PostMapping
    public ResponseEntity<Conta> criarConta(@RequestBody Conta conta) {
        Conta novaConta = service.salvar(conta);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaConta);
    }

    public record PedidoTransferencia(
            @NotBlank String cpfOrigem,
            @NotBlank String cpfDestino,
            @Positive double valor) {}

    @PostMapping("/transferencia")
    public ResponseEntity<String> transferirPix(@RequestBody @Valid PedidoTransferencia pedido) {
        String resultado = service.transferir(pedido.cpfOrigem(), pedido.cpfDestino(), pedido.valor());

        if (resultado.startsWith("Erro")) {
            return ResponseEntity.badRequest().body(resultado);
        }

        return ResponseEntity.ok(resultado);
    }
}