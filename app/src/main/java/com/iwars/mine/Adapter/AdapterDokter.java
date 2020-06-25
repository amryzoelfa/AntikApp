package com.iwars.mine.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.iwars.mine.Model.ModelDokter;
import com.iwars.mine.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterDokter extends RecyclerView.Adapter<AdapterDokter.HolderData> {
    private List<ModelDokter> mItems ;
    private Context context;

    public AdapterDokter(Context context, List<ModelDokter> items)
    {
        this.mItems = items;
        this.context = context;
    }

    @Override
    public AdapterDokter.HolderData onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_dokter,parent,false);
        AdapterDokter.HolderData holderData = new AdapterDokter.HolderData(layout);
        return holderData;
    }

    @Override
    public void onBindViewHolder(AdapterDokter.HolderData holder, int position) {
        ModelDokter md  = mItems.get(position);
        //holder.ivfoto.setImageURI(md.getFoto());
        //Glide.with(context).load(md.getFoto()).into(holder.ivfoto);
        Picasso.get().load(md.getFoto()).into(holder.ivfoto);
        holder.tvnama.setText(md.getNama());
        holder.tvjenis_kelamin.setText(md.getJenis_kelamin());
        holder.tvno_hp.setText(md.getNo_hp());
        holder.tvalamat.setText(md.getAlamat());
        holder.tvket_akses.setText(md.getKet_akses());

        holder.md = md;
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    class HolderData extends RecyclerView.ViewHolder
    {
        TextView tvnama,tvjenis_kelamin, tvalamat, tvket_akses, tvno_hp;
        ImageView ivfoto;
        ModelDokter md;

        public  HolderData (View view)
        {
            super(view);

            ivfoto = (ImageView) view.findViewById(R.id.foto);
            tvnama = (TextView) view.findViewById(R.id.nama);
            tvjenis_kelamin = (TextView) view.findViewById(R.id.jenis_kelamin);
            tvno_hp = (TextView) view.findViewById(R.id.no_hp);
            tvalamat = (TextView) view.findViewById(R.id.alamat);
            tvket_akses = (TextView) view.findViewById(R.id.ket_akses);

        }
    }

}
