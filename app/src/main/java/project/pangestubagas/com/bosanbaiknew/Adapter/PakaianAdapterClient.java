package project.pangestubagas.com.bosanbaiknew.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import project.pangestubagas.com.bosanbaiknew.DetailPakaianClient;
import project.pangestubagas.com.bosanbaiknew.Models.Pakaian;
import project.pangestubagas.com.bosanbaiknew.R;

/**
 * Created by GJH IT-ASUS on 25/01/2018.
 */

public class PakaianAdapterClient extends RecyclerView.Adapter<PakaianAdapterClient.ViewHolder> {
    private Context context;
    private List<Pakaian> listMenuPakaian;
    String email;

    public PakaianAdapterClient(Context context, List<Pakaian> listMenuPakaian,String email) {
        this.context = context;
        this.listMenuPakaian = listMenuPakaian;
        this.email = email;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_menu_client,parent,false);
        return new PakaianAdapterClient.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Pakaian pakaian = listMenuPakaian.get(position);
        holder.nama.setText(pakaian.getNamaPakaian());
        holder.nama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), DetailPakaianClient.class);
                i.putExtra("idPakaian",pakaian.getId_pakaian());
                i.putExtra("namaPakaian",pakaian.getNamaPakaian());
                i.putExtra("kategori",pakaian.getKategori());
                i.putExtra("harga",pakaian.getHarga());
                i.putExtra("deskripsi",pakaian.getDeskripsi());
                i.putExtra("email",email);
                v.getContext().startActivity(i);
            }
        });
        holder.kategori.setText(pakaian.getKategori());
        holder.harga.setText(pakaian.getHarga());
    }

    @Override
    public int getItemCount() {
        return listMenuPakaian.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nama,kategori,harga;
        public ViewHolder(View itemView) {
            super(itemView);
            nama = itemView.findViewById(R.id.TVNamaClient);
            kategori = itemView.findViewById(R.id.TVKategoriClient);
            harga = itemView.findViewById(R.id.TVHarga);
        }
    }
}
