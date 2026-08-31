package br.com.gustavogoncalves.carteiradigital.service;

import br.com.gustavogoncalves.carteiradigital.model.Conta;
import br.com.gustavogoncalves.carteiradigital.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContaService {

    @Autowired
    private ContaRepository repository;

    public List<Conta> listarTodas() {
        return repository.findAll();
    }

    public Conta buscarPorCpf(String cpf) {
        return repository.findById(cpf).orElse(null);
    }

    public Conta salvar(Conta conta) {
        return repository.save(conta);
    }

    public String transferir(String cpfOrigem, String cpfDestino, double valor) {
        Conta origem = buscarPorCpf(cpfOrigem);
        Conta destino = buscarPorCpf(cpfDestino);

        if (origem == null || destino == null) {
            return "Erro: Conta não encontrada.";
        }
        if (origem.getSaldo() < valor) {
            return "Erro: Saldo insuficiente.";
        }

        origem.sacar(valor);
        destino.depositar(valor);

        repository.save(origem);
        repository.save(destino);

        return "Pix realizado com sucesso!";
    }
}