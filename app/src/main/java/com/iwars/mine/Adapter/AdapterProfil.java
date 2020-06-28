package com.iwars.mine.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.iwars.mine.Model.ModelProfil;
import com.iwars.mine.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterProfil extends RecyclerView.Adapter<AdapterProfil.HolderData> {
    private List<ModelProfil> mItems ;
    private Context context;

    public AdapterProfil(Context context, List<ModelProfil> items)
    {
        this.mItems = items;
        this.context = context;
    }

    @Override
    public AdapterProfil.HolderData onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_profil,parent,false);
        AdapterProfil.HolderData holderData = new AdapterProfil.HolderData(layout);
        return holderData;
    }

    @Override
    public void onBindViewHolder(AdapterProfil.HolderData holder, int position) {
        ModelProfil md  = mItems.get(position);
        //holder.ivfoto.setImageURI(md.getFoto());
        //Glide.with(context).load(md.getFoto()).into(holder.ivfoto);
        //Picasso.get().load(md.getFoto()).into(holder.ivfoto);
        holder.tvid.setText(md.getNo_identitas());
        holder.tvnama.setText(md.getNama());
        holder.tvjenis_kelamin.setText(md.getJenis_kelamin());
        holder.tvno_hp.setText(md.getNo_hp());
        holder.tvalamat.setText(md.getAlamat());
        //holder.tvttl.setText(md.getTempat_lahir() + "," + md.getTanggal_lahir());
        holder.tvttl.setText(md.getTtl());

        holder.md = md;
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    class HolderData extends RecyclerView.ViewHolder
    {
        TextView tvnama,tvjenis_kelamin, tvalamat, tvttl, tvno_hp, tvid;
        //ImageView ivfoto;
        ModelProfil md;

        public  HolderData (View view)
        {
            super(view);

            //ivfoto = (ImageView) view.findViewById(R.id.foto);
            tvid = (TextView) view.findViewById(R.id.tvID);
            tvnama = (TextView) view.findViewById(R.id.tvNama);
            tvjenis_kelamin = (TextView) view.findViewById(R.id.tvJK);
            tvno_hp = (TextView) view.findViewById(R.id.tvNo);
            tvalamat = (TextView) view.findViewById(R.id.tvAlamat);
            tvttl = (TextView) view.findViewById(R.id.tvTTL);

        }
    }
}
