package co.com.gane.activosfijos.puntos;

import android.support.annotation.BoolRes;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import co.com.gane.activosfijos.R;
import co.com.gane.activosfijos.orm.entity.Bodegas;

/**
 * Created by HoN on 15/04/2016.
 */
public class PuntosAdapter extends RecyclerView.Adapter<PuntosAdapter.PuntosViewHolder> implements Filterable{

    List<Bodegas> puntos;
    List<Bodegas> puntosFiltrado;
    PuntosHandler handler;

    PuntosAdapter(List<Bodegas> puntos, PuntosHandler handler){
        this.puntos = puntos;
        this.handler = handler;
    }

    @Override
    public Filter getFilter() {
        return null;
    }

    public class PuntosViewHolder extends RecyclerView.ViewHolder {

        CardView cvPuntosItem;
        TextView txtCodigoCvPuntosItem;
        TextView txtTituloCvPuntosItem;
        TextView txtDireccionCvPuntosItem;

        PuntosViewHolder(View itemView) {
            super(itemView);
            cvPuntosItem = (CardView)itemView.findViewById(R.id.cvPuntosItem);
            txtCodigoCvPuntosItem = (TextView)itemView.findViewById(R.id.txtCodigoCvPuntosItem);
            txtTituloCvPuntosItem = (TextView)itemView.findViewById(R.id.txtTituloCvPuntosItem);
            txtDireccionCvPuntosItem = (TextView)itemView.findViewById(R.id.txtDireccionCvPuntosItem);

            itemView.setOnClickListener(PuntosAdapter.this.handler);
        }
    }

    @Override
    public PuntosViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cv_puntos_layout, parent, false);
        PuntosViewHolder pvh = new PuntosViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(PuntosViewHolder holder, int position) {
        holder.txtCodigoCvPuntosItem.setText(puntos.get(position).getCodigo());
        holder.txtTituloCvPuntosItem.setText(puntos.get(position).getNombre());
        holder.txtDireccionCvPuntosItem.setText(puntos.get(position).getDireccion());
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return puntos.size();
    }

    public List<Bodegas> filtrar (String filtro){

        this.puntosFiltrado = new ArrayList<Bodegas>();
        int count = getItemCount();

        String titulo, direccion;

        Boolean valido = false;
        for (int i = 0; i < count; i++) {
            titulo = this.puntos.get(i).getNombre();
            direccion = this.puntos.get(i).getDireccion();
            if (titulo.toLowerCase().contains(filtro.toLowerCase()) || direccion.toLowerCase().contains(filtro.toLowerCase())) {
                this.puntosFiltrado.add(this.puntos.get(i));
            }
        }
        return this.puntosFiltrado;
    }
}
