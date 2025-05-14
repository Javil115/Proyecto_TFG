package com.miflappybird.personalizable;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetsManager {

    public static AssetManager manager;

    public static TextureRegion fondo1, fondo2, fondo3, fondo4, fondo5, fondo6, fondo7, fondo8;
    public static TextureRegion fondo9, fondo10, fondo11, fondo12;
    public static TextureRegion backgroundDay, demo;

    // Player Animations
    public static TextureRegion blackSupercatDown, blackSupercatMid, blackSupercatUp;
    public static TextureRegion dogDown, dogMid, dogUp;
    public static TextureRegion halloweenCatDown, halloweenCatMid, halloweenCatUp;
    public static TextureRegion orangeSupercatDown, orangeSupercatMid, orangeSupercatUp;
    public static TextureRegion vultureDown, vultureMid, vultureUp;
    public static TextureRegion whiteSupercatDown, whiteSupercatMid, whiteSupercatUp;
    public static TextureRegion yellowbirdDown, yellowbirdMid, yellowbirdUp;

    // Pipes
    public static TextureRegion[] pipes;
    public static TextureRegion pipeGreen;

    public static void load() {
        manager = new AssetManager();
        manager.load("backgrounds/fondos_fb.png", Texture.class);
        manager.load("player/player.png", Texture.class);
        manager.load("pipes/Pipes.png", Texture.class);
        manager.finishLoading();

        Texture fondosTexture = manager.get("backgrounds/fondos_fb.png", Texture.class);
        fondo1 = new TextureRegion(fondosTexture, 1104, 0, 576, 324);
        fondo2 = new TextureRegion(fondosTexture, 0, 512, 576, 324);
        fondo3 = new TextureRegion(fondosTexture, 576, 512, 576, 324);
        fondo4 = new TextureRegion(fondosTexture, 1152, 512, 576, 324);
        fondo5 = new TextureRegion(fondosTexture, 0, 836, 576, 324);
        fondo6 = new TextureRegion(fondosTexture, 576, 836, 576, 324);
        fondo7 = new TextureRegion(fondosTexture, 1152, 836, 576, 324);
        fondo8 = new TextureRegion(fondosTexture, 0, 1160, 576, 324);
        fondo9 = new TextureRegion(fondosTexture, 576, 1160, 576, 324);
        fondo10 = new TextureRegion(fondosTexture, 1152, 1160, 576, 324);
        fondo11 = new TextureRegion(fondosTexture, 0, 1484, 576, 324);
        fondo12 = new TextureRegion(fondosTexture, 576, 1484, 576, 324);
        backgroundDay = new TextureRegion(fondosTexture, 0, 0, 288, 512);
        demo = new TextureRegion(fondosTexture, 624, 0, 480, 180);

        Texture playerTexture = manager.get("player/player.png", Texture.class);
        blackSupercatDown = new TextureRegion(playerTexture, 0, 0, 32, 32);
        blackSupercatMid = new TextureRegion(playerTexture, 32, 0, 32, 32);
        blackSupercatUp = new TextureRegion(playerTexture, 64, 0, 32, 32);
        dogDown = new TextureRegion(playerTexture, 0, 32, 33, 26);
        dogMid = new TextureRegion(playerTexture, 33, 32, 33, 26);
        dogUp = new TextureRegion(playerTexture, 66, 32, 33, 26);
        halloweenCatDown = new TextureRegion(playerTexture, 0, 58, 32, 64);
        halloweenCatMid = new TextureRegion(playerTexture, 32, 58, 32, 64);
        halloweenCatUp = new TextureRegion(playerTexture, 64, 58, 32, 64);
        orangeSupercatDown = new TextureRegion(playerTexture, 0, 122, 32, 32);
        orangeSupercatMid = new TextureRegion(playerTexture, 32, 122, 32, 32);
        orangeSupercatUp = new TextureRegion(playerTexture, 64, 122, 32, 32);
        vultureDown = new TextureRegion(playerTexture, 0, 154, 39, 39);
        vultureMid = new TextureRegion(playerTexture, 39, 154, 39, 39);
        vultureUp = new TextureRegion(playerTexture, 78, 154, 39, 39);
        whiteSupercatDown = new TextureRegion(playerTexture, 0, 193, 32, 32);
        whiteSupercatMid = new TextureRegion(playerTexture, 32, 193, 32, 32);
        whiteSupercatUp = new TextureRegion(playerTexture, 64, 193, 32, 32);
        yellowbirdDown = new TextureRegion(playerTexture, 0, 225, 34, 24);
        yellowbirdMid = new TextureRegion(playerTexture, 34, 225, 34, 24);
        yellowbirdUp = new TextureRegion(playerTexture, 68, 225, 34, 24);

        Texture pipesTexture = manager.get("pipes/Pipes.png", Texture.class);
        pipes = new TextureRegion[24];
        for (int i = 0; i < 24; i++) {
            int x = (i % 2) * 32;
            int y = (i / 2) * 48;
            pipes[i] = new TextureRegion(pipesTexture, x, y, 32, 48);
        }
        pipeGreen = new TextureRegion(pipesTexture, 0, 768, 52, 320);
    }

    public static TextureRegion getBackground(int fondoIndex) {
        switch (fondoIndex) {
            case 1: return fondo1;
            case 2: return fondo2;
            case 3: return fondo3;
            case 4: return fondo4;
            case 5: return fondo5;
            case 6: return fondo6;
            case 7: return fondo7;
            case 8: return fondo8;
            case 9: return fondo9;
            case 10: return fondo10;
            case 11: return fondo11;
            case 12: return fondo12;
            case 13: return backgroundDay;
            case 14: return demo;
            default: return fondo1;
        }
    }

    public static TextureRegion getPlayer(String playerName) {
        switch (playerName) {
            case "whiteSupercatDown": return whiteSupercatDown;
            case "whiteSupercatMid": return whiteSupercatMid;
            case "whiteSupercatUp": return whiteSupercatUp;
            default: return whiteSupercatUp;
        }
    }

    public static void dispose() {
        manager.dispose();
    }
}
