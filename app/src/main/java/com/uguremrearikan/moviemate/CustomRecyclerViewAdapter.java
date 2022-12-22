package com.uguremrearikan.moviemate;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomRecyclerViewAdapter extends RecyclerView.Adapter<CustomRecyclerViewAdapter.MyViewHolder> {
    private Context context;
    private ArrayList movie_id,movie_name,movie_year,movie_score;



    public CustomRecyclerViewAdapter(Context context,
                                     ArrayList movie_id,
                                     ArrayList movie_name,
                                     ArrayList movie_year,
                                     ArrayList movie_score) {
        this.context = context;
        this.movie_id = movie_id;
        this.movie_name = movie_name;
        this.movie_year = movie_year;
        this.movie_score = movie_score;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view =inflater.inflate(R.layout.my_row,parent,false);

        return new MyViewHolder(view)  ;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder,int position) {


        holder.movie_id.setText(String.valueOf(movie_id.get(position)));
        holder.movie_name.setText(String.valueOf(movie_name.get(position)));
        holder.movie_year.setText(String.valueOf(movie_year.get(position)));
        holder.movie_score.setText(String.valueOf(movie_score.get(position)));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,UpdateActivity.class);
                intent.putExtra("id",String.valueOf(movie_id.get(holder.getLayoutPosition())));
                intent.putExtra("name",String.valueOf(movie_name.get(holder.getLayoutPosition())));
                intent.putExtra("year",String.valueOf(movie_year.get(holder.getLayoutPosition())));
                intent.putExtra("score",String.valueOf(movie_score.get(holder.getLayoutPosition())));



                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return movie_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView movie_id,movie_name,movie_year,movie_score;
        LinearLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            movie_id=itemView.findViewById(R.id.movie_id_txt);
            movie_name=itemView.findViewById(R.id.movie_name_txt);
            movie_year=itemView.findViewById(R.id.movie_year_txt);
            movie_score=itemView.findViewById(R.id.movie_score_txt);
            mainLayout=itemView.findViewById(R.id.mainLayout);
        }
    }
}
