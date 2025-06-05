package com.miflappybird.personalizable;

import com.badlogic.gdx.Game;

public class Main extends Game {

    private final ScoreService scoreService;

    // Constructor que recibe el ScoreService desde AndroidLauncher
    public Main(ScoreService scoreService) {
        this.scoreService = scoreService;
    }

    @Override
    public void create() {
        // Cargar los assets antes de usar cualquier recurso gráfico
        AssetsManager.load();

        // Establecer la primera pantalla, pasándole también el ScoreService
        setScreen(new MainMenuScreen(this, scoreService));
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        super.dispose();
        AssetsManager.dispose();
    }

    public ScoreService getScoreService() {
        return scoreService;
    }
}
