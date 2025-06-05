package com.miflappybird.personalizable.android;

import android.os.Bundle;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.miflappybird.personalizable.Main;
import com.miflappybird.personalizable.ScoreService;

import java.util.ArrayList;

public class AndroidLauncher extends AndroidApplication implements ScoreService {

    private ScoreManager scoreManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        scoreManager = new ScoreManager(this);

        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
        config.useImmersiveMode = true;

        initialize(new Main(this), config); // <- `this` implementa ScoreService
    }

    @Override
    public ArrayList<Integer> getTopScores() {
        return scoreManager.getTopScores();
    }

    @Override
    public void addScore(int score) {
        scoreManager.addScore(score);
    }
}
