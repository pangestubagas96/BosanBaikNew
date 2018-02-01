package project.pangestubagas.com.bosanbaiknew.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import project.pangestubagas.com.bosanbaiknew.DetailPakaianAdmin;
import project.pangestubagas.com.bosanbaiknew.Models.Pakaian;
import project.pangestubagas.com.bosanbaiknew.R;

/**
 * Created by GJH IT-ASUS on 16/01/2018.
 */

public class PakaianAdapter extends RecyclerView.Adapter<PakaianAdapter.ViewHolder>{
    private Context context;
    private List<Pakaian> listPakaian;

    public PakaianAdapter(Context context, List<Pakaian> listPakaian) {
        this.context = context;
        this.listPakaian = listPakaian;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_menu,parent,false);
        return new PakaianAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Pakaian pakaian = listPakaian.get(position);
        holder.tvNama.setText(pakaian.getNamaPakaian());
        holder.tvNama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), DetailPakaianAdmin.class);
                i.putExtra("idPakaian",pakaian.getId_pakaian());
                i.putExtra("namaPakaian",pakaian.getNamaPakaian());
                i.putExtra("kategori",pakaian.getKategori());
                i.putExtra("harga",pakaian.getHarga());
                i.putExtra("deskripsi",pakaian.getDeskripsi());
                i.putExtra("ukuran",pakaian.getUkuran());
                v.getContext().startActivity(i);
            }
        });
        holder.tvKategori.setText(pakaian.getKategori());
    }

    @Override
    public int getItemCount() {
        return listPakaian.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvNama,tvKategori;
        public ViewHolder(View itemView) {
            super(itemView);
            tvNama = itemView.findViewById(R.id.TVNama);
            tvKategori = itemView.findViewById(R.id.TVKategori);
        }
    }
}
