package com.miflappybird.personalizable;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;

public class FirstScreen implements Screen {

    private Game game;
    private SpriteBatch batch;
    private TextureRegion background;
    private TextureRegion playerDown;
    private TextureRegion playerMid;
    private TextureRegion playerUp;
    private float playerY;
    private float velocity;
    private float gravity;
    private int score;
    private BitmapFont font;

    public FirstScreen(Game game) {
        this.game = game;
        batch = new SpriteBatch();
        playerY = 300;
        velocity = 0;
        gravity = -0.5f;
        score = 0;
        font = new BitmapFont();

        // Cargar las regiones específicas usando el AssetsManager
        background = AssetsManager.getBackground(13);
        playerDown = AssetsManager.getPlayer("whiteSupercatDown");
        playerMid = AssetsManager.getPlayer("whiteSupercatMid");
        playerUp = AssetsManager.getPlayer("whiteSupercatUp");
    }

    @Override
    public void show() {}

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        batch.begin();

        // Dibujar fondo
        batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        // Determinar el estado del jugador (subiendo, en el medio o cayendo)
        TextureRegion currentPlayer;
        if (velocity > 0) {
            currentPlayer = playerUp;
        } else if (velocity < 0 && playerY <= 20) {
            currentPlayer = playerDown;
        } else {
            currentPlayer = playerMid;
        }

        // Dibujar jugador
        batch.draw(currentPlayer, 350, playerY, 154, 138);
        font.draw(batch, "Score: " + score, 10, Gdx.graphics.getHeight() - 10);
        batch.end();

        // Actualizar posición del jugador
        if (Gdx.input.justTouched()) {
            velocity = 10;
        }
        velocity += gravity;
        playerY += velocity;

        // Colisión con el suelo
        if (playerY <= 0) {
            playerY = 0;
            velocity = 0;
        }

        // Colisión con el techo
        if (playerY + currentPlayer.getRegionHeight() >= Gdx.graphics.getHeight()) {
            playerY = Gdx.graphics.getHeight() - currentPlayer.getRegionHeight();
            velocity = 0;
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
        font.dispose();
    }
}
