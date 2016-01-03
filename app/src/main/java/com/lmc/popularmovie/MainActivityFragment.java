package com.lmc.popularmovie;

import android.app.Activity;
import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;

import com.lmc.popularmovie.ui.helper.MovieDetailsAdapter;
import com.lmc.popularmovie.utility.MovieApplication;
import com.lmc.popularmovie.utility.MovieDetails;
import com.lmc.popularmovie.utility.ParseJson;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
    // following is the api key for movie DB api

    ListView listview;
    Activity parent;
    MovieDetailsAdapter adapter;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_main, container, false);
        listview = (ListView) view.findViewById(R.id.movie_listView);
        listview.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {

            }

            @Override
            public void onScroll(AbsListView absListView, int i, int i1, int i2) {

            }
        });


        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        parent= getActivity();
        //listview.setAdapter(new MovieDetailsAdapter( getActivity(),null));

        try {
            // keep the movie api key here
            new DownloadTask().execute(new URL("http://api.themoviedb.org/3/discover/movie?sort_by=popularity.desc&api_key="));
        }
        catch(MalformedURLException ex){
            Log.e("Lamchith",ex.getMessage());

        }

    }



    private class DownloadTask extends AsyncTask<URL, Integer, ArrayList<MovieDetails>> {
        protected  ArrayList<MovieDetails> doInBackground(URL... urls) {

            ParseJson parseJson= new ParseJson();
            ArrayList<MovieDetails> list=null;
            URL url = null;
            try {
                url = urls[0];;
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                list=parseJson.parsejson(in);
                Log.i("Lamchith", "got the list" + list.size());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return list;
        }


        protected void onPostExecute(ArrayList<MovieDetails> list) {

            MovieDetailsAdapter adapter=new MovieDetailsAdapter(MovieApplication.context,list);
            listview.setAdapter(adapter);


        }
    }
}
