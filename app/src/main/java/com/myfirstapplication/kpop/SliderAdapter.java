package com.myfirstapplication.kpop;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class SliderAdapter extends PagerAdapter {
    LayoutInflater inflater;
    Context context;

    public int[] imageArray = {R.drawable.musicisdream,R.drawable.musicismeditating,R.drawable.musicislife};
    public String[] titleArray = {"Music is Dream","Music is Meditating","Music is Life"};
    public  String[] descriptionArray = {"A prevailing mood or atmosphere in some aspect of your life. Ask yourself how the music or musician makes you feel while listening to it. Consider the words to the song that you are dreaming about for additional meaning.","Meditation has the power to improve digestion, to strengthen the immune system, maintain normal cholesterol and blood pressure levels. ... Calming the mind, body, and spirit through meditation can help the body to rest more deeply so that it can focus on health and healing.","Music can raise someone's mood, get them excited, or make them calm and relaxed. Music also - and this is important - allows us to feel nearly or possibly all emotions that we experience in our lives. ... It is an important part of their lives and fills a need or an urge to create music."};
    public int[] backgroundColorArray = {Color.rgb(55,55,55),
            Color.rgb(239,85,85),
            Color.rgb(110,49,89),
            Color.rgb(1,188,212)
    };
    public SliderAdapter(Context context){

        this.context = context;
    }
    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return (view==object);
    }

    @Override
    public int getCount() {
        return titleArray.length;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout)object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        inflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.slide,container,false);

        LinearLayout linearLayout = (LinearLayout)view.findViewById(R.id.linearLayout);
        ImageView imageView = (ImageView)view.findViewById(R.id.slideimg);
        TextView t1_title = (TextView)view.findViewById(R.id.txtTitle);
        TextView t2_desc = (TextView)view.findViewById(R.id.txtDescrition);
        imageView.setImageResource(imageArray[position]);
        t1_title.setText(titleArray[position]);
        t2_desc.setText(descriptionArray[position]);
        container.addView(view);
        return view;
    }
}
