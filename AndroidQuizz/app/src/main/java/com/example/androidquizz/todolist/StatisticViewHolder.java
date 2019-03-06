package com.example.androidquizz.todolist;


import android.content.ClipData;
import android.graphics.Paint;
import android.view.View;
import android.widget.TextView;

import com.example.androidquizz.R;
import com.example.androidquizz.models.Statistics;

import java.lang.ref.WeakReference;

import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class StatisticViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    @BindView(R.id.todolist_id) TextView textView1;
    @BindView(R.id.todolist_nbPlayedAnswers) TextView textView2;
    @BindView(R.id.todolist_nbGoodAnswers) TextView textView3;

    // FOR DATA
    private WeakReference<StatisticAdapter.Listener> callbackWeakRef;

    public StatisticViewHolder(View StatisticView) {
        super(StatisticView);
        ButterKnife.bind(this, StatisticView);
        /* ou alors à la place de butterKnife :
        textView1 = (TextView) StatisticView.findViewById(R.id.todolist_id);
        textView2 = (TextView) StatisticView.findViewById(R.id.todolist_nbPlayedAnswers);
        textView3 = (TextView) StatisticView.findViewById(R.id.todolist_nbGoodAnswers);
        */
    }

    public void updateWithStatistic(Statistics statistic, StatisticAdapter.Listener callback){
        this.callbackWeakRef = new WeakReference<StatisticAdapter.Listener>(callback);
        this.textView1.setText((int) statistic.getUserId());
        this.textView2.setText(statistic.getNbPlayedAnswers());
        this.textView3.setText(statistic.getNbGoodAnswers());


    }


    @Override
    public void onClick(View view) {
        StatisticAdapter.Listener callback = callbackWeakRef.get();
        if (callback != null) callback.onClickDeleteButton(getAdapterPosition());
    }


}