package com.akash.jumpybird.game.menus;

import com.akash.jumpybird.helpers.AssetLoader;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MainMenu {
    private boolean isPressed;
    private int screenWidth, screenHeight, playButtonWidth, playButtonHeight;
    private int playButtonX, playButtonY;

    public MainMenu(int screenWidth, int screenHeight) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        playButtonWidth = AssetLoader.playButtonDown.getRegionWidth();
        playButtonHeight = AssetLoader.playButtonDown.getRegionHeight();
        playButtonX = (screenWidth / 2) - 15;
        playButtonY = screenHeight / 2 + 20;
    }

    public void update(float delta) {
    }

    public void render(SpriteBatch spriteBatch) {
        renderTitle(spriteBatch);
        renderPlayButton(spriteBatch);
    }

    private void renderPlayButton(SpriteBatch spriteBatch) {
//        if (isPressed) {
//            spriteBatch.draw(
//                    AssetLoader.playButtonDown,
//                    playButtonX,
//                    playButtonY,
//                    playButtonWidth / 2.0f,
//                    playButtonHeight / 2.0f,
//                    playButtonWidth,
//                    playButtonHeight,
//                    1,
//                    1,
//                    0
//            );
//        } else {
            spriteBatch.draw(
                    AssetLoader.playButtonUp,
                    playButtonX,
                    playButtonY,
                    screenWidth / 2.0f,
                    screenHeight / 2.0f,
                    playButtonWidth,
                    playButtonHeight,
                    1,
                    1,
                    0
            );
//        }
    }

    private void renderTitle(SpriteBatch spriteBatch) {
        AssetLoader.shadow.draw(
                spriteBatch,
                "Jumpy Bird",
                (136 / 2f) - (50),
                21
        );
        AssetLoader.font.draw(
                spriteBatch,
                "Jumpy Bird",
                (136 / 2f) - (50 - 1),
                20
        );
    }

    public boolean isPressed() {
        return isPressed;
    }

    public boolean checkPlayButtonPressed(int x, int y) {
        return (isPressed = (x >= this.playButtonX
                && x <= (this.playButtonX + playButtonWidth)
                && y >= this.playButtonY
                && y <= (this.playButtonY + playButtonHeight)));
    }
}
