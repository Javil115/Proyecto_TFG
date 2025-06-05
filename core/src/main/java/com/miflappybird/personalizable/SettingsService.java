package com.miflappybird.personalizable;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class SettingsService {

    private static final String PREFS_NAME = "flappy_settings";
    private static final String KEY_PLAYER = "player";
    private static final String KEY_PIPE = "pipe";
    private static final String KEY_BACKGROUND = "background";

    private final Preferences prefs;

    public SettingsService() {
        prefs = Gdx.app.getPreferences(PREFS_NAME);
    }

    public void savePlayer(String playerName) {
        prefs.putString(KEY_PLAYER, playerName);
        prefs.flush();
    }

    public String getPlayer() {
        return prefs.getString(KEY_PLAYER, "blackSupercat"); // valor por defecto
    }

    public void savePipe(String pipeName) {
        prefs.putString(KEY_PIPE, pipeName);
        prefs.flush();
    }

    public String getPipe() {
        return prefs.getString(KEY_PIPE, "tile004");
    }

    public void saveBackground(String backgroundName) {
        prefs.putString(KEY_BACKGROUND, backgroundName);
        prefs.flush();
    }

    public String getBackground() {
        return prefs.getString(KEY_BACKGROUND, "background-day");
    }
}
