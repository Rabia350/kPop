package com.myfirstapplication.kpop;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.util.ArrayList;

public class audioListAdapter extends RecyclerView.Adapter<audioListAdapter.AudioViewHolder> {
    private ArrayList<File> allFiles;
    private TimeAgo timeago;
    private onItemListClick onItemListClick;
    public audioListAdapter(ArrayList<File> allFiles ,onItemListClick onItemListClick){
        this.allFiles = allFiles;
        this.onItemListClick =onItemListClick;

    }
    @NonNull
    @Override
    public AudioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fav_list_item,parent,false);
        timeago= new TimeAgo();
        return new AudioViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AudioViewHolder holder, int position) {
        holder.list_title.setText(allFiles.get(position).getName());
        holder.list_date.setText(timeago.getTimeAgo(allFiles.get(position).lastModified()));

    }

    @Override
    public int getItemCount() {
        return allFiles.size();

    }

    public class AudioViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView list_image;
        private TextView list_title;
        private  TextView list_date;
        public AudioViewHolder(@NonNull View itemView) {
            super(itemView);

            list_image= itemView.findViewById(R.id.list_imageview);
            list_title= itemView.findViewById(R.id.list_title);
            list_date= itemView.findViewById(R.id.list_date);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            onItemListClick.onclickListner(allFiles.get(getAdapterPosition()) ,getAdapterPosition());
        }
    }
    public interface onItemListClick{
        void onclickListner(File file,int position);
    }
}

