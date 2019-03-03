package com.example.androidquizz.todolist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.androidquizz.R;
import com.example.androidquizz.models.Statistics;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class StatisticAdapter extends RecyclerView.Adapter<StatisticViewHolder> {

    // CALLBACK
    public interface Listener { void onClickDeleteButton(int position); }
    private final Listener callback;

    // FOR DATA
    private List<Statistics> statistics;

    // CONSTRUCTOR
    public StatisticAdapter(Listener callback) {
        this.statistics = new ArrayList<>();
        this.callback = callback;
    }

    @Override
    public StatisticViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        //je crois que ici l'activité c'est pas recycler mais activity_todolist sauf que mets tout en rouge
        View view = inflater.inflate(R.layout.activity_recycler, parent, false);

        return new StatisticViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StatisticViewHolder viewHolder, int position) {
        viewHolder.updateWithStatistic(this.statistics.get(position), this.callback);
    }

    @Override
    public int getItemCount() {
        return this.statistics.size();
    }

    public Statistics getStatistics(int position){
        return this.statistics.get(position);
    }

    public void updateData(List<Statistics> statistics){
        this.statistics = statistics;
        this.notifyDataSetChanged();
    }
}