package app.atividadem2_cardapio;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button btnComidas, btnBebidas, btnSobremesas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnComidas = findViewById(R.id.btnComidas);
        btnBebidas = findViewById(R.id.btnBebidas);
        btnSobremesas = findViewById(R.id.btnSobremesas);

        btnComidas.setOnClickListener(v -> abrirCategoria("comidas"));

        btnBebidas.setOnClickListener(v -> abrirCategoria("bebidas"));

        btnSobremesas.setOnClickListener(v -> abrirCategoria("sobremesas"));
    }

    private void abrirCategoria(String categoria){

        Intent intent = new Intent(this, CategoriaActivity.class);

        intent.putExtra("categoria", categoria);

        startActivity(intent);
    }
}