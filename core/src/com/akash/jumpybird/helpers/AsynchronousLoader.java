package com.akash.jumpybird.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AsynchronousLoader extends AssetLoader {
    private AssetManager assetManager;

    private void loadAssets(){
        assetManager = new AssetManager();

        assetManager.load("logo.png", Texture.class);
        assetManager.load("texture.png", Texture.class);
        assetManager.load("dead.wav", Sound.class);
        assetManager.load("flap.wav", Sound.class);
        assetManager.load("coin.wav", Sound.class);
        assetManager.load("text.fnt", BitmapFont.class);
        assetManager.load("shadow.fnt", BitmapFont.class);
    }

    private void acquireAssets(){
        logoTexture = assetManager.get("logo.png");
        logoTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        logo = new TextureRegion(logoTexture, 0, 0, 512, 114);

        texture = assetManager.get("texture.png");
        texture.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        playButtonUp = new TextureRegion(texture, 0, 83, 29, 16);
        playButtonDown = new TextureRegion(texture, 29, 83, 29, 16);
        playButtonUp.flip(false, true);
        playButtonDown.flip(false, true);

        zbLogo = new TextureRegion(texture, 0, 55, 135, 24);
        zbLogo.flip(false, true);

        bg = new TextureRegion(texture, 0, 0, 136, 43);
        bg.flip(false, true);

        grass = new TextureRegion(texture, 0, 43, 143, 11);
        grass.flip(false, true);

        birdDown = new TextureRegion(texture, 136, 0, 17, 12);
        birdDown.flip(false, true);

        bird = new TextureRegion(texture, 153, 0, 17, 12);
        bird.flip(false, true);

        birdUp = new TextureRegion(texture, 170, 0, 17, 12);
        birdUp.flip(false, true);

        TextureRegion[] birds = {birdDown, bird, birdUp};
        birdAnimation = new Animation<TextureRegion>(0.06f, birds);
        birdAnimation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);

        skullUp = new TextureRegion(texture, 192, 0, 24, 14);
        // Create by flipping existing skullUp
        skullDown = new TextureRegion(skullUp);
        skullDown.flip(false, true);

        bar = new TextureRegion(texture, 136, 16, 22, 3);
        bar.flip(false, true);

        dead = assetManager.get("dead.wav");
        flap = assetManager.get("flap.wav");
        coin = assetManager.get("coin.wav");

        font = assetManager.get("text.fnt");
        font.getData().setScale(.25f, -.25f);

        shadow = assetManager.get("shadow.fnt");
        shadow.getData().setScale(.25f, -.25f);
    }

    @Override
    protected void loadActual() {
        loadAssets();

        prefs = Gdx.app.getPreferences("com.akash.flappyclone.preferences");

        if (!prefs.contains("highScore")) {
            prefs.putInteger("highScore", 0);
        }
    }

    @Override
    public boolean updateActual() {
        if (assetManager.update()){
            acquireAssets();
            return true;
        }
        return false;
    }

    @Override
    public float getProgressActual() {
        return assetManager.getProgress();
    }

    @Override
    protected void disposeActual() {

    }
}
