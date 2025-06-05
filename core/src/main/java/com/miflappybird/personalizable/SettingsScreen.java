package com.miflappybird.personalizable;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

import java.util.HashMap;

public class SettingsScreen implements Screen {

    private final Game game;
    private final ScoreService scoreService;
    private final SettingsService settingsService;

    private final SpriteBatch batch;
    private final BitmapFont font;

    private final TextureRegion background;
    private final TextureRegion overlayImage;
    private final TextureRegion backButton;

    private final TextureRegion btnCambiarJugador, btnCambiarTuberia, btnCambiarEscenario;
    private float btnWidth, btnHeight;
    private float btnJugadorX, btnJugadorY, btnTuberiaX, btnTuberiaY, btnEscenarioX, btnEscenarioY;

    private float backButtonX, backButtonY;
    private float backButtonWidth, backButtonHeight;
    private float overlayX, overlayY;
    private float overlayWidth, overlayHeight;

    private final String[] playerNames = {"blackSupercat", "whiteSupercat", "orangeSupercat", "yellowbird", "vulture", "halloweenCat"};
    private final String[] playerLabels = {"Supergato negro", "Supergato blanco", "Supergato naranja", "Flappy bird", "Buitre", "Gata bruja"};

    private final String[] pipeNames = {"tile000", "tile001", "tile002", "tile003", "tile004", "tile005", "tile006", "tile007"};
    private final String[] pipeLabels = {"Tubería verde", "Tubería amarilla", "Tubería roja", "Tubería azul", "Tubería gris", "Tubería rosa", "Tubería marrón", "Tubería naranja"};

    private final String[] backgroundNames = {
        "background-day", "background-moonnight", "background-afternoon", "background-morning",
        "background-futureday", "background-futurenight", "background-starwars1",
        "background-starwars2", "background-moon", "background-neonsunset"
    };
    private final String[] backgroundLabels = {
        "Día", "Noche", "Tarde", "Mañana", "Futuro de día", "Futuro de noche",
        "Invasión tarde", "Invasión noche", "Luna", "Neon"
    };

    private final HashMap<String, String> baseByBackground = new HashMap<String, String>() {{
        put("background-day", "base");
        put("background-moonnight", "base_blue");
        put("background-afternoon", "base_orange");
        put("background-morning", "base_morning");
        put("background-futureday", "base");
        put("background-futurenight", "base_blue");
        put("background-starwars1", "base_starwars_colors");
        put("background-starwars2", "base_blue");
        put("background-moon", "base_blue_gray");
        put("background-neonsunset", "base_neon");
    }};

    private int playerIndex = 0;
    private int pipeIndex = 0;
    private int backgroundIndex = 0;

    // Variables para animación de parpadeo
    private float blinkAlpha = 1f;
    private long lastBlinkTime = 0;
    private int blinkingButton = -1; // 0 = jugador, 1 = escenario, 2 = tubería

    public SettingsScreen(Game game, ScoreService scoreService) {
        this.game = game;
        this.scoreService = scoreService;
        this.settingsService = new SettingsService();
        this.batch = new SpriteBatch();

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font/Orbitron-Black.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 36;
        font = generator.generateFont(parameter);
        font.setColor(0.53f, 0.81f, 0.92f, 1);
        generator.dispose();

        background = AssetsManager.getBackgroundByName("background-day");
        overlayImage = AssetsManager.getButton("fondoPausaJuego");

        float overlayScale = 4.7f;
        overlayWidth = overlayImage.getRegionWidth() * overlayScale;
        overlayHeight = overlayImage.getRegionHeight() * overlayScale;
        overlayX = (Gdx.graphics.getWidth() - overlayWidth) / 2f;
        overlayY = (Gdx.graphics.getHeight() - overlayHeight) / 2f;

        backButton = AssetsManager.getButton("botonSalir");
        float scale = 0.6f;
        backButtonWidth = backButton.getRegionWidth() * scale;
        backButtonHeight = backButton.getRegionHeight() * scale;
        backButtonX = 30;
        backButtonY = Gdx.graphics.getHeight() - backButtonHeight - 30;

        btnCambiarJugador = AssetsManager.getButton("cambiarJugador");
        btnCambiarTuberia = AssetsManager.getButton("cambiarTuberia");
        btnCambiarEscenario = AssetsManager.getButton("cambiarEscenario");

        float btnScale = 0.7f;
        btnWidth = btnCambiarJugador.getRegionWidth() * btnScale;
        btnHeight = btnCambiarJugador.getRegionHeight() * btnScale;

        btnJugadorX = overlayX + 220;
        btnJugadorY = overlayY + overlayHeight - 760;

        btnEscenarioX = overlayX + 220;
        btnEscenarioY = overlayY + overlayHeight - 1040;

        btnTuberiaX = overlayX + 220;
        btnTuberiaY = overlayY + overlayHeight - 1320;
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);

