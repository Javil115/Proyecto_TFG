package com.miflappybird.personalizable;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.HashMap;

public class AssetsManager {

    public static AssetManager manager;

    public static TextureRegion backgroundAfternoon, backgroundDay, backgroundFutureDay, backgroundFutureNight,
        backgroundMoon, backgroundMoonNight, backgroundMorning, backgroundNeonSunset,
        backgroundStarWars1, backgroundStarWars2;

    public static TextureRegion base, baseBlue, baseBlueGray, baseMorning,
        baseNeon, baseOrange, baseStarWarsColors;

    public static TextureRegion demo;

    // Player Animations
    public static TextureRegion blackSupercatDown, blackSupercatMid, blackSupercatUp;
    public static TextureRegion dogDown, dogMid, dogUp;
    public static TextureRegion halloweenCatDown, halloweenCatMid, halloweenCatUp;
    public static TextureRegion orangeSupercatDown, orangeSupercatMid, orangeSupercatUp;
    public static TextureRegion vultureDown, vultureMid, vultureUp;
    public static TextureRegion whiteSupercatDown, whiteSupercatMid, whiteSupercatUp;
    public static TextureRegion yellowbirdDown, yellowbirdMid, yellowbirdUp;

    public static TextureRegion botonJugar, botonMarcador, botonOpciones, botonPausa;
    public static TextureRegion botonSeguir, botonSalir, botonSalirLetras;
    public static TextureRegion botonInformacion, fondoPausaJuego, botonX, botonJugarDeNuevo;
    public static TextureRegion botonSi, botonNo;
    public static TextureRegion cambiarEscenario, cambiarJugador, cambiarTuberia;
    public static TextureRegion mejoresPuntuacionesTitle, fondoBorrar, iconoBasura;


    // Pipes
    public static TextureRegion[] pipes;
    public static TextureRegion pipeGreen;
    public static HashMap<String, TextureRegion> namedPipes;

    public static void load() {
        manager = new AssetManager();
        manager.load("backgrounds/fondos_fb.png", Texture.class);
        manager.load("player/player.png", Texture.class);
        manager.load("pipes/Pipes.png", Texture.class);
        manager.load("Buttons/Buttons.png", Texture.class);
        manager.finishLoading();

        Texture fondosTexture = manager.get("backgrounds/fondos_fb.png", Texture.class);

        backgroundAfternoon = new TextureRegion(fondosTexture, 960, 112, 324, 576);
        backgroundDay = new TextureRegion(fondosTexture, 672, 112, 288, 512);
        backgroundFutureDay = new TextureRegion(fondosTexture, 1284, 112, 325, 576);
        backgroundFutureNight = new TextureRegion(fondosTexture, 1609, 112, 340, 576);
        backgroundMoon = new TextureRegion(fondosTexture, 0, 688, 324, 576);
        backgroundMoonNight = new TextureRegion(fondosTexture, 324, 688, 324, 576);
        backgroundMorning = new TextureRegion(fondosTexture, 648, 688, 324, 576);
        backgroundNeonSunset = new TextureRegion(fondosTexture, 972, 688, 338, 576);
        backgroundStarWars1 = new TextureRegion(fondosTexture, 1310, 688, 324, 576);
        backgroundStarWars2 = new TextureRegion(fondosTexture, 1634, 688, 324, 576);

        base = new TextureRegion(fondosTexture, 0, 0, 336, 112);
        baseBlue = new TextureRegion(fondosTexture, 336, 0, 336, 112);
        baseBlueGray = new TextureRegion(fondosTexture, 672, 0, 336, 112);
        baseMorning = new TextureRegion(fondosTexture, 1008, 0, 336, 112);
        baseNeon = new TextureRegion(fondosTexture, 1344, 0, 336, 112);
        baseOrange = new TextureRegion(fondosTexture, 0, 112, 336, 112);
        baseStarWarsColors = new TextureRegion(fondosTexture, 336, 112, 336, 112);

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

        Texture buttonsTexture = manager.get("Buttons/Buttons.png", Texture.class);
        botonX = new TextureRegion(buttonsTexture, 0, 181, 201, 211);
        botonPausa = new TextureRegion(buttonsTexture, 201, 181, 184, 222);
        botonSeguir = new TextureRegion(buttonsTexture, 385, 181, 423, 226);
        botonJugarDeNuevo = new TextureRegion(buttonsTexture, 808, 181, 430, 227);
        botonSalirLetras = new TextureRegion(buttonsTexture, 0, 408, 417, 231);
        botonSalir = new TextureRegion(buttonsTexture, 910, 1254, 249, 447);
        botonJugar = new TextureRegion(buttonsTexture, 323, 685, 755, 282);
        botonOpciones = new TextureRegion(buttonsTexture, 0, 967, 755, 282);
        botonMarcador = new TextureRegion(buttonsTexture, 755, 967, 754, 287);
        fondoPausaJuego = new TextureRegion(buttonsTexture, 0, 1254, 214, 330);
        botonInformacion = new TextureRegion(buttonsTexture, 498, 1254, 412, 410);
        botonSi = new TextureRegion(buttonsTexture, 0, 685, 323, 277);
        botonNo = new TextureRegion(buttonsTexture, 1200, 408, 336, 277);
        cambiarEscenario = new TextureRegion(buttonsTexture, 0, 0, 480, 145);
        cambiarJugador = new TextureRegion(buttonsTexture, 417, 408, 783, 273);
        cambiarTuberia = new TextureRegion(buttonsTexture, 480, 0, 599, 179);
        mejoresPuntuacionesTitle = new TextureRegion(buttonsTexture, 1079, 0, 488, 181);
        fondoBorrar = new TextureRegion(buttonsTexture, 1159, 1254, 340, 562);
        iconoBasura = new TextureRegion(buttonsTexture, 214, 1254, 284, 361);

        Texture pipesTexture = manager.get("pipes/Pipes.png", Texture.class);

        namedPipes = new HashMap<>();
        for (int i = 0; i <= 7; i++) {
            String name = String.format("tile00%d", i);
            int x = (i % 2) * 32;
            int y = 1088 + (i / 2) * 80;
            namedPipes.put(name, new TextureRegion(pipesTexture, x, y, 32, 80));
        }

        pipeGreen = new TextureRegion(pipesTexture, 0, 768, 52, 320);
        namedPipes.put("pipe-green", pipeGreen);
    }

    public static TextureRegion getBackgroundByName(String name) {
        switch (name) {
            case "background-afternoon": return backgroundAfternoon;
            case "background-day": return backgroundDay;
            case "background-futureday": return backgroundFutureDay;
            case "background-futurenight": return backgroundFutureNight;
            case "background-moon": return backgroundMoon;
            case "background-moonnight": return backgroundMoonNight;
            case "background-morning": return backgroundMorning;
            case "background-neonsunset": return backgroundNeonSunset;
            case "background-starwars1": return backgroundStarWars1;
            case "background-starwars2": return backgroundStarWars2;
            default: return backgroundDay;
        }
    }

    public static TextureRegion getBaseByName(String name) {
        switch (name) {
            case "base": return base;
            case "base_blue": return baseBlue;
            case "base_blue_gray": return baseBlueGray;
            case "base_morning": return baseMorning;
            case "base_neon": return baseNeon;
            case "base_orange": return baseOrange;
            case "base_starwars_colors": return baseStarWarsColors;
            default: return base;
        }
    }

    public static TextureRegion getPlayer(String playerName) {
        switch (playerName) {
            case "blackSupercatDown": return blackSupercatDown;
            case "blackSupercatMid": return blackSupercatMid;
            case "blackSupercatUp": return blackSupercatUp;
            case "whiteSupercatDown": return whiteSupercatDown;
            case "whiteSupercatMid": return whiteSupercatMid;
            case "whiteSupercatUp": return whiteSupercatUp;
            case "orangeSupercatDown": return orangeSupercatDown;
            case "orangeSupercatMid": return orangeSupercatMid;
            case "orangeSupercatUp": return orangeSupercatUp;
            case "yellowbirdDown": return yellowbirdDown;
            case "yellowbirdMid": return yellowbirdMid;
            case "yellowbirdUp": return yellowbirdUp;
            case "vultureDown": return vultureDown;
            case "vultureMid": return vultureMid;
            case "vultureUp": return vultureUp;
            case "halloweenCatDown": return halloweenCatDown;
            case "halloweenCatMid": return halloweenCatMid;
            case "halloweenCatUp": return halloweenCatUp;
            default: return whiteSupercatUp;
        }
    }

    public static TextureRegion getButton(String buttonName) {
        switch (buttonName) {
            case "botonJugar": return botonJugar;
            case "botonMarcador": return botonMarcador;
            case "botonOpciones": return botonOpciones;
            case "botonPausa": return botonPausa;
            case "botonSeguir": return botonSeguir;
            case "botonSalir": return botonSalir;
            case "botonSalirLetras": return botonSalirLetras;
            case "botonInformacion": return botonInformacion;
            case "fondoPausaJuego": return fondoPausaJuego;
            case "botonX": return botonX;
            case "botonJugarDeNuevo": return botonJugarDeNuevo;
            case "botonSi": return botonSi;
            case "botonNo": return botonNo;
            case "cambiarEscenario": return cambiarEscenario;
            case "cambiarJugador": return cambiarJugador;
            case "cambiarTuberia": return cambiarTuberia;
            case "mejoresPuntuacionesTitle": return mejoresPuntuacionesTitle;
            case "fondoBorrar": return fondoBorrar;
            case "iconoBasura": return iconoBasura;
            default: return null;
        }
    }

    public static TextureRegion getPipe(String pipeName) {
        return namedPipes.getOrDefault(pipeName, pipeGreen);
    }

    public static void dispose() {
        manager.dispose();
    }
}
