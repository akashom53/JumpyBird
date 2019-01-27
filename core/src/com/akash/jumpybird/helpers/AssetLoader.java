package com.akash.jumpybird.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public abstract class AssetLoader {

    private static AssetLoader instance;

    public static Texture texture, logoTexture, akkipediaLogoTexture;
    public static TextureRegion logo, zbLogo, bg, grass, bird, birdDown, birdUp, skullUp, skullDown, bar, playButtonUp, playButtonDown;
    public static Animation<TextureRegion> birdAnimation;
    public static Sound dead, flap, coin;
    public static BitmapFont font, shadow;
    protected Preferences prefs;

    public static void loadGameAssets() {
        instance = new AsynchronousLoader();
        instance.loadActual();
    }

    public static void loadSplashAssets(){
        akkipediaLogoTexture = new Texture(Gdx.files.internal("akkipedia.png"));
        akkipediaLogoTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
    }

    public static void disposeSplashAssets(){
        akkipediaLogoTexture.dispose();
    }

    protected abstract void loadActual();


    public static boolean update(){
        return instance.updateActual();
    }

    protected abstract boolean updateActual();

    public static float getProgress(){
        return instance.getProgressActual();
    }

    protected abstract float getProgressActual();

    public static void setHighScore(int val) {
        instance.prefs.putInteger("highScore", val);
        instance.prefs.flush();
    }

    public static int getHighScore() {
        return instance.prefs.getInteger("highScore");
    }


    public static void dispose() {
        instance.disposeActual();
    }

    protected abstract void disposeActual();
}
