package project.pangestubagas.com.bosanbaiknew.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

import project.pangestubagas.com.bosanbaiknew.Models.Cart;
import project.pangestubagas.com.bosanbaiknew.R;

/**
 * Created by GJH IT-ASUS on 28/01/2018.
 */

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder>{
    private Context context;
    private List<Cart> listCart;
    DatabaseReference databaseBosanBaik;

    public CartAdapter(Context context, List<Cart> listCart) {
        this.context = context;
        this.listCart = listCart;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart,parent,false);
        return new CartAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Cart cart = listCart.get(position);
        holder.txtNama.setText(cart.getNamaPakaian());
        holder.txtjumlah.setText(cart.getJumlah());
        holder.txtHarga.setText(cart.getHarga());
        holder.btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = cart.getIdCart();
                databaseBosanBaik = FirebaseDatabase.getInstance().getReference("Cart").child(id);
                databaseBosanBaik.removeValue();
                Toast.makeText(context, "Data List Cart Telah Dihapus", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listCart.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtNama,txtjumlah,txtHarga;
        Button btnDel;
        public ViewHolder(View itemView) {
            super(itemView);
            txtNama = itemView.findViewById(R.id.txtNamaCart);
            txtjumlah = itemView.findViewById(R.id.txtJumlahCart);
            txtHarga = itemView.findViewById(R.id.HargaChart);
            btnDel = itemView.findViewById(R.id.btnRemoveCart);
        }
    }
}
