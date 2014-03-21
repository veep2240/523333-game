package sut.game01.core;

import playn.core.Image;
import playn.core.ImageLayer;
import playn.core.util.Callback;
import tripleplay.game.Screen;
import tripleplay.game.ScreenStack;

import static playn.core.PlayN.assets;
import static playn.core.PlayN.graphics;

/**
 * Created by rst706 on 3/21/14.
 */
public class ShopScreen extends Screen{
    ScreenStack ss;
    public ShopScreen (ScreenStack ss){
        this.ss = ss;
    }

    @Override
    public void wasAdded() {
        super.wasAdded();
        Image bgImage = assets().getImage("images/shop.png");

        bgImage.addCallback(new Callback<Image>() {
            @Override
            public void onSuccess(Image result) {

            }

            @Override
            public void onFailure(Throwable cause) {

            }
        });
        ImageLayer bgLayer = graphics().createImageLayer(bgImage);
        bgLayer.setSize(640,480);
        layer.add(bgLayer);
    }
}
