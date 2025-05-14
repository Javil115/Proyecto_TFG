package com.miflappybird.personalizable;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

public class Main extends Game {

    @Override
    public void create() {
        // Cargar los assets antes de usar cualquier recurso gráfico
        AssetsManager.load();

        // Establecer la primera pantalla
        setScreen(new MainMenuScreen(this));
    }

    @Override
    public void render() {
        super.render(); // Importante para que la pantalla activa funcione
    }

    @Override
    public void dispose() {
        super.dispose();
        AssetsManager.dispose();
    }
}
