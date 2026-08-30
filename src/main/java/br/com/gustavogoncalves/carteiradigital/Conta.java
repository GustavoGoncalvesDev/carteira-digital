package br.com.gustavogoncalves.carteiradigital;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Conta {

    @Id
    private String cpf;
    private String titular;
    private double saldo;

    public Conta() {}

    public Conta(String cpf, String titular, double saldo) {
        this.cpf = cpf;
        this.titular = titular;
        this.saldo = saldo;
    }

    public String getCpf() { return cpf; }
    public String getTitular() { return titular; }
    public double getSaldo() { return saldo; }
}