package com.myfirstapplication.kpop;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class VoiceListAdapter extends RecyclerView.Adapter<VoiceListAdapter.AudioViewHolder> {

    private File[] allFiles;
    private TimeAgo timeago;
    private onItemListClick onItemListClick;
    public VoiceListAdapter(File[] allFiles ,onItemListClick onItemListClick){
        this.allFiles = allFiles;
        this.onItemListClick =onItemListClick;

    }
    @NonNull
    @Override
    public VoiceListAdapter.AudioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_list_item,parent,false);
        timeago= new TimeAgo();
        return new AudioViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VoiceListAdapter.AudioViewHolder holder, int position) {
        holder.list_title.setText(allFiles[position].getName());
        holder.list_date.setText(timeago.getTimeAgo(allFiles[position].lastModified()));
    }

    @Override
    public int getItemCount() {
        return allFiles.length ;
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
            onItemListClick.onclickListner(allFiles[getAdapterPosition()] ,getAdapterPosition());
        }
    }
    public interface onItemListClick{
        void onclickListner(File file,int position);
    }
}
