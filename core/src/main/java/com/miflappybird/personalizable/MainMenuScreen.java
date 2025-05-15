package com.miflappybird.personalizable;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;

public class MainMenuScreen implements Screen {

    private final Game game;
    private final SpriteBatch batch;
    private final TextureRegion playButton;
    private final TextureRegion settingsButton;
    private final TextureRegion scoresButton;
    private final TextureRegion background;

    // Coordenadas de botones
    private final float playX = 160, playY = 1400;
    private final float settingsX = 160, settingsY = 600;
    private final float scoresX = 160, scoresY = 1000;

    public MainMenuScreen(Game game) {
        this.game = game;
        batch = new SpriteBatch();

        playButton = AssetsManager.getButton("botonJugar");
        settingsButton = AssetsManager.getButton("botonOpciones");
        scoresButton = AssetsManager.getButton("botonMarcador");
        background = AssetsManager.getBackground(13); // background-day
    }

    @Override
    public void show() {}

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        batch.begin();

        batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.draw(playButton, playX, playY);
        batch.draw(settingsButton, settingsX, settingsY);
        batch.draw(scoresButton, scoresX, scoresY);

        batch.end();

        if (Gdx.input.justTouched()) {
            float x = Gdx.input.getX();
            float y = Gdx.graphics.getHeight() - Gdx.input.getY();

            // Botón Play
            if (x >= playX && x <= playX + playButton.getRegionWidth()
                && y >= playY && y <= playY + playButton.getRegionHeight()) {
                game.setScreen(new FirstScreen(game));
            }

            // Botón Opciones
            else if (x >= settingsX && x <= settingsX + settingsButton.getRegionWidth()
                && y >= settingsY && y <= settingsY + settingsButton.getRegionHeight()) {
                game.setScreen(new SettingsScreen(game));
            }

            // Botón Marcador
            else if (x >= scoresX && x <= scoresX + scoresButton.getRegionWidth()
                && y >= scoresY && y <= scoresY + scoresButton.getRegionHeight()) {
                game.setScreen(new ScoresScreen(game));
            }
        }
    }

    @Override
    public void resize(int width, int height) {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        batch.dispose();
    }
}
