package com.miflappybird.personalizable.android;

import com.miflappybird.personalizable.ScoreService;
import com.miflappybird.personalizable.android.ScoreManager;

import java.util.ArrayList;

public class ScoreServiceImpl implements ScoreService {

    private final ScoreManager scoreManager;

    public ScoreServiceImpl(ScoreManager scoreManager) {
        this.scoreManager = scoreManager;
    }

    @Override
    public ArrayList<Integer> getTopScores() {
        return scoreManager.getTopScores();
    }

    public void addScore(int score) {
        scoreManager.addScore(score);
    }
}
