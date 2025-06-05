package com.miflappybird.personalizable;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

public class MainMenuScreen implements Screen {

    private final Game game;
    private final ScoreService scoreService;
    private final SpriteBatch batch;
    private final TextureRegion playButton;
    private final TextureRegion settingsButton;
    private final TextureRegion scoresButton;
    private final TextureRegion background;

    private boolean showingInfo = false;
    private TextureRegion infoButton, closeButton, infoBackground;
    private BitmapFont infoFont;
    private String infoText = "HOLAA, BIENVENID@!!!\n" +
        "Estás jugando a un\n" +
        "flappy bird personalizable,\n" +
        "te explico un poco como\n" +
        "va la aplicación : )\n" +
        "En primer lugar, el juego\n" +
        "en sí (al que accederás\n" +
        "con el botón jugar) es muy\n" +
        "sencillo, solo tienes que\n" +
        "pasar entre las tuberías\n" +
        "que irán viniendo\n" +
        "pulsando la pantalla\n" +
        "para que tu personaje\n" +
        "salte. Luego, puedes \n" +
        "ver tus mejores marcas \n" +
        "pulsando el botón de\n" +
        "marcador. Y por último\n" +
        "podrás cambiar el\n" +
        "escenario en el que juegas,\n" +
        "los personajes y los\n" +
        "obstaculos en la\n" +
        "pantalla de opciones";


    private final float playX = 160, playY = 1400;
    private final float settingsX = 160, settingsY = 600;
    private final float scoresX = 160, scoresY = 1000;

    private boolean isAnimating = false;
    private float animationTime = 0f;
    private float animationDuration = 0.15f;
    private float playScale = 1f, settingsScale = 1f, scoresScale = 1f;
    private String buttonPressed = "";

    public MainMenuScreen(Game game, ScoreService scoreService) {
        this.game = game;
        this.scoreService = scoreService;
        batch = new SpriteBatch();

        playButton = AssetsManager.getButton("botonJugar");
        settingsButton = AssetsManager.getButton("botonOpciones");
        scoresButton = AssetsManager.getButton("botonMarcador");
        infoButton = AssetsManager.getButton("botonInformacion");
        closeButton = AssetsManager.getButton("botonX");
        infoBackground = AssetsManager.getButton("fondoPausaJuego");

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font/Orbitron-Black.ttf"));
        FreeTypeFontParameter parameter = new FreeTypeFontParameter();
        parameter.size = 14; // ajusta si lo ves demasiado grande o pequeño
        infoFont = generator.generateFont(parameter);
        generator.dispose();

        background = AssetsManager.getBackgroundByName("background-day");
    }

    @Override
    public void show() {}

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        batch.begin();

        // Fondo
        batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        // Botones principales
        if (!showingInfo) {
            drawButtonWithScale(playButton, playX, playY, playScale);
            drawButtonWithScale(settingsButton, settingsX, settingsY, settingsScale);
            drawButtonWithScale(scoresButton, scoresX, scoresY, scoresScale);
        }

        // Botón info o cerrar
        float infoScale = 0.5f;
        float infoWidth = infoButton.getRegionWidth() * infoScale;
        float infoHeight = infoButton.getRegionHeight() * infoScale;
        float infoX = Gdx.graphics.getWidth() - infoWidth - 20;
        float infoY = Gdx.graphics.getHeight() - infoHeight - 20;
        batch.draw(showingInfo ? closeButton : infoButton, infoX, infoY, infoWidth, infoHeight);

        // Mostrar ventana de información
        if (showingInfo) {
            // Fondo oscuro translúcido
            batch.setColor(0, 0, 0, 0.6f); // negro con alpha
            batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            batch.setColor(1, 1, 1, 1); // restaurar

            // Fondo del cuadro
            float bgScale = 4.5f;
            float bgWidth = infoBackground.getRegionWidth() * bgScale;
            float bgHeight = infoBackground.getRegionHeight() * bgScale;
            float centerX = (Gdx.graphics.getWidth() - bgWidth) / 2f;
            float centerY = (Gdx.graphics.getHeight() - bgHeight) / 2f;
            batch.draw(infoBackground, centerX, centerY, bgWidth, bgHeight);

            // Texto
            infoFont.setColor(0.53f, 0.81f, 0.98f, 1f); // Sky blue
            infoFont.getData().setScale(2.9f);
            infoFont.draw(batch, infoText, centerX + 40, centerY + bgHeight - 180, bgWidth - 80, 1, true);
        }

        if (isAnimating) {
            animationTime += delta;
            float progress = animationTime / animationDuration;
            float scale = 1f + 0.15f * (float)Math.sin(progress * Math.PI);
            switch (buttonPressed) {
                case "play": playScale = scale; break;
                case "settings": settingsScale = scale; break;
                case "scores": scoresScale = scale; break;
            }
            if (animationTime >= animationDuration) {
                isAnimating = false;
                playScale = settingsScale = scoresScale = 1f;
                switch (buttonPressed) {
                    case "play": game.setScreen(new FirstScreen(game, scoreService)); break;
                    case "settings": game.setScreen(new SettingsScreen(game, scoreService)); break;
                    case "scores": game.setScreen(new ScoresScreen(game, scoreService)); break;
                }
            }
        }

        batch.end();

        if (Gdx.input.justTouched()) {
            float x = Gdx.input.getX();
            float y = Gdx.graphics.getHeight() - Gdx.input.getY();

            if (x >= infoX && x <= infoX + infoWidth && y >= infoY && y <= infoY + infoHeight) {
                showingInfo = !showingInfo;
            } else if (!showingInfo) {
                if (x >= playX && x <= playX + playButton.getRegionWidth()
                    && y >= playY && y <= playY + playButton.getRegionHeight()) {
                    isAnimating = true;
                    animationTime = 0f;
                    buttonPressed = "play";
                } else if (x >= settingsX && x <= settingsX + settingsButton.getRegionWidth()
                    && y >= settingsY && y <= settingsY + settingsButton.getRegionHeight()) {
                    isAnimating = true;
                    animationTime = 0f;
                    buttonPressed = "settings";
                } else if (x >= scoresX && x <= scoresX + scoresButton.getRegionWidth()
                    && y >= scoresY && y <= scoresY + scoresButton.getRegionHeight()) {
                    isAnimating = true;
                    animationTime = 0f;
                    buttonPressed = "scores";
                }
            }
        }
    }

    private void drawButtonWithScale(TextureRegion button, float x, float y, float scale) {
        float width = button.getRegionWidth() * scale;
        float height = button.getRegionHeight() * scale;
        float drawX = x + (button.getRegionWidth() - width) / 2f;
        float drawY = y + (button.getRegionHeight() - height) / 2f;
        batch.draw(button, drawX, drawY, width, height);
    }

    @Override public void resize(int width, int height) {}
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}
    @Override public void dispose() {
        batch.dispose();
    }
}
