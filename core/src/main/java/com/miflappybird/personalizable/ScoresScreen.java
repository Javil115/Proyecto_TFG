package com.miflappybird.personalizable;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.ArrayList;

public class ScoresScreen implements Screen {

    private final Game game;
    private final ScoreService scoreService;
    private final SpriteBatch batch;
    private final TextureRegion background;
    private final TextureRegion overlayImage;
    private final TextureRegion backButton;
    private final BitmapFont font;

    private float backButtonX, backButtonY;
    private float backButtonWidth, backButtonHeight;
    private float overlayX, overlayY;
    private float overlayWidth, overlayHeight;

    private final ArrayList<Integer> topScores;

    public ScoresScreen(Game game, ScoreService scoreService) {
        this.game = game;
        this.scoreService = scoreService;
        this.batch = new SpriteBatch();

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font/Orbitron-Black.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 36;
        this.font = generator.generateFont(parameter);
        font.setColor(0.53f, 0.81f, 0.92f, 1); // Color azul cielo
        generator.dispose();

        this.background = AssetsManager.getBackgroundByName("background-day"); // backgroundDay

        this.overlayImage = AssetsManager.getButton("fondoPausaJuego");
        float overlayScale = 4.0f;
        overlayWidth = overlayImage.getRegionWidth() * overlayScale;
        overlayHeight = overlayImage.getRegionHeight() * overlayScale;
        overlayX = (Gdx.graphics.getWidth() - overlayWidth) / 2f;
        overlayY = (Gdx.graphics.getHeight() - overlayHeight) / 2f;

        this.backButton = AssetsManager.getButton("botonSalir");
        float scale = 0.6f;
        this.backButtonWidth = backButton.getRegionWidth() * scale;
        this.backButtonHeight = backButton.getRegionHeight() * scale;
        this.backButtonX = 30;
        this.backButtonY = Gdx.graphics.getHeight() - backButtonHeight - 30;

        this.topScores = scoreService.getTopScores();
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        batch.begin();

        // Fondo completo
        batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        // Overlay
        batch.draw(overlayImage, overlayX, overlayY, overlayWidth, overlayHeight);

        // Título
        font.getData().setScale(2.0f);
        font.getData().setScale(2.0f);

        GlyphLayout layout = new GlyphLayout();
        layout.setText(font, "Mejores\nPuntuaciones");

// Coordenadas del centro horizontal
        float centerX = overlayX + overlayWidth / 2f;

// Coordenadas verticales (ajusta a tu gusto)
        float textX = centerX - layout.width / 2f;
        float textY = overlayY + overlayHeight - 230;

// Dibuja el texto centrado
        font.draw(batch, layout, textX, textY);

        // Mostrar top scores
        font.getData().setScale(1.8f);
        float scoreX = overlayX + 300;
        float scoreYStart = overlayY + overlayHeight - 470;
        float spacing = 90;

        for (int i = 0; i < topScores.size(); i++) {
            String scoreText = (i + 1) + ". " + topScores.get(i);
            font.draw(batch, scoreText, scoreX, scoreYStart - i * spacing);
        }

        // Botón volver
        batch.draw(backButton, backButtonX, backButtonY, backButtonWidth, backButtonHeight);
        batch.end();

        // Detectar clic en botón volver
        if (Gdx.input.justTouched()) {
            float touchX = Gdx.input.getX();
            float touchY = Gdx.graphics.getHeight() - Gdx.input.getY();

            if (touchX >= backButtonX && touchX <= backButtonX + backButtonWidth &&
                touchY >= backButtonY && touchY <= backButtonY + backButtonHeight) {
                game.setScreen(new MainMenuScreen(game, scoreService)); // ← Pasamos scoreService de nuevo
            }
        }
    }

    @Override public void show() {}
    @Override public void resize(int width, int height) {}
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}
    @Override public void dispose() {
        batch.dispose();
        font.dispose();
    }
}
