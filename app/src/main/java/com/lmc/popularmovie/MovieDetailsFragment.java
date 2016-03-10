package com.lmc.popularmovie;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.lmc.popularmovie.utility.MovieApplication;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;


/**
 * A placeholder fragment containing a simple view.
 */
public class MovieDetailsFragment extends Fragment {
    // following is the api key for movie DB api


    ArrayList<String> list = null;

    public MovieDetailsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ImageView imagePoster = (ImageView) view.findViewById(R.id.imageView_poster);

        TextView textViewUserRating = (TextView) view.findViewById(R.id.textView_userRating);
        TextView textViewReleaseDate = (TextView) view.findViewById(R.id.textView_releaseDate);
        TextView textViewOverview = (TextView) view.findViewById(R.id.textView_overview);
        TextView textViewTitle = (TextView) view.findViewById(R.id.textView_title);
        if (list != null) {
            textViewTitle.setText(list.get(0));
            Picasso.with(MovieApplication.context).load("http://image.tmdb.org/t/p/w185/" + list.get(1)).into(imagePoster);
            textViewOverview.setText(list.get(2));
            textViewUserRating.setText(list.get(3));
            textViewReleaseDate.setText(list.get(4));
        }
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
            list = getArguments().getStringArrayList("com.lmc.popularmovie.details");


    }


}
