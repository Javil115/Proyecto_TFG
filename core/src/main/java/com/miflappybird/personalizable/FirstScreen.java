package com.miflappybird.personalizable;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class FirstScreen implements Screen {

    private final Game game;
    private final SpriteBatch batch;
    private final TextureRegion background;
    private final TextureRegion base;
    private final TextureRegion playerDown;
    private final TextureRegion playerMid;
    private final TextureRegion playerUp;
    private final TextureRegion pauseButton;
    private final TextureRegion pauseWindow;
    private final TextureRegion continueButton;
    private final TextureRegion backButton;
    private final TextureRegion pipeTexture;
    private final TextureRegion botonJugarDeNuevo;

    private float playerY;
    private float velocity;
    private final float gravity;
    private int score;
    private final BitmapFont font;
    private boolean paused;
    private boolean gameOver;

    private final ArrayList<Pipe> pipes;
    private float pipeSpawnTimer;
    private final float pipeSpawnInterval = 2f;
    private final Random random;

    public FirstScreen(Game game) {
        this.game = game;
        batch = new SpriteBatch();
        playerY = 300;
        velocity = 0;
        gravity = -0.5f;
        score = 0;
        font = new BitmapFont();
        paused = false;
        gameOver = false;

        background = AssetsManager.getBackground(13);
        base = AssetsManager.getBackground(15);
        playerDown = AssetsManager.getPlayer("blackSupercatMid");
        playerMid = AssetsManager.getPlayer("blackSupercatDown");
        playerUp = AssetsManager.getPlayer("blackSupercatUp");

        pauseButton = AssetsManager.getButton("botonPausa");
        pauseWindow = AssetsManager.getButton("fondoPausaJuego");
        continueButton = AssetsManager.getButton("botonSeguir");
        backButton = AssetsManager.getButton("botonSalirLetras");
        pipeTexture = AssetsManager.getPipe("pipe11");
        botonJugarDeNuevo = AssetsManager.getButton("botonJugarDeNuevo");

        pipes = new ArrayList<>();
        pipeSpawnTimer = 0f;
        random = new Random();
    }

    @Override
    public void show() {}

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        batch.begin();

        batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        int baseHeight = (int)(base.getRegionHeight() * ((float)Gdx.graphics.getWidth() / base.getRegionWidth()));
        int baseY = 0;
        batch.draw(base, 0, baseY, Gdx.graphics.getWidth(), baseHeight);

        if (!paused && !gameOver) {
            pipeSpawnTimer += delta;
            if (pipeSpawnTimer >= pipeSpawnInterval) {
                float gap = 800;
                float minPipeY = baseHeight + 100;
                float maxPipeY = Gdx.graphics.getHeight() - gap - 100;
                float gapY = minPipeY + random.nextInt((int)(maxPipeY - minPipeY));

                pipes.add(new Pipe(Gdx.graphics.getWidth(), gapY - pipeTexture.getRegionHeight(), pipeTexture)); // upper
                pipes.add(new Pipe(Gdx.graphics.getWidth(), gapY + gap, pipeTexture)); // lower
                pipeSpawnTimer = 0f;
            }

            Iterator<Pipe> it = pipes.iterator();
            while (it.hasNext()) {
                Pipe pipe = it.next();
                pipe.update(delta);
                batch.draw(pipe.getTexture(), pipe.getX(), pipe.getY(), pipe.getWidth(), pipe.getHeight());

                if (pipe.collides(350, playerY, 154, 138)) {
                    gameOver = true;
                }

                if (pipe.getX() + pipe.getWidth() < 0) {
                    it.remove();
                }
            }
        } else {
            for (Pipe pipe : pipes) {
                batch.draw(pipe.getTexture(), pipe.getX(), pipe.getY(), pipe.getWidth(), pipe.getHeight());
            }
        }

        TextureRegion currentPlayer = velocity > 0 ? playerUp : (playerY <= baseHeight ? playerDown : playerMid);
        batch.draw(currentPlayer, 350, playerY, 154, 138);

        float scale = 0.3f;
        float pauseWidth = pauseButton.getRegionWidth() * scale;
        float pauseHeight = pauseButton.getRegionHeight() * scale;
        float pauseX = Gdx.graphics.getWidth() - pauseWidth - 20;
        float pauseY = Gdx.graphics.getHeight() - pauseHeight - 20;
        if (!gameOver) {
            batch.draw(pauseButton, pauseX, pauseY, pauseWidth, pauseHeight);
        }

        font.draw(batch, "Score: " + score, 10, Gdx.graphics.getHeight() - 10);

        if (paused && !gameOver) {
            drawWindow(pauseWindow, continueButton, backButton, true);
        }

        if (gameOver) {
            drawWindow(pauseWindow, botonJugarDeNuevo, backButton, false);
        }

        batch.end();

        if (Gdx.input.justTouched()) {
            float x = Gdx.input.getX();
            float y = Gdx.graphics.getHeight() - Gdx.input.getY();

            if (!paused && !gameOver) {
                if (x >= pauseX && x <= pauseX + pauseWidth && y >= pauseY && y <= pauseY + pauseHeight) {
                    paused = true;
                } else {
                    velocity = 10;
                }
            } else if (paused) {
                if (checkButtonPressed(continueButton, true, x, y)) {
                    paused = false;
                } else if (checkButtonPressed(backButton, true, x, y)) {
                    game.setScreen(new MainMenuScreen(game));
                }
            } else if (gameOver) {
                if (checkButtonPressed(botonJugarDeNuevo, false, x, y)) {
                    game.setScreen(new FirstScreen(game));
                } else if (checkButtonPressed(backButton, false, x, y)) {
                    game.setScreen(new MainMenuScreen(game));
                }
            }
        }

        if (!paused && !gameOver) {
            velocity += gravity;
            playerY += velocity;
            if (playerY <= baseHeight) {
                playerY = baseHeight;
                velocity = 0;
            }
            if (playerY + playerMid.getRegionHeight() >= Gdx.graphics.getHeight()) {
                playerY = Gdx.graphics.getHeight() - playerMid.getRegionHeight();
                velocity = 0;
            }
        }
    }

    private void drawWindow(TextureRegion window, TextureRegion botonPrincipal, TextureRegion botonSecundario, boolean isPause) {
        float windowScale = 4.0f;
        float windowWidth = window.getRegionWidth() * windowScale;
        float windowHeight = window.getRegionHeight() * windowScale;
        float centerX = (Gdx.graphics.getWidth() - windowWidth) / 2f;
        float centerY = (Gdx.graphics.getHeight() - windowHeight) / 2f;

        batch.draw(window, centerX, centerY, windowWidth, windowHeight);

        float btnScale = 1.2f;

        float btnPrincipalWidth = botonPrincipal.getRegionWidth() * btnScale;
        float btnPrincipalHeight = botonPrincipal.getRegionHeight() * btnScale;
        float btnPrincipalX = centerX + (windowWidth - btnPrincipalWidth) / 2f;
        float btnPrincipalY = centerY + windowHeight - btnPrincipalHeight - 300;
        batch.draw(botonPrincipal, btnPrincipalX, btnPrincipalY, btnPrincipalWidth, btnPrincipalHeight);

        float btnSecundarioWidth = botonSecundario.getRegionWidth() * btnScale;
        float btnSecundarioHeight = botonSecundario.getRegionHeight() * btnScale;
        float btnSecundarioX = centerX + (windowWidth - btnSecundarioWidth) / 2f;
        float btnSecundarioY = centerY + 300;
        batch.draw(botonSecundario, btnSecundarioX, btnSecundarioY, btnSecundarioWidth, btnSecundarioHeight);
    }

    private boolean checkButtonPressed(TextureRegion button, boolean isPause, float x, float y) {
        float windowScale = 4.0f;
        float windowWidth = pauseWindow.getRegionWidth() * windowScale;
        float windowHeight = pauseWindow.getRegionHeight() * windowScale;
        float centerX = (Gdx.graphics.getWidth() - windowWidth) / 2f;
        float centerY = (Gdx.graphics.getHeight() - windowHeight) / 2f;

        float btnScale = 1.2f;
        float btnWidth = button.getRegionWidth() * btnScale;
        float btnHeight = button.getRegionHeight() * btnScale;
        float btnX, btnY;

        if (button == continueButton || button == botonJugarDeNuevo) {
            btnX = centerX + (windowWidth - btnWidth) / 2f;
            btnY = centerY + windowHeight - btnHeight - 300;
        } else {
            btnX = centerX + (windowWidth - btnWidth) / 2f;
            btnY = centerY + 300;
        }

        return x >= btnX && x <= btnX + btnWidth && y >= btnY && y <= btnY + btnHeight;
    }

    @Override public void resize(int width, int height) {}
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}
    @Override public void dispose() {
        batch.dispose();
        font.dispose();
    }
}

class Pipe {
    private float x, y;
    private final float width = 52;
    private final float height = 320;
    private final float speed = 200;
    private final TextureRegion texture;

    public Pipe(float x, float y, TextureRegion texture) {
        this.x = x;
        this.y = y;
        this.texture = texture;
    }

    public void update(float delta) {
        x -= speed * delta;
    }

    public boolean collides(float px, float py, float pw, float ph) {
        return px < x + width && px + pw > x && py < y + height && py + ph > y;
    }

    public float getX() { return x; }
    public float getY() { return y; }
    public float getWidth() { return width; }
    public float getHeight() { return height; }
    public TextureRegion getTexture() { return texture; }
}
