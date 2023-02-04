package com.ancafra.controledeprodutos;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdapterProduto extends RecyclerView.Adapter<AdapterProduto.MyViewHolder> {

    //criando a lista que será exibida com os itens do meu produto entre <> coloco a classe que contém os itens, no caso calsse Produto
    private List<Produto> produtoList;

    //criando o construtor da lista
    public AdapterProduto(List<Produto> produtoList) {
        this.produtoList = produtoList;
    }

    @NonNull
    @Override
    //método responsável por exibir o layout
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //criando variável do tipo View para receber o meu layout
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_produto, parent, false);

        //reornar o meu myViewHolder passando o meu iteView
        return new MyViewHolder(itemView);
    }

    //método responsável por configurar as informações da linha da lista de acordo cam as informações necessárias
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        //estanciar a classe produto que é onde estão as informações e passando para ele a minha lista que é o produtoList pegando pela posição que vem no parâmetro do método(int position)
        Produto produto = produtoList.get(position);

        //exibir as informações em cada intem da lista
        holder.textProduto.setText(produto.getProduto());
        //quando houver variáves que não forem Strings tem que adcionar o String.valueOf para convertyer o valor para string para ser exibido sem erro
        holder.textEstoque.setText(String.valueOf(produto.getEstoque()));
        holder.textValor.setText(String.valueOf(produto.getValor()));

    }

    // método responsável por mostrar a quantidade de linhas que serão exibidas
    @Override
    public int getItemCount() {
        //recuperando a lista e retornando para o adapter de acordo com o seu tamanho.
        return produtoList.size();
    }

    //configura quais elementos teremos na nossa listagem, aqui se declara todos os componentes que tem no layout
    static class MyViewHolder extends RecyclerView.ViewHolder{
        //declarando os componentes que terão no layout
        TextView textProduto;
        TextView textEstoque;
        TextView textValor;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            //referenciando os itens declarados acima passando a minha view que no caso é itemView que é o layout que contém as informações a serem exibidas
            textProduto = itemView.findViewById(R.id.text_produto);
            textEstoque = itemView.findViewById(R.id.text_estoque);
            textValor = itemView.findViewById(R.id.text_valor);
        }
    }
}
