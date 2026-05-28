package app.atividadem2_cardapio;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class CategoriaActivity extends AppCompatActivity {

    CardView cardComidas;
    CardView cardBebidas;
    CardView cardSobremesas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoria);

        cardComidas = findViewById(R.id.cardComidas);
        cardBebidas = findViewById(R.id.cardBebidas);
        cardSobremesas = findViewById(R.id.cardSobremesas);

        cardComidas.setOnClickListener(v -> abrirItens("comidas"));

        cardBebidas.setOnClickListener(v -> abrirItens("bebidas"));

        cardSobremesas.setOnClickListener(v -> abrirItens("sobremesas"));
    }

    private void abrirItens(String categoria){

        Intent intent = new Intent(
                CategoriaActivity.this,
                ItensActivity.class
        );

        intent.putExtra("categoria", categoria);

        startActivity(intent);
    }
}