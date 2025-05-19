package com.miflappybird.personalizable;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;

public class SettingsScreen implements Screen {

    private final Game game;
    private final SpriteBatch batch;

    private final TextureRegion background;
    private final TextureRegion overlayImage;
    private final TextureRegion backButton;

    private float backButtonX, backButtonY;
    private float backButtonWidth, backButtonHeight;

    private float overlayX, overlayY;
    private float overlayWidth, overlayHeight;

    public SettingsScreen(Game game) {
        this.game = game;
        this.batch = new SpriteBatch();

        // Fondo y capa intermedia
        background = AssetsManager.getBackground(13);
        overlayImage = AssetsManager.getButton("fondoPausaJuego");

        float overlayScale = 4.0f;
        overlayWidth = overlayImage.getRegionWidth() * overlayScale;
        overlayHeight = overlayImage.getRegionHeight() * overlayScale;
        overlayX = (Gdx.graphics.getWidth() - overlayWidth) / 2f;
        overlayY = (Gdx.graphics.getHeight() - overlayHeight) / 2f;

        // Botón gráfico "Back"
        backButton = AssetsManager.getButton("botonSalir");
        float scale = 0.8f;
        backButtonWidth = backButton.getRegionWidth() * scale;
        backButtonHeight = backButton.getRegionHeight() * scale;
        backButtonX = 20;
        backButtonY = Gdx.graphics.getHeight() - backButtonHeight - 20;
    }

    @Override
    public void show() {}

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);

        batch.begin();

        // Fondo
        batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        // Capa superpuesta
        batch.draw(overlayImage, overlayX, overlayY, overlayWidth, overlayHeight);

        // Botón volver
        batch.draw(backButton, backButtonX, backButtonY, backButtonWidth, backButtonHeight);

        batch.end();

        // Manejo del botón volver
        if (Gdx.input.justTouched()) {
            float x = Gdx.input.getX();
            float y = Gdx.graphics.getHeight() - Gdx.input.getY();

            if (x >= backButtonX && x <= backButtonX + backButtonWidth &&
                y >= backButtonY && y <= backButtonY + backButtonHeight) {
                game.setScreen(new MainMenuScreen(game));
            }
        }
    }

    @Override public void resize(int width, int height) {}
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}

    @Override
    public void dispose() {
        batch.dispose();
    }
}
