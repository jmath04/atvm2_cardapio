package app.atividadem2_cardapio;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ItensActivity extends AppCompatActivity {

    TextView txtCategoria;
    RecyclerView recyclerView;

    List<MenuItem> lista = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itens);

        txtCategoria = findViewById(R.id.txtCategoria);
        recyclerView = findViewById(R.id.recyclerView);

        String categoria = getIntent().getStringExtra("categoria");

        txtCategoria.setText(categoria);

        carregarJson(categoria);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(new MenuAdapter(lista));
    }

    private void carregarJson(String categoriaSelecionada){

        try {

            String json = "[" +

                    "{\"nome\":\"Água\",\"preco\":\"R$ 4,00\",\"categoria\":\"bebidas\",\"imagem\":\"agua\"}," +

                    "{\"nome\":\"Refrigerante\",\"preco\":\"R$ 8,00\",\"categoria\":\"bebidas\",\"imagem\":\"refri\"}," +

                    "{\"nome\":\"Suco Natural\",\"preco\":\"R$ 12,00\",\"categoria\":\"bebidas\",\"imagem\":\"suco\"}," +

                    "{\"nome\":\"Chá Gelado\",\"preco\":\"R$ 9,00\",\"categoria\":\"bebidas\",\"imagem\":\"cha\"}," +

                    "{\"nome\":\"Milkshake\",\"preco\":\"R$ 18,00\",\"categoria\":\"bebidas\",\"imagem\":\"milkshake\"}," +

                    "{\"nome\":\"Cerveja\",\"preco\":\"R$ 14,00\",\"categoria\":\"bebidas\",\"imagem\":\"cerveja\"}," +

                    "{\"nome\":\"Petit Gateau\",\"preco\":\"R$ 22,00\",\"categoria\":\"sobremesas\",\"imagem\":\"petitgateau\"}," +

                    "{\"nome\":\"Brownie\",\"preco\":\"R$ 15,00\",\"categoria\":\"sobremesas\",\"imagem\":\"brownie\"}," +

                    "{\"nome\":\"Salada de Frutas\",\"preco\":\"R$ 14,00\",\"categoria\":\"sobremesas\",\"imagem\":\"saladafrutas\"}," +

                    "{\"nome\":\"Batata Frita\",\"preco\":\"R$ 18,00\",\"categoria\":\"comidas\",\"imagem\":\"batata\"}," +

                    "{\"nome\":\"Polenta Frita\",\"preco\":\"R$ 20,00\",\"categoria\":\"comidas\",\"imagem\":\"polenta\"}," +

                    "{\"nome\":\"X-Salada\",\"preco\":\"R$ 28,00\",\"categoria\":\"comidas\",\"imagem\":\"xsalada\"}," +

                    "{\"nome\":\"X-Galinha\",\"preco\":\"R$ 30,00\",\"categoria\":\"comidas\",\"imagem\":\"xgalinha\"}," +

                    "{\"nome\":\"X-Burguer\",\"preco\":\"R$ 32,00\",\"categoria\":\"comidas\",\"imagem\":\"xburguer\"}," +

                    "{\"nome\":\"X-Bacon\",\"preco\":\"R$ 35,00\",\"categoria\":\"comidas\",\"imagem\":\"xbacon\"}" +

                    "]";

            JSONArray array = new JSONArray(json);

            for(int i = 0; i < array.length(); i++){

                JSONObject objeto = array.getJSONObject(i);

                String nome = objeto.getString("nome");
                String preco = objeto.getString("preco");
                String categoria = objeto.getString("categoria");
                String nomeImagem = objeto.getString("imagem");

                if(categoria.equals(categoriaSelecionada)){

                    int imagem = getResources().getIdentifier(
                            nomeImagem,
                            "drawable",
                            getPackageName()
                    );

                    lista.add(new MenuItem(
                            nome,
                            preco,
                            categoria,
                            imagem
                    ));
                }
            }

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}