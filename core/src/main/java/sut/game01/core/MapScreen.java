package sut.game01.core;

import playn.core.Image;
import playn.core.ImageLayer;
import playn.core.Pointer;
import playn.core.util.Callback;
import tripleplay.game.ScreenStack;
import tripleplay.game.UIScreen;

import static playn.core.PlayN.assets;
import static playn.core.PlayN.graphics;

/**
 * Created by rst706 on 3/21/14.
 */
public class MapScreen extends UIScreen {
   public Image m1Image, m2Image, m3Image, m4Image,mf1Image, mf2Image, mf3Image, mf4Image;
   public ImageLayer m1Layer,m2Layer,m3Layer,m4Layer,mf1Layer,mf2Layer,mf3Layer,mf4Layer;
   public ScreenStack ss;
   public boolean v1=true,v2=true,v3=true,v4=true,v12=true,v13=true,v14=true;
   public MapScreen (ScreenStack ss){
       this.ss = ss;
   }

    @Override
    public void wasAdded() {
        super.wasAdded();
        Image bgImage = assets().getImage("images/map.png");
        MyGame.v12=true;
        MyGame.v13=true;
        MyGame.v14=true;

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
         m1Image = assets().getImage("images/gmap1.png");
         m1Layer =graphics().createImageLayer(m1Image);
        m1Layer.setSize(30,40);
        m1Layer.setOrigin(15,10);
        m1Layer.setTranslation(100,375);
        layer.add(m1Layer);
         m2Image = assets().getImage("images/gmap2.png");
         m2Layer =graphics().createImageLayer(m2Image);
        m2Layer.setSize(30,40);
        m2Layer.setOrigin(15,10);
        m2Layer.setTranslation(50,325);
        layer.add(m2Layer);
         m3Image = assets().getImage("images/gmap2.png");
         m3Layer =graphics().createImageLayer(m3Image);
        m3Layer.setSize(30,40);
        m3Layer.setOrigin(15,10);
        m3Layer.setTranslation(180,305);
        layer.add(m3Layer);
         m4Image = assets().getImage("images/gmap2.png");
         m4Layer =graphics().createImageLayer(m4Image);
        m4Layer.setSize(30,40);
        m4Layer.setOrigin(15,10);
        m4Layer.setTranslation(260,340);
        layer.add(m4Layer);
        m1Layer.addListener(new Pointer.Adapter(){
            @Override
            public void onPointerStart(Pointer.Event event) {
                super.onPointerStart(event);
                ss.push(new GameScreen(ss));

            }
        });
        Image reImage = assets().getImage("images/reload.png");
        ImageLayer reLayer =graphics().createImageLayer(reImage);
        reLayer.setSize(44,44);
        reLayer.setOrigin(22,22);
        reLayer.setTranslation(40,40);
        layer.add(reLayer);
        reLayer.addListener(new Pointer.Adapter(){
            @Override
            public void onPointerStart(Pointer.Event event) {
                super.onPointerStart(event);
                ss.remove(MapScreen.this);
            }
        });
        if (MyGame.sta>=2){
            if (v2==true){
                layer.remove(m2Layer);
                mf2Image = assets().getImage("images/gmap1.png");
                mf2Layer =graphics().createImageLayer(mf2Image);
                mf2Layer.setSize(30,40);
                mf2Layer.setOrigin(15,10);
                mf2Layer.setTranslation(50,325);
                layer.add(mf2Layer);

                v2=false;
            }
        }
        if (MyGame.sta>=3){
            if (v3==true){
                layer.remove(m3Layer);
                mf3Image = assets().getImage("images/gmap1.png");
                mf3Layer =graphics().createImageLayer(mf3Image);
                mf3Layer.setSize(30,40);
                mf3Layer.setOrigin(15,10);
                mf3Layer.setTranslation(180,305);
                layer.add(mf3Layer);

                v3=false;
            }


        }
        if (MyGame.sta>=4){

            if (v4==true){
                layer.remove(m4Layer);
                mf4Image = assets().getImage("images/gmap1.png");
                mf4Layer =graphics().createImageLayer(mf4Image);
                mf4Layer.setSize(30,40);
                mf4Layer.setOrigin(15,10);
                mf4Layer.setTranslation(260,340);
                layer.add(mf4Layer);

                v4=false;
            }

        }

    }

    @Override
    public void update(int delta) {

        super.update(delta);
        if (MyGame.sta>=2){
            if (v2==true){
                layer.remove(m2Layer);
                mf2Image = assets().getImage("images/gmap1.png");
                mf2Layer =graphics().createImageLayer(mf2Image);
                mf2Layer.setSize(30,40);
                mf2Layer.setOrigin(15,10);
                mf2Layer.setTranslation(50,325);
                layer.add(mf2Layer);

                v2=false;
            }
            mf2Layer.addListener(new Pointer.Adapter(){
                @Override
                public void onPointerStart(Pointer.Event event) {
                    if(MyGame.v12==true){
                        super.onPointerStart(event);
                        ss.push(new GameScreen(ss));
                        MyGame.v12=false;
                    }

                }
            });
        }
        if (MyGame.sta>=3){
            if (v3==true){
                layer.remove(m3Layer);
                mf3Image = assets().getImage("images/gmap1.png");
                mf3Layer =graphics().createImageLayer(mf3Image);
                mf3Layer.setSize(30,40);
                mf3Layer.setOrigin(15,10);
                mf3Layer.setTranslation(180,305);
                layer.add(mf3Layer);

                v3=false;
            }
            mf3Layer.addListener(new Pointer.Adapter(){
                @Override
                public void onPointerStart(Pointer.Event event) {
                    if(MyGame.v13==true){
                        super.onPointerStart(event);
                        ss.push(new GameScreen(ss));
                        MyGame.v13=false;
                    }

                }
            });


        }
        if (MyGame.sta>=4){

            if (v4==true){
                layer.remove(m4Layer);
                mf4Image = assets().getImage("images/gmap1.png");
                mf4Layer =graphics().createImageLayer(mf4Image);
                mf4Layer.setSize(30,40);
                mf4Layer.setOrigin(15,10);
                mf4Layer.setTranslation(260,340);
                layer.add(mf4Layer);

                v4=false;
            }
            mf4Layer.addListener(new Pointer.Adapter(){
                @Override
                public void onPointerStart(Pointer.Event event) {
                    if(MyGame.v14==true){
                        super.onPointerStart(event);
                        ss.push(new GameScreen(ss));
                        MyGame.v14=false;
                    }

                }
            });

        }




    }
    public void reset(){
        v12=true;
        v13=true;
        v14=true;


    }

    }

