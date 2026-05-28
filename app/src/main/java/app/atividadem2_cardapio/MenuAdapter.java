package app.atividadem2_cardapio;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder> {

    List<MenuItem> lista;

    public MenuAdapter(List<MenuItem> lista) {
        this.lista = lista;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_cardapio, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        MenuItem item = lista.get(position);

        holder.txtNome.setText(item.getNome());

        holder.txtPreco.setText(item.getPreco());

        holder.imgPrato.setImageResource(item.getImagem());
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtNome;
        TextView txtPreco;
        ImageView imgPrato;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtNome = itemView.findViewById(R.id.txtNome);

            txtPreco = itemView.findViewById(R.id.txtPreco);

            imgPrato = itemView.findViewById(R.id.imgPrato);
        }
    }
}