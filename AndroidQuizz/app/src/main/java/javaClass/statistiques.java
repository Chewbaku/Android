package javaClass;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.androidquizz.R;

public class statistiques {
    private int nbPartiesJouees;
    private int nbReponsesCorrectes;

    private final Context context;
    private final SharedPreferences sharedPrefs;

    public statistiques() {
    }

    public int getNbPartiesJouees() {
        return nbPartiesJouees;
    }

    public void incrementerNbPartiesJouees() {
        this.nbPartiesJouees++;
    }

    public int getNbReponsesCorrectes() {
        SharedPreferences  localSettings = mContext.getSharedPreferences("FileName",
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = localSettings.edit();

        return nbReponsesCorrectes;
    }

    public void incrementerNbReponsesCorrectes() {
        this.nbReponsesCorrectes++;
    }
}
