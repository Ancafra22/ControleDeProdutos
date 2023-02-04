package com.ancafra.controledeprodutos;

import android.widget.TextView;

public class Produto {
    private String nome;
    private int estoque;
    private double valor;

    public Produto() {
    }

    public Produto(String nome, int estoque, double valor) {
        this.nome = nome;
        this.estoque = estoque;
        this.valor = valor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String produto) {
        this.nome = produto;
    }

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int quantidade) {
        this.estoque = quantidade;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}
