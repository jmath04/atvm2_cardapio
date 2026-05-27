package app.atividadem2_cardapio;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CategoriaActivity extends AppCompatActivity {

    TextView txtCategoria;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoria);

        txtCategoria = findViewById(R.id.txtCategoria);
        recyclerView = findViewById(R.id.recyclerView);

        String categoria = getIntent().getStringExtra("categoria");

        txtCategoria.setText(categoria);

        List<MenuItem> lista = new ArrayList<>();

        if(categoria.equals("bebidas")){

            lista.add(new MenuItem("Água", "R$ 4,00", R.drawable.agua));
            lista.add(new MenuItem("Refrigerante", "R$ 8,00", R.drawable.refri));
            lista.add(new MenuItem("Suco Natural", "R$ 12,00", R.drawable.suco));
            lista.add(new MenuItem("Chá Gelado", "R$ 9,00", R.drawable.cha));
            lista.add(new MenuItem("Milkshake", "R$ 18,00", R.drawable.milkshake));
            lista.add(new MenuItem("Cerveja", "R$ 14,00", R.drawable.cerveja));

        } else if(categoria.equals("sobremesas")){

            lista.add(new MenuItem("Petit Gateau", "R$ 22,00", R.drawable.petitgateau));
            lista.add(new MenuItem("Brownie", "R$ 15,00", R.drawable.brownie));
            lista.add(new MenuItem("Salada de Frutas", "R$ 14,00", R.drawable.saladafrutas));

        } else {

            lista.add(new MenuItem("Batata Frita", "R$ 18,00", R.drawable.batata));
            lista.add(new MenuItem("Polenta Frita", "R$ 20,00", R.drawable.polenta));
            lista.add(new MenuItem("X-Salada", "R$ 28,00", R.drawable.xsalada));
            lista.add(new MenuItem("X-Galinha", "R$ 30,00", R.drawable.xgalinha));
            lista.add(new MenuItem("X-Burguer", "R$ 32,00", R.drawable.xburguer));
            lista.add(new MenuItem("X-Bacon", "R$ 35,00", R.drawable.xbacon));
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MenuAdapter(lista));
    }
}