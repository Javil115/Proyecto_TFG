package com.miflappybird.personalizable;

import com.badlogic.gdx.Game;

public class MyGdxGame extends Game {

    public void create() {

        AssetsManager.load();

        setScreen(new MainMenuScreen(this));

    }

    public void dispose() {

        super.dispose();
        AssetsManager.dispose();

    }

}
