package com.iwars.mine.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.iwars.mine.Model.ModelRiwayat;
import com.iwars.mine.R;

import java.util.List;

public class AdapterRiwayat extends RecyclerView.Adapter<AdapterRiwayat.HolderData> {
    private List<ModelRiwayat> mItems ;
    private Context context;

    public AdapterRiwayat(Context context, List<ModelRiwayat> items)
    {
        this.mItems = items;
        this.context = context;
    }

    @Override
    public HolderData onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_riwayat,parent,false);
        HolderData holderData = new HolderData(layout);
        return holderData;
    }

    @Override
    public void onBindViewHolder(HolderData holder, int position) {
        ModelRiwayat md  = mItems.get(position);
        holder.tvtanggal.setText(md.getTanggal());
        holder.tvpoli.setText(md.getPoli());
        holder.tvdiagnosa.setText(md.getDiagnosa());
        holder.tvtindakan.setText(md.getTindakan());
        holder.tvobat.setText(md.getObat());

        holder.md = md;
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    class HolderData extends RecyclerView.ViewHolder
    {
        TextView tvtanggal,tvpoli,tvdiagnosa, tvtindakan, tvobat;
        ModelRiwayat md;

        public  HolderData (View view)
        {
            super(view);

            tvtanggal = (TextView) view.findViewById(R.id.tanggal);
            tvpoli = (TextView) view.findViewById(R.id.ket_poli);
            tvdiagnosa = (TextView) view.findViewById(R.id.diagnosa);
            tvtindakan = (TextView) view.findViewById(R.id.tindakan);
            tvobat = (TextView) view.findViewById(R.id.resep_obat);

        }
    }

}
