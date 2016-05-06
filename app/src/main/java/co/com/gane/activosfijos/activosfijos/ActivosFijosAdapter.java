package co.com.gane.activosfijos.activosfijos;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import co.com.gane.activosfijos.R;
import co.com.gane.activosfijos.ayuda.Utilidades;
import co.com.gane.activosfijos.orm.entity.Activosfijos;

public class ActivosFijosAdapter extends RecyclerView.Adapter<ActivosFijosAdapter.ActivosFijosViewHolder>{

    List<Activosfijos> activosFijos;
    List<Activosfijos> activosFijosFiltrado;
    ActivosFijosHandler handler;
    Context ctx;

    ActivosFijosAdapter(List<Activosfijos> puntos, ActivosFijosHandler handler, Context ctx){
        this.activosFijos = puntos;
        this.handler = handler;
        this.ctx = ctx;
    }

    public class ActivosFijosViewHolder extends RecyclerView.ViewHolder {
        View view;
        TextView txtIdActivoFijoCvActivosFijosItem;
        TextView txtNombreCvActivosFijosItem;
        TextView txtUbicacionCvActivosFijosItem;

        ActivosFijosViewHolder(View itemView) {
            super(itemView);
            view = itemView;

            txtIdActivoFijoCvActivosFijosItem = (TextView)itemView.findViewById(R.id.txtIdActivoFijoCvActivoFijoItem);
            txtNombreCvActivosFijosItem = (TextView)itemView.findViewById(R.id.txtNombreCvActivoFijoItem);
            txtUbicacionCvActivosFijosItem = (TextView)itemView.findViewById(R.id.txtUbicacionCvActivoFijoItem);

            itemView.setOnClickListener(handler);
        }
    }

    @Override
    public ActivosFijosViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cv_activos_fijos_layout, parent, false);
        ActivosFijosViewHolder pvh = new ActivosFijosViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(ActivosFijosViewHolder holder, int position) {
        Activosfijos objActivosfijos = this.activosFijos.get(position);

        holder.txtIdActivoFijoCvActivosFijosItem.setText(objActivosfijos.getId_activo());
        holder.txtNombreCvActivosFijosItem.setText(objActivosfijos.getNombre());
        holder.txtUbicacionCvActivosFijosItem.setText(objActivosfijos.getUbicacion());

        holder.view.setTag(objActivosfijos);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return activosFijos.size();
    }
}
