package app.atividadem2_cardapio;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button btnCardapio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCardapio = findViewById(R.id.btnCardapio);

        btnCardapio.setOnClickListener(v -> {

            Intent intent = new Intent(
                    MainActivity.this,
                    CategoriaActivity.class
            );

            startActivity(intent);
        });
    }
}