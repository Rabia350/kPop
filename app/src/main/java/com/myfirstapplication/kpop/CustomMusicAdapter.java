package com.myfirstapplication.kpop;

import android.content.Context;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomMusicAdapter extends BaseAdapter implements Filterable {
    private Context context;
    private int layout;
    private ArrayList<Music> arrayList;
    private ArrayList<Music> displayArrayList;
    private MediaPlayer mediaPlayer;
    private Boolean flag = true;
    public CustomMusicAdapter(Context context, int layout, ArrayList<Music> arrayList) {
        this.context = context;
        this.layout = layout;
        this.arrayList = arrayList;
        this.displayArrayList=arrayList;
    }
    @Override
    public int getCount() {
        return displayArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint,FilterResults results) {

                displayArrayList = (ArrayList<Music>) results.values; // has the filtered values
                notifyDataSetChanged();  // notifies the data with new filtered values
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();        // Holds the results of a filtering operation in values
                ArrayList<Music> FilteredArrList = new ArrayList<Music>();

                if (arrayList == null) {
                    arrayList = new ArrayList<Music>(displayArrayList); // saves the original data in mOriginalValues
                }

                /********
                 *
                 *  If constraint(CharSequence that is received) is null returns the mOriginalValues(Original) values
                 *  else does the Filtering and returns FilteredArrList(Filtered)
                 *
                 ********/
                if (constraint == null || constraint.length() == 0) {

                    // set the Original result to return
                    results.count = arrayList.size();
                    results.values = arrayList;
                } else {
                    constraint = constraint.toString().toLowerCase();
                    for (int i = 0; i < arrayList.size(); i++) {
                        String data = arrayList.get(i).name;
                        if (data.toLowerCase().startsWith(constraint.toString())) {
                            FilteredArrList.add(new Music(arrayList.get(i).name,arrayList.get(i).singer,arrayList.get(i).song));
                        }
                    }
                    // set the Filtered result to return
                    results.count = FilteredArrList.size();
                    results.values = FilteredArrList;
                }
                return results;
            }
        };
        return filter;
    }

    private class ViewHolder {
        TextView txtName, txtSinger;
        ImageView ivPlay, ivStop;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if(convertView == null){
            viewHolder = new ViewHolder();
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = layoutInflater.inflate(layout, null);
            viewHolder.txtName = (TextView) convertView.findViewById(R.id.txtName);
            viewHolder.txtSinger = (TextView) convertView.findViewById(R.id.txtSinger);
            viewHolder.ivPlay = (ImageView) convertView.findViewById(R.id.ivPlay);
            viewHolder.ivStop = (ImageView) convertView.findViewById(R.id.ivStop);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final Music music = displayArrayList.get(position);

        viewHolder.txtName.setText(music.getName());
        viewHolder.txtSinger.setText(music.getSinger());

        // play music
        viewHolder.ivPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag){
                    mediaPlayer = MediaPlayer.create(context, music.getSong());
                    flag = false;
                }
                if(mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                    viewHolder.ivPlay.setImageResource(R.drawable.startt);
                } else {
                    mediaPlayer.start();
                    viewHolder.ivPlay.setImageResource(R.drawable.pauce);
                }
            }
        });

        // stop
        viewHolder.ivStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!flag) {
                    mediaPlayer.stop();
                    mediaPlayer.release();
                    flag = true;
                }
                viewHolder.ivPlay.setImageResource(R.drawable.startt);
            }
        });

        return convertView;
    }
}
