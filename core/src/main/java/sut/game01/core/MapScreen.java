package sut.game01.core;

import playn.core.Image;
import playn.core.ImageLayer;
import playn.core.util.Callback;
import tripleplay.game.ScreenStack;
import tripleplay.game.UIScreen;

import static playn.core.PlayN.assets;
import static playn.core.PlayN.graphics;

/**
 * Created by rst706 on 3/21/14.
 */
public class MapScreen extends UIScreen {
   public ScreenStack ss;
   public MapScreen (ScreenStack ss){
       this.ss = ss;
   }

    @Override
    public void wasAdded() {
        super.wasAdded();
        Image bgImage = assets().getImage("images/map.png");

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
        Image m1Image = assets().getImage("images/gmap2.png");
        ImageLayer m1Layer =graphics().createImageLayer(m1Image);
        m1Layer.setSize(30,40);
        m1Layer.setOrigin(15,10);
        m1Layer.setTranslation(100,375);
        layer.add(m1Layer);
        Image m2Image = assets().getImage("images/gmap2.png");
        ImageLayer m2Layer =graphics().createImageLayer(m2Image);
        m2Layer.setSize(30,40);
        m2Layer.setOrigin(15,10);
        m2Layer.setTranslation(50,325);
        layer.add(m2Layer);
        Image m3Image = assets().getImage("images/gmap2.png");
        ImageLayer m3Layer =graphics().createImageLayer(m3Image);
        m3Layer.setSize(30,40);
        m3Layer.setOrigin(15,10);
        m3Layer.setTranslation(180,305);
        layer.add(m3Layer);
        Image m4Image = assets().getImage("images/gmap2.png");
        ImageLayer m4Layer =graphics().createImageLayer(m4Image);
        m4Layer.setSize(30,40);
        m4Layer.setOrigin(15,10);
        m4Layer.setTranslation(260,340);
        layer.add(m4Layer);
    }
}
