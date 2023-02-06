package com.ancafra.controledeprodutos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.tsuryo.swipeablerv.SwipeLeftRightCallback;
import com.tsuryo.swipeablerv.SwipeableRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterProduct.OnClick {

    private List<Product> productList = new ArrayList<>();
    private SwipeableRecyclerView rvProduct;
    private AdapterProduct adapterProduct;
    private TextView textTitle;
    private ImageButton ibAdd;
    private ImageButton ibMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initComponents();
        listLoading();
        configRecycleView();
        clickListener();
    }

    private void clickListener() {
        ibAdd.setOnClickListener(v -> {
            startActivity(new Intent(this, FormProductActivity.class));
        });
        ibMenu.setOnClickListener(v -> {
            PopupMenu popupMenu  = new PopupMenu(this, ibMenu);
            popupMenu.getMenuInflater().inflate(R.menu.menu_toolbar, popupMenu.getMenu());
            popupMenu.setOnMenuItemClickListener(menuItem -> {
                if(menuItem.getItemId() == R.id.menuAbout) {
                    Toast.makeText(this, "Você clicou no botão sobre", Toast.LENGTH_SHORT).show();

                }
                return true;
            });
            popupMenu.show();
        });
    }

    private void listLoading() {
        Product produto1 = new Product();
        produto1.setName("Monitor 34 LG");
        produto1.setQuantity(45);
        produto1.setValue(1349.99);

        Product produto2 = new Product();
        produto2.setName("Caixa de Som C3 Tech");
        produto2.setQuantity(15);
        produto2.setValue(149.99);

        Product produto3 = new Product();
        produto3.setName("Microfone Blue yeti");
        produto3.setQuantity(38);
        produto3.setValue(1699.99);

        Product produto4 = new Product();
        produto4.setName("Gabinete NZXT H440");
        produto4.setQuantity(15);
        produto4.setValue(979.99);

        Product produto5 = new Product();
        produto5.setName("Placa Mãe Asus");
        produto5.setQuantity(60);
        produto5.setValue(1199.99);

        Product produto6 = new Product();
        produto6.setName("Memória Corsair 16GB");
        produto6.setQuantity(44);
        produto6.setValue(599.99);

        productList.add(produto1);
        productList.add(produto2);
        productList.add(produto3);
        productList.add(produto4);
        productList.add(produto5);
        productList.add(produto6);
    }

    //criação do método responsável por receber o padrão de layout e setar o recyclerView e instanciar o adapter
    private void configRecycleView() {
        //indicando para o recycleview que ele vai receber um linear layout
        rvProduct.setLayoutManager(new LinearLayoutManager(this));
        //deixa o recycleview mais performático
        rvProduct.setHasFixedSize(true);
        //instanciando o adapter com lista de produto e demais parâmetros passados no construtor
        adapterProduct = new AdapterProduct(productList, this);
        //atribuindo o adapter ao recyclerview
        rvProduct.setAdapter(adapterProduct);

        //implementando o movimento de swipe para a lista onde cada elemento da lista vai deslizar para ser deletado
        rvProduct.setListener(new SwipeLeftRightCallback.Listener() {
            @Override
            public void onSwipedLeft(int position) {

            }

            @Override
            public void onSwipedRight(int position) {
                //removendo o item da lista
                productList.remove(position);
                //informando o adapter que esse item excluído já não pertence mais a nossa lista, para que ele recarregue alista
                adapterProduct.notifyItemRemoved(position);
            }
        });

    }
    //método responsável por executar a sequencia do clique, o que vai acontecer depois do clique
    @Override
    public void onClickListener(Product produto) {
        //neste caso exibir um toast com o nome do produto clicado

    }

    private void initComponents() {
        rvProduct = findViewById(R.id.rvProduct);
        textTitle = findViewById(R.id.textTitulo);
        textTitle.setText("Product control");
        ibMenu = findViewById(R.id.ibMenu);
        ibAdd = findViewById(R.id.ibAdd);
    }
}