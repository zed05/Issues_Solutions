package com.example.zed.issues_solutions;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.koushikdutta.ion.Ion;

import java.util.ArrayList;

public class NewsAdapter extends BaseAdapter{
    private Context context;
    private int layout;
    private ArrayList<News> newsList;

    public NewsAdapter(Context context, int layout, ArrayList<News> newsList) {
        this.context = context;
        this.layout = layout;
        this.newsList = newsList;
    }

    @Override
    public int getCount() {
        return newsList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public class ViewHolder{
        ImageView imgNews;
        TextView tvTitle, tvSummary, tvAuthor, tvDate;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        
        ViewHolder holder;
        if(convertView == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);

            holder.imgNews      = convertView.findViewById(R.id.newsImageImageView);
            holder.tvTitle      = convertView.findViewById(R.id.newsTitleTextView);
            holder.tvSummary    = convertView.findViewById(R.id.summaryTextView);
            holder.tvAuthor     = convertView.findViewById(R.id.authorTextView);
            holder.tvDate       = convertView.findViewById(R.id.dateCreateTextView);

            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        News news = newsList.get(position);

        Ion.with(holder.imgNews).load(news.getImg());
        holder.tvTitle.setText(news.getTitle());
        holder.tvSummary.setText(news.getSummary());
        if(news.getAuthor() == null){
            holder.tvAuthor.setText("");
        }
        else {
            holder.tvAuthor.setText(news.getAuthor());
        }

        holder.tvDate.setText(news.getDate());
        
        
        return convertView;
    }
}
