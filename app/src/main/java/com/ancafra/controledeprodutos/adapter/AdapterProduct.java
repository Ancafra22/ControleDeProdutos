package com.ancafra.controledeprodutos.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ancafra.controledeprodutos.databinding.ItemProductBinding;
import com.ancafra.controledeprodutos.model.Product;

import java.util.List;

public class AdapterProduct extends RecyclerView.Adapter<AdapterProduct.MyViewHolder> {

    //criando a lista que será exibida com os itens do meu produto entre <> coloco a classe que contém os itens, no caso calsse Produto
    private List<Product> productList;
    //declarrando a interface criada
    private OnClick onClick;

    //criando o construtor das variáves declaradas acima
    public AdapterProduct(List<Product> productList, OnClick onClick) {
        this.productList = productList;
        this.onClick = onClick;
    }

    @NonNull
    @Override
    //método responsável por exibir o layout
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(
              ItemProductBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false)
        );
    }

    //método responsável por configurar as informações da linha da lista de acordo cam as informações necessárias
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        //estanciar a classe produto que é onde estão as informações e passando para ele a minha lista que é o produtoList pegando pela posição que vem no parâmetro do método(int position)
        Product product = productList.get(position);

        //exibir as informações em cada intem da lista
        holder.binding.textProduct.setText(product.getName());
        //quando houver variáves que não forem Strings tem que adcionar o String.valueOf para convertyer o valor para string para ser exibido sem erro
        holder.binding.textQuantity.setText("Stock: " + product.getQuantity());
        holder.binding.textValue.setText("US$ " + product.getValue());

        //cria o evento de clique dentro da lista
        holder.itemView.setOnClickListener(v -> onClick.onClickListener(product));

    }

    // método responsável por mostrar a quantidade de linhas que serão exibidas
    @Override
    public int getItemCount() {
        //recuperando a lista e retornando para o adapter de acordo com o seu tamanho.
        return productList.size();
    }

    //criando uma interface para o evento de clique no item da lista
    public interface OnClick{
        void onClickListener(Product product);
    }

    //configura quais elementos teremos na nossa listagem, aqui se declara todos os componentes que tem no layout
    static class MyViewHolder extends RecyclerView.ViewHolder{

        private final ItemProductBinding binding;

        public MyViewHolder(@NonNull ItemProductBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }
    }
}
