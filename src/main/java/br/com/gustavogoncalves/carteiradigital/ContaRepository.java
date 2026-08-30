package br.com.gustavogoncalves.carteiradigital;

import org.springframework.data.jpa.repository.JpaRepository;


public interface ContaRepository extends JpaRepository<Conta, String> {

}