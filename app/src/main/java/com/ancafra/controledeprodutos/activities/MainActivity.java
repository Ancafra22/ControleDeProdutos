package com.ancafra.controledeprodutos.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.ancafra.controledeprodutos.adapter.AdapterProduct;
import com.ancafra.controledeprodutos.model.Product;
import com.ancafra.controledeprodutos.ProductDAO;
import com.ancafra.controledeprodutos.R;
import com.tsuryo.swipeablerv.SwipeLeftRightCallback;
import com.tsuryo.swipeablerv.SwipeableRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterProduct.OnClick {

    private ProductDAO productDAO;
    private SwipeableRecyclerView rvProduct;
    private AdapterProduct adapterProduct;
    private List<Product> productList = new ArrayList<>();
    private TextView textTitle;
    private ImageButton ibAdd;
    private ImageButton ibMenu;
    private TextView textInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        productDAO = new ProductDAO(this);
        productList = productDAO.getListProduct();

        initComponents();
        clickListener();
    }

    @Override
    protected void onStart() {
        super.onStart();
        configRecycleView();
    }

    private void clickListener() {
        ibAdd.setOnClickListener(v -> {
            startActivity(new Intent(this, FormProductActivity.class));
        });
        ibMenu.setOnClickListener(v -> {
            PopupMenu popupMenu = new PopupMenu(this, ibMenu);
            popupMenu.getMenuInflater().inflate(R.menu.menu_toolbar, popupMenu.getMenu());
            popupMenu.setOnMenuItemClickListener(menuItem -> {
                if (menuItem.getItemId() == R.id.menuAbout) {
                    Toast.makeText(this, "Você clicou no botão sobre", Toast.LENGTH_SHORT).show();

                }
                return true;
            });
            popupMenu.show();
        });
    }

    //criação do método responsável por receber o padrão de layout e setar o recyclerView e instanciar o adapter
    private void configRecycleView() {

        productList.clear();
        productList = productDAO.getListProduct();
        checkListQuantity();

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
//                Product product = productList.get(position);
//                if (product.getId() != 0) {
//                    productDAO.updateProduct(product);
//                }
            }

            @Override
            public void onSwipedRight(int position) {

                Product product = productList.get(position);
                //removendo o item da lista do banco de dados
                productDAO.deleteProduct(product);
                //removendo o item da lista
                productList.remove(product);
                //informando o adapter que esse item excluído já não pertence mais a nossa lista, para que ele recarregue alista
                adapterProduct.notifyItemRemoved(position);

                checkListQuantity();

            }
        });
    }

    //método responsável por executar a sequencia do clique, o que vai acontecer depois do clique
    @Override
    public void onClickListener(Product product) {
        //neste caso vai abrir a activity de formulário para editar o produto
        Intent intent = new Intent(this, FormProductActivity.class);
        intent.putExtra("product", product);
        startActivity(intent);

    }

    private void checkListQuantity() {
        if (productList.size() == 0) {
            textInfo.setVisibility(View.VISIBLE);
        } else {
            textInfo.setVisibility(View.GONE);
        }

    }

    private void initComponents() {
        rvProduct = findViewById(R.id.rvProduct);
        textTitle = findViewById(R.id.textTitle);
        textTitle.setText("Product control");
        ibMenu = findViewById(R.id.ibMenu);
        ibAdd = findViewById(R.id.ibAdd);
        textInfo = findViewById(R.id.textInfo);
    }
}