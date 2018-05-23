package com.example.zed.issues_solutions;

import android.app.Fragment;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

public class VnReviewFragment extends Fragment {

    ListView                lv;
    ArrayList<String>       arrayLink;
    ArrayList<News>    arrayList;
    NewsAdapter             adapter;
    String                  vnreviewLink = "http://vnreview.vn/feed/-/rss/13626/13628";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_vnreview, container, false);

        lv = view.findViewById(R.id.lvVnReview);
        arrayList       = new ArrayList<>();
        arrayLink       = new ArrayList<>();


        new readRSS().execute(vnreviewLink);
        adapter         = new NewsAdapter(getActivity(), R.layout.news_row, arrayList);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), NewsDetailActivity.class);
                intent.putExtra("newsLink", arrayLink.get(position));
                startActivity(intent);
            }
        });

        return view;
    }

    public class readRSS extends AsyncTask<String , Void, String> {

        @Override
        protected String doInBackground(String... strings) {

            StringBuilder builder = new StringBuilder();

            try {
                URL url = new URL(strings[0]);
                InputStreamReader inputStreamReader = new InputStreamReader(url.openConnection().getInputStream());
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                String line;
                while ((line = bufferedReader.readLine()) != null){
                    builder.append(line);
                }

                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return builder.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            XMLDOMParser parser  = new XMLDOMParser();
            Document document    = parser.getDocument(s);
            NodeList nodeList    = document.getElementsByTagName("item");

            String tieuDe, link, img, summary, date, author;

            for(int i = 0; i < nodeList.getLength(); i++){
                Element element = (Element) nodeList.item(i);

                tieuDe  = parser.getValue(element, "title");
                link    = parser.getValue(element, "link");
                img     = parser.getValue(element, "guid");
                summary = parser.getValue(element, "description");
                date    = parser.getValue(element, "pubDate");
                author  = parser.getValue(element, "author");

                arrayLink.add(link);
                arrayList.add(new News(img, tieuDe, summary, author, date));
            }
            adapter.notifyDataSetChanged();
        }
    }

}