        // Actualización de parpadeo
        if (blinkingButton != -1 && System.currentTimeMillis() - lastBlinkTime < 150) {
            blinkAlpha = 0.4f;
        } else {
            blinkAlpha = 1f;
            blinkingButton = -1;
        }

        batch.begin();

        batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.draw(overlayImage, overlayX, overlayY, overlayWidth, overlayHeight);
        batch.draw(backButton, backButtonX, backButtonY, backButtonWidth, backButtonHeight);

        font.getData().setScale(1.2f);
        float textX = overlayX + 170;
        float startY = overlayY + overlayHeight - 290;

        font.draw(batch, "Jugador: " + playerLabels[playerIndex], textX, startY);
        font.draw(batch, "Tuberia: " + pipeLabels[pipeIndex], textX, startY - 160);
        font.draw(batch, "Escenario: " + backgroundLabels[backgroundIndex], textX, startY - 80);

        // Botones con parpadeo
        batch.setColor(1, 1, 1, blinkingButton == 0 ? blinkAlpha : 1f);
        batch.draw(btnCambiarJugador, btnJugadorX, btnJugadorY, btnWidth, btnHeight);

        batch.setColor(1, 1, 1, blinkingButton == 1 ? blinkAlpha : 1f);
        batch.draw(btnCambiarEscenario, btnEscenarioX, btnEscenarioY, btnWidth, btnHeight);

        batch.setColor(1, 1, 1, blinkingButton == 2 ? blinkAlpha : 1f);
        batch.draw(btnCambiarTuberia, btnTuberiaX, btnTuberiaY, btnWidth, btnHeight);

        batch.setColor(1, 1, 1, 1); // restaurar

        batch.end();

        // Entrada táctil
        if (Gdx.input.justTouched()) {
            float x = Gdx.input.getX();
            float y = Gdx.graphics.getHeight() - Gdx.input.getY();

            if (x >= backButtonX && x <= backButtonX + backButtonWidth &&
                y >= backButtonY && y <= backButtonY + backButtonHeight) {
                game.setScreen(new MainMenuScreen(game, scoreService));
            } else {
                if (x >= btnJugadorX && x <= btnJugadorX + btnWidth &&
                    y >= btnJugadorY && y <= btnJugadorY + btnHeight) {
                    playerIndex = (playerIndex + 1) % playerNames.length;
                    settingsService.savePlayer(playerNames[playerIndex]);
                    blinkingButton = 0;
                    lastBlinkTime = System.currentTimeMillis();
                } else if (x >= btnEscenarioX && x <= btnEscenarioX + btnWidth &&
                    y >= btnEscenarioY && y <= btnEscenarioY + btnHeight) {
                    backgroundIndex = (backgroundIndex + 1) % backgroundNames.length;
                    settingsService.saveBackground(backgroundNames[backgroundIndex]);
                    blinkingButton = 1;
                    lastBlinkTime = System.currentTimeMillis();
                } else if (x >= btnTuberiaX && x <= btnTuberiaX + btnWidth &&
                    y >= btnTuberiaY && y <= btnTuberiaY + btnHeight) {
                    pipeIndex = (pipeIndex + 1) % pipeNames.length;
                    settingsService.savePipe(pipeNames[pipeIndex]);
                    blinkingButton = 2;
                    lastBlinkTime = System.currentTimeMillis();
                }
            }
        }
    }

    @Override public void show() {}
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
