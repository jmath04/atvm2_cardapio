package app.atividadem2_cardapio;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
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

        String categoria =
                getIntent().getStringExtra("categoria");

        txtCategoria.setText(categoria);

        recyclerView.setLayoutManager(
                new LinearLayoutManager(this)
        );

        carregarJson(categoria);
    }

    private void carregarJson(String categoriaSelecionada){

        new Thread(() -> {

            try {

                URL url = new URL("https://api.jsonbin.io/v3/b/6a189dc5ddf5aa59f7716336/latest");

                HttpURLConnection conexao =
                        (HttpURLConnection) url.openConnection();

                conexao.setRequestProperty(
                        "6a189dc5ddf5aa59f7716336",
                        "$2a$10$19A1SJa/0MXILSmL8fDVx.DsPzgPYYGUSgqIJSW82mX2XjvKIT9FS"
                );

                conexao.setRequestMethod("GET");

                BufferedReader reader =
                        new BufferedReader(
                                new InputStreamReader(
                                        conexao.getInputStream()
                                )
                        );

                StringBuilder json = new StringBuilder();

                String linha;

                while((linha = reader.readLine()) != null){

                    json.append(linha);
                }

                JSONObject jsonObject =
                        new JSONObject(json.toString());

                JSONArray array =
                        jsonObject.getJSONArray("record");

                lista.clear();

                DatabaseHelper db =
                        new DatabaseHelper(this);

                db.limparTabela();

                for(int i = 0; i < array.length(); i++){

                    JSONObject objeto =
                            array.getJSONObject(i);

                    String nome =
                            objeto.getString("nome");

                    String preco =
                            objeto.getString("preco");

                    String categoria =
                            objeto.getString("categoria");

                    String nomeImagem =
                            objeto.getString("imagem");

                    db.inserirItem(
                            nome,
                            preco,
                            categoria,
                            nomeImagem
                    );

                    if(categoria.equals(categoriaSelecionada)){

                        int imagem =
                                getResources().getIdentifier(
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

                runOnUiThread(() -> {

                    recyclerView.setAdapter(
                            new MenuAdapter(lista)
                    );
                });

            } catch (Exception e){

                DatabaseHelper db =
                        new DatabaseHelper(this);

                lista.clear();

                List<MenuItem> itensOffline =
                        db.listarItens(categoriaSelecionada);

                for(MenuItem item : itensOffline){

                    lista.add(new MenuItem(
                            item.getNome(),
                            "A consultar",
                            item.getCategoria(),
                            item.getImagem()
                    ));
                }

                runOnUiThread(() -> {

                    recyclerView.setAdapter(
                            new MenuAdapter(lista)
                    );
                });
            }

        }).start();
    }
}