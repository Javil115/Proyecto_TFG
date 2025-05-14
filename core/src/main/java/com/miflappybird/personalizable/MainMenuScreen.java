package com.miflappybird.personalizable;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;

public class MainMenuScreen implements Screen {

    private Game game;
    private SpriteBatch batch;
    private Texture playButton;
    private Texture settingsButton;
    private Texture scoresButton;
    private TextureRegion background;

    public MainMenuScreen(Game game) {
        this.game = game;
        batch = new SpriteBatch();
        playButton = new Texture("ui/play.png");
        settingsButton = new Texture("ui/settings.png");
        scoresButton = new Texture("ui/scores.png");

        // Fondo asignado desde el AssetsManager
        background = AssetsManager.getBackground(13); // background-day
    }

    @Override
    public void show() {}

    @Override
    public void render(float delta) {
        Gdx.app.log("MainMenuScreen", "render ejecutándose");
        ScreenUtils.clear(0, 0, 0, 1);
        batch.begin();

        // Dibujar el fondo ajustado al tamaño de pantalla
        batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        // Dibujar botones
        batch.draw(playButton, 100, 300);
        batch.draw(settingsButton, 100, 200);
        batch.draw(scoresButton, 100, 100);
        batch.end();

        if (Gdx.input.justTouched()) {
            float x = Gdx.input.getX();
            float y = Gdx.graphics.getHeight() - Gdx.input.getY();

            if (x >= 100 && x <= 300) {
                if (y >= 300 && y <= 350) {
                    game.setScreen(new FirstScreen(game));
                } else if (y >= 200 && y <= 250) {
                    game.setScreen(new SettingsScreen(game));
                } else if (y >= 100 && y <= 150) {
                    game.setScreen(new ScoresScreen(game));
                }
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
        playButton.dispose();
        settingsButton.dispose();
        scoresButton.dispose();
    }
}
