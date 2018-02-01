package project.pangestubagas.com.bosanbaiknew.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;

import java.util.List;

import project.pangestubagas.com.bosanbaiknew.Models.Cart;
import project.pangestubagas.com.bosanbaiknew.R;

/**
 * Created by GJH IT-ASUS on 30/01/2018.
 */

public class KonfirmasiAdapter extends RecyclerView.Adapter<KonfirmasiAdapter.ViewHolder> {
    private Context context;
    private List<Cart> listCart;
    DatabaseReference databaseBosanBaik;

    public KonfirmasiAdapter(Context context, List<Cart> listCart) {
        this.context = context;
        this.listCart = listCart;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_konfirmasi,parent,false);
        return new KonfirmasiAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Cart cart = listCart.get(position);
        holder.namaPakaianItem.setText(cart.getNamaPakaian());
        holder.JumlahPakaianItem.setText(cart.getJumlah());
    }

    @Override
    public int getItemCount() {
        return listCart.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView namaPakaianItem,JumlahPakaianItem;
        public ViewHolder(View itemView) {
            super(itemView);
            namaPakaianItem = itemView.findViewById(R.id.namaPakaianItem);
            JumlahPakaianItem = itemView.findViewById(R.id.JumlahPakaianItem);
        }
    }
}
