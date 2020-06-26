package com.iwars.mine.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.iwars.mine.Model.ModelListUmum;
import com.iwars.mine.R;

import java.util.List;

public class AdapterListUmum extends RecyclerView.Adapter<AdapterListUmum.HolderData> {

    private List<ModelListUmum> mItems ;
    private Context context;

    public AdapterListUmum(Context context, List<ModelListUmum> items)
    {
        this.mItems = items;
        this.context = context;
    }

    @Override
    public AdapterListUmum.HolderData onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_umum,parent,false);
        AdapterListUmum.HolderData holderData = new AdapterListUmum.HolderData(layout);
        return holderData;
    }

    @Override
    public void onBindViewHolder(AdapterListUmum.HolderData holder, int position) {
        ModelListUmum md  = mItems.get(position);
        //holder.tvtanggal.setText(md.getTanggal());
        holder.tvno.setText(md.getNo_antrian());
        holder.tvnama.setText(md.getNama());
        holder.tvstatus.setText(md.getKet_status());

        holder.md = md;
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    class HolderData extends RecyclerView.ViewHolder
    {
        TextView tvtanggal,tvstatus,tvno, tvnama;
        ModelListUmum md;

        public  HolderData (View view)
        {
            super(view);

            //tvtanggal = (TextView) view.findViewById(R.id.tanggal);
            tvno = (TextView) view.findViewById(R.id.no_antrian);
            tvnama = (TextView) view.findViewById(R.id.nama);
            tvstatus = (TextView) view.findViewById(R.id.ket_status);
        }
    }
}
