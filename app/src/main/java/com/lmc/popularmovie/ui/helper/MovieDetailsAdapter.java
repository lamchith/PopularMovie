package com.lmc.popularmovie.ui.helper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lmc.popularmovie.R;
import com.lmc.popularmovie.utility.MovieDetails;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by lmarathchathu on 12/26/2015.
 */
public class MovieDetailsAdapter  extends BaseAdapter{

    private ArrayList<MovieDetails> details;
    private Context context;
    public MovieDetailsAdapter(Context context,ArrayList<MovieDetails> details) {
        this.details=details;
        this.context=context;
    }

    @Override
    public int getCount() {
        return details.size();
    }

    @Override
    public Object getItem(int i) {
        return details.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        MovieDetails entry = details.get(i);
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_view_lay_out,viewGroup,false);
        }
        TextView title = (TextView) view.findViewById(R.id.textView_title);
        title.setText(entry.title);

        TextView description = (TextView) view.findViewById(R.id.textView_overview);
        description.setText(entry.overview);

        //http://image.tmdb.org/t/p/w185/
        ImageView poster = (ImageView) view.findViewById(R.id.imageView_poster);
        Picasso.with(context).load("http://image.tmdb.org/t/p/w185/"+entry.moviePoster).into(poster);

        TextView date = (TextView) view.findViewById(R.id.textView_releaseDate);
        date.setText(entry.releaseDate);

        TextView rating = (TextView) view.findViewById(R.id.textView_userRating);
        rating.setText(entry.userRating);


        return view;
    }
}
