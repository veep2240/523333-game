package sut.game01.core;

//import junit.framework.Test;
import playn.core.Image;
import playn.core.ImageLayer;
import playn.core.Pointer;
import playn.core.util.Callback;
import react.UnitSlot;
import sut.game01.sprite.zealot;
import sut.game01.sprite.HP;
import tripleplay.game.ScreenStack;
import tripleplay.game.Screen;
import tripleplay.game.UIScreen;
import tripleplay.ui.*;
import tripleplay.ui.layout.AxisLayout;
import playn.core.Pointer;


import static playn.core.PlayN.*;

import static playn.core.PlayN.graphics;

/**
 * Created by all user on 22/1/2557.
 */
public class TestScreen extends Screen{

    private ScreenStack ss;
    private Root root;
    private zealot z ;
    private HP x = new HP(200f,200f);

    public TestScreen(ScreenStack ss){
        this.ss = ss;
    }
    @Override
    public void wasAdded(){
        super.wasAdded();




        Image bgImage = assets().getImage("images/bg.png");
        bgImage.addCallback(new Callback<Image>() {
            @Override
            public void onSuccess(Image result) {
            }

            @Override
            public void onFailure(Throwable cause) {
            }
        });
        ImageLayer bgLayer = graphics().createImageLayer(bgImage);
        layer.add(bgLayer);

        Image backImage = assets().getImage("images/back.png");
        ImageLayer backLayer = graphics().createImageLayer(backImage);
        backLayer.setSize(60,60);
        backLayer.setOrigin(30,30);
        backLayer.setTranslation(320,240);
        layer.add(backLayer);
        backLayer.addListener(new Pointer.Adapter() {
            @Override
            public void onPointerEnd(Pointer.Event event) {
                //ss.remove(TestScreen.this);
                ss.remove(ss.top());
            }
           });



//       layer.add(z.layer());

    }

    @Override
    public void update(int delta) {
        System.out.print("asdasd");
        super.update(delta);
//        z.update(delta);

    }
}