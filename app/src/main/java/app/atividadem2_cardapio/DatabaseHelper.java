package app.atividadem2_cardapio;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "cardapio.db";

    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "CREATE TABLE cardapio (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nome TEXT," +
                "preco TEXT," +
                "categoria TEXT," +
                "imagem TEXT)";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS cardapio");

        onCreate(db);
    }

    public void inserirItem(
            String nome,
            String preco,
            String categoria,
            String imagem
    ){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("nome", nome);
        values.put("preco", preco);
        values.put("categoria", categoria);
        values.put("imagem", imagem);

        db.insert("cardapio", null, values);

        db.close();
    }

    public List<MenuItem> listarItens(String categoriaSelecionada){

        List<MenuItem> lista = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT * FROM cardapio WHERE categoria = ?",
                new String[]{categoriaSelecionada}
        );

        if(cursor.moveToFirst()){

            do {

                String nome = cursor.getString(1);
                String preco = cursor.getString(2);
                String categoria = cursor.getString(3);
                String nomeImagem = cursor.getString(4);

                int imagem = App.context
                        .getResources()
                        .getIdentifier(
                                nomeImagem,
                                "drawable",
                                App.context.getPackageName()
                        );

                lista.add(new MenuItem(
                        nome,
                        preco,
                        categoria,
                        imagem
                ));

            } while(cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return lista;
    }

    public void limparTabela(){

        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("DELETE FROM cardapio");

        db.close();
    }
}