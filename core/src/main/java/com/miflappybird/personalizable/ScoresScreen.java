package com.miflappybird.personalizable;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;

public class ScoresScreen implements Screen {

    private final Game game;
    private final SpriteBatch batch;
    private final TextureRegion background;
    private final TextureRegion overlayImage;
    private final TextureRegion backButton;
    private final BitmapFont font;

    private float backButtonX, backButtonY;
    private float backButtonWidth, backButtonHeight;

    private float overlayX, overlayY;
    private float overlayWidth, overlayHeight;

    public ScoresScreen(Game game) {
        this.game = game;
        this.batch = new SpriteBatch();
        this.font = new BitmapFont();

        // Fondo
        this.background = AssetsManager.getBackground(13); // backgroundDay

        // Imagen intermedia
        this.overlayImage = AssetsManager.getButton("fondoPausaJuego");
        float overlayScale = 4.0f;
        overlayWidth = overlayImage.getRegionWidth() * overlayScale;
        overlayHeight = overlayImage.getRegionHeight() * overlayScale;
        overlayX = (Gdx.graphics.getWidth() - overlayWidth) / 2f;
        overlayY = (Gdx.graphics.getHeight() - overlayHeight) / 2f;

        // Botón de volver
        this.backButton = AssetsManager.getButton("botonSalir");
        float scale = 0.8f;
        this.backButtonWidth = backButton.getRegionWidth() * scale;
        this.backButtonHeight = backButton.getRegionHeight() * scale;
        this.backButtonX = 20;
        this.backButtonY = Gdx.graphics.getHeight() - backButtonHeight - 20;
    }

    @Override
    public void show() {}

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        batch.begin();

        // Fondo
        batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        // Imagen de overlay
        batch.draw(overlayImage, overlayX, overlayY, overlayWidth, overlayHeight);

        // Título
        String title = "Mejores Puntuaciones";
        float textWidth = font.getRegion().getRegionWidth(); // Aproximación si no usas layout
        float textX = overlayX + overlayWidth / 2f - 260; // Ajuste manual
        float textY = overlayY + overlayHeight - 200;
        font.getData().setScale(3.5f); // Aumenta el tamaño del texto
        font.draw(batch, title, textX, textY);

        // Botón
        batch.draw(backButton, backButtonX, backButtonY, backButtonWidth, backButtonHeight);

        batch.end();

        // Pulsación
        if (Gdx.input.justTouched()) {
            float touchX = Gdx.input.getX();
            float touchY = Gdx.graphics.getHeight() - Gdx.input.getY();

            if (touchX >= backButtonX && touchX <= backButtonX + backButtonWidth &&
                touchY >= backButtonY && touchY <= backButtonY + backButtonHeight) {
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
        font.dispose();
    }
}

