package com.ancafra.controledeprodutos;

import android.widget.TextView;

public class Produto {
    private String produto;
    private int estoque;
    private double valor;

    public Produto() {
    }

    public Produto(String produto, int estoque, double valor) {
        this.produto = produto;
        this.estoque = estoque;
        this.valor = valor;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
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
