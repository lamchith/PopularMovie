package com.lmc.popularmovie.utility;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.JsonReader;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * Created by lmarathchathu on 12/7/2015.
 */
public class ParseJson {

    public ArrayList<MovieDetails> parsejson(InputStream in) {
        ArrayList<MovieDetails> list = new ArrayList<MovieDetails>();

        try {
            JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
            try {
                reader.beginObject();

                while (reader.hasNext()) {
                    String name = reader.nextName();
                    if (name.equals("results")) {
                        reader.beginArray();
                        while (reader.hasNext()) {
                            readMovieDetails(reader, list);

                        }
                        reader.endArray();


                    } else {
                        reader.skipValue();
                    }
                }

                reader.endObject();
            } catch (IOException e) {
                e.printStackTrace();
            }


        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return list;
    }


    static ArrayList<MovieDetails> readMovieDetails(JsonReader reader, ArrayList<MovieDetails> details) {
        try {
            reader.beginObject();
            MovieDetails movie = new MovieDetails();
            while (reader.hasNext()) {
                try {

                    String name = reader.nextName();

                    if (name.equals("title")) {
                        movie.title = reader.nextString();
                    } else if (name.equals("poster_path")) {
                        movie.moviePoster = reader.nextString();
                    } else if (name.equals("overview")) {
                        movie.overview = reader.nextString();
                    } else if (name.equals("vote_average")) {
                        movie.userRating = reader.nextString();
                    } else if (name.equals("release_date")) {
                        movie.releaseDate = reader.nextString();
                    } else {
                        reader.skipValue();
                    }
                } catch (IllegalStateException ex) {
                    Log.e("Lamchith", ex.toString());
                }
                //release_date


            }
            details.add(movie);
            Log.d("Lamchith", movie.toString());
            reader.endObject();

        } catch (IOException io) {
        }

        return details;
    }


    public boolean isOnline(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

        return isConnected;
    }


}
