package sut.game01.core;

import playn.core.Image;
import playn.core.ImageLayer;
import playn.core.Pointer;
import playn.core.util.Callback;
import tripleplay.game.Screen;
import tripleplay.game.ScreenStack;

import static playn.core.PlayN.assets;
import static playn.core.PlayN.graphics;

/**
 * Created by rst706 on 2/26/14.
 */
public class StartScreen extends Screen {
    ScreenStack ss;

    public StartScreen(ScreenStack ss) {
        this.ss = ss;

    }

    @Override
    public void wasAdded() {
        super.wasAdded();

        Image bgImage = assets().getImage("images/bgstart.png");

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


        Image bbImage = assets().getImage("images/startbutton.png");
        ImageLayer bbLayer =graphics().createImageLayer(bbImage);
        bbLayer.setSize(183,40);
        bbLayer.setOrigin(91,20);
        bbLayer.setTranslation(300,230);
        layer.add(bbLayer);
        bbLayer.addListener(new Pointer.Adapter(){
            @Override
            public void onPointerEnd(Pointer.Event event) {
                ss.push(new ShopScreen(ss));
            }
        });

        Image bb1Image = assets().getImage("images/settingbutton.png");
        ImageLayer bb1Layer =graphics().createImageLayer(bb1Image);
        bb1Layer.setSize(183,40);
        bb1Layer.setOrigin(91,20);
        bb1Layer.setTranslation(300,280);
        layer.add(bb1Layer);
        bb1Layer.addListener(new Pointer.Adapter(){
            @Override
            public void onPointerEnd(Pointer.Event event) {
                ss.push(new GameScreen(ss));
            }
        });

        Image bb2Image = assets().getImage("images/aboutbutton.png");
        ImageLayer bb2Layer =graphics().createImageLayer(bb2Image);
        bb2Layer.setSize(183,40);
        bb2Layer.setOrigin(91,20);
        bb2Layer.setTranslation(300,330);
        layer.add(bb2Layer);
        bb2Layer.addListener(new Pointer.Adapter(){
            @Override
            public void onPointerEnd(Pointer.Event event) {
                ss.push(new GameScreen(ss));
            }
        });


    }

}
