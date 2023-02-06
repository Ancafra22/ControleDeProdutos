package com.ancafra.controledeprodutos;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ancafra.controledeprodutos.databinding.ItemProdutoBinding;

import java.util.List;

public class AdapterProduto extends RecyclerView.Adapter<AdapterProduto.MyViewHolder> {

    //criando a lista que será exibida com os itens do meu produto entre <> coloco a classe que contém os itens, no caso calsse Produto
    private List<Produto> produtoList;
    //declarrando a interface criada
    private OnClick onClick;

    //criando o construtor das variáves declaradas acima
    public AdapterProduto(List<Produto> produtoList, OnClick onClick) {
        this.produtoList = produtoList;
        this.onClick = onClick;
    }

    @NonNull
    @Override
    //método responsável por exibir o layout
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(
              ItemProdutoBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false)
        );
    }

    //método responsável por configurar as informações da linha da lista de acordo cam as informações necessárias
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        //estanciar a classe produto que é onde estão as informações e passando para ele a minha lista que é o produtoList pegando pela posição que vem no parâmetro do método(int position)
        Produto produto = produtoList.get(position);

        //exibir as informações em cada intem da lista
        holder.binding.textProduto.setText(produto.getNome());
        //quando houver variáves que não forem Strings tem que adcionar o String.valueOf para convertyer o valor para string para ser exibido sem erro
        holder.binding.textEstoque.setText("Estoque: " + produto.getEstoque());
        holder.binding.textValor.setText("R$ " + produto.getValor());

        //cria o evento de clique dentro da lista
        holder.itemView.setOnClickListener(v -> onClick.onClickListener(produto));

    }

    // método responsável por mostrar a quantidade de linhas que serão exibidas
    @Override
    public int getItemCount() {
        //recuperando a lista e retornando para o adapter de acordo com o seu tamanho.
        return produtoList.size();
    }

    //criando uma interface para o evento de clique no item da lista
    public interface OnClick{
        void onClickListener(Produto produto);
    }

    //configura quais elementos teremos na nossa listagem, aqui se declara todos os componentes que tem no layout
    static class MyViewHolder extends RecyclerView.ViewHolder{

        private ItemProdutoBinding binding;

        public MyViewHolder(ItemProdutoBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }
    }
}
