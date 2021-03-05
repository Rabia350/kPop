package com.myfirstapplication.kpop;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

public class VoiceListAdapter extends RecyclerView.Adapter<VoiceListAdapter.AudioViewHolder> {
    private File[] allFiles;
    Fragment fragment;
    private TimeAgo timeago;
    private onItemListClick onItemListClick;
    public static Context myContext;
    RecyclerView recyclerView;
    public VoiceListAdapter(File[] allFiles , onItemListClick onItemListClick, Context context, Fragment fragment, RecyclerView recyclerView){
        this.allFiles = allFiles;
        this.recyclerView = recyclerView;
        this.onItemListClick =onItemListClick;
 //       this.myContext=context;
        this.fragment = fragment;

    }
    @NonNull
    @Override
    public VoiceListAdapter.AudioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_list_item_2,parent,false);
        timeago= new TimeAgo();
        return new AudioViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VoiceListAdapter.AudioViewHolder holder, final int position) {
        holder.list_title.setText(allFiles[position].getName());
        holder.list_date.setText(timeago.getTimeAgo(allFiles[position].lastModified()));
        final PopupMenu dropDownMenu = new PopupMenu(myContext, holder.btnMore);
        final Menu menu = dropDownMenu.getMenu();
        menu.add(0, 0, 0, "Delete");
//        menu.add(0, 1, 0, "Share");

        dropDownMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case 0:
                        deleteFiles(allFiles[position].getAbsolutePath(), position);
                        return true;
//                    case 1:
//                        // item ID 1 was clicked
//                        return true;
                }
                return false;
            }
        });

        holder.btnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dropDownMenu.show();
            }
        });

    }

    public void deleteFiles(String path, int i) {
        File file = new File(path);

        if (file.exists()) {
            String deleteCmd = "rm -r " + path;
            Runtime runtime = Runtime.getRuntime();
            try {
                runtime.exec(deleteCmd);
                File[] allFiles1 = removeTheElement(allFiles, i);
                Toast.makeText(myContext, "done", Toast.LENGTH_SHORT).show();
               if (fragment!=null){
                   if (fragment.getClass().getName().equals(AudioListFragment.class.getName())){
                       AudioListFragment audioListFragment = new AudioListFragment();
                       audioListFragment.populateList(allFiles1, recyclerView);
                   }
               }
                notifyDataSetChanged();
            } catch (IOException e) {
                Toast.makeText(myContext, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }

    }

    public static File[] removeTheElement(File[] arr,
                                         int index)
    {

        // If the array is empty
        // or the index is not in array range
        // return the original array
        if (arr == null
                || index < 0
                || index >= arr.length) {

            return arr;
        }

        // Create another array of size one less
        File[] anotherArray = new File[arr.length - 1];

        // Copy the elements except the index
        // from original array to the other array
        for (int i = 0, k = 0; i < arr.length; i++) {

            // if the index is
            // the removal element index
            if (i == index) {
                continue;
            }

            // if the index is not
            // the removal element index
            anotherArray[k++] = arr[i];
        }

        // return the resultant array
        return anotherArray;
    }

    @Override
    public int getItemCount() {
        return allFiles.length ;
    }
    public class AudioViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView list_image;
        private TextView list_title;
        private  TextView list_date;
        private  TextView save;
        private ImageButton btnMore;
        public AudioViewHolder(@NonNull View itemView) {
            super(itemView);

            list_image= itemView.findViewById(R.id.list_imageview);
            list_title= itemView.findViewById(R.id.list_title);
            list_date= itemView.findViewById(R.id.list_date);
            save=itemView.findViewById(R.id.save);
            btnMore = itemView.findViewById(R.id.btnMore);
            save.setOnClickListener(this);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.save:
                    Log.e("click","click");
                    SharedPreferences prefs=myContext.getSharedPreferences("Settings", Context.MODE_PRIVATE);
                    SharedPreferences.Editor edit=prefs.edit();
                    ArrayList<String> arrayList=new ArrayList<String>();
                    arrayList.add(allFiles[getAdapterPosition()].toString());
                    Set<String> set = new HashSet<String>();
                    set.addAll(arrayList);
                    edit.putStringSet("Fav", set);
                    edit.commit();
                    Toast.makeText(myContext,"Song Saved Successfully", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    onItemListClick.onclickListner(allFiles[getAdapterPosition()] ,getAdapterPosition());

            }
        }
    }
    public interface onItemListClick{
        void onclickListner(File file,int position);
    }
}
