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

    private ProductDAO productDAO;
    private SwipeableRecyclerView rvProduct;
    private AdapterProduct adapterProduct;
    private TextView textTitle;
    private ImageButton ibAdd;
    private ImageButton ibMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        productDAO = new ProductDAO(this);

        initComponents();
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

    //criação do método responsável por receber o padrão de layout e setar o recyclerView e instanciar o adapter
    private void configRecycleView() {
        //indicando para o recycleview que ele vai receber um linear layout
        rvProduct.setLayoutManager(new LinearLayoutManager(this));
        //deixa o recycleview mais performático
        rvProduct.setHasFixedSize(true);
        //instanciando o adapter com lista de produto e demais parâmetros passados no construtor
        adapterProduct = new AdapterProduct(productDAO.getListProduct(), this);
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
                productDAO.getListProduct().remove(position);
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