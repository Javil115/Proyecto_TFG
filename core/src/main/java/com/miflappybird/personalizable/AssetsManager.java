package com.miflappybird.personalizable;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
public class AssetsManager {
    public static AssetManager manager;
    public static Texture background;

    public static void load() {
        manager = new AssetManager();
        manager.load(("background.png", Texture.class);
        manager.finishLoading();
        background = manager.get("background.png", Texture.class);
    }
    public static void dispose() {
        manager.dispose();
    }
}
