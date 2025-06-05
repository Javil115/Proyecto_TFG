package com.miflappybird.personalizable;

import java.util.ArrayList;

public interface ScoreService {
    ArrayList<Integer> getTopScores();
    void addScore(int score);  // ← Añadido para guardar puntuaciones
}
