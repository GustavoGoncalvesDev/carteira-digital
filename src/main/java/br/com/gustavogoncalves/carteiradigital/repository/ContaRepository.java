package br.com.gustavogoncalves.carteiradigital.repository;

import br.com.gustavogoncalves.carteiradigital.model.Conta;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ContaRepository extends JpaRepository<Conta, String> {

}