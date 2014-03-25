package sut.game01.core;

import playn.core.*;
import playn.core.util.Callback;
import playn.core.util.Clock;
import sut.game01.sprite.carshop;
import tripleplay.game.Screen;
import tripleplay.game.ScreenStack;
import tripleplay.game.UIScreen;
import tripleplay.ui.Label;
import tripleplay.ui.Root;
import tripleplay.ui.SimpleStyles;
import tripleplay.ui.Style;
import tripleplay.ui.layout.AxisLayout;

import static playn.core.PlayN.assets;
import static playn.core.PlayN.graphics;


/**
 * Created by rst706 on 3/21/14.
 */
public class ShopScreen extends UIScreen{
    public static Root root,root1,root2,root3,root4,root5;
    public static boolean orm = true,orp = false;
    public static Image se1Image,sef1Image,se2Image,sef2Image;
    public static ImageLayer se1Layer,sef1Layer,se2Layer,sef2Layer;
    public static Image d1,d2;
    public static ImageLayer dl1,dl2;
    public int sd=300,spp=100;
    public boolean opdi=false,tt=true;


    public carshop c ;
    public static final Font TITLE_FONT = PlayN.graphics().createFont(
            "Helvetica",
            Font.Style.BOLD,
            24
    );
    public ScreenStack ss;
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
        Image bbImage = assets().getImage("images/money.png");
        ImageLayer bbLayer =graphics().createImageLayer(bbImage);
        bbLayer.setSize(30,30);
        bbLayer.setOrigin(15,15);
        bbLayer.setTranslation(420,30);
        layer.add(bbLayer);

        Image pImage = assets().getImage("images/62956.jpg");
        ImageLayer pLayer =graphics().createImageLayer(pImage);
        pLayer.setSize(60,60);
        pLayer.setOrigin(30,30);
        pLayer.setTranslation(440,100);
        layer.add(pLayer);

        Image dImage = assets().getImage("images/diver.png");
        ImageLayer dLayer =graphics().createImageLayer(dImage);
        dLayer.setSize(100,21);
        dLayer.setOrigin(50,10);
        dLayer.setTranslation(100,390);
        layer.add(dLayer);

        se1Image = assets().getImage("images/sellf.png");
        se1Layer =graphics().createImageLayer(se1Image);
        se1Layer.setSize(120,20);
        se1Layer.setOrigin(7,12);
        se1Layer.setTranslation(50,430);
        layer.add(se1Layer);

        Image d2Image = assets().getImage("images/diver2.png");
        ImageLayer d2Layer =graphics().createImageLayer(d2Image);
        d2Layer.setSize(100,21);
        d2Layer.setOrigin(50,10);
        d2Layer.setTranslation(250,390);
        layer.add(d2Layer);

        se2Image = assets().getImage("images/sell.png");
        se2Layer =graphics().createImageLayer(se2Image);
        se2Layer.setSize(120,20);
        se2Layer.setOrigin(7,12);
        se2Layer.setTranslation(200,430);
        layer.add(se2Layer);

        Image sepImage = assets().getImage("images/sell.png");
        ImageLayer sepLayer =graphics().createImageLayer(sepImage);
        sepLayer.setSize(120,20);
        sepLayer.setOrigin(7,12);
        sepLayer.setTranslation(475,105);
        layer.add(sepLayer);



        Image goImage = assets().getImage("images/butst.png");
        ImageLayer goLayer =graphics().createImageLayer(goImage);
        goLayer.setSize(120,20);
        goLayer.setOrigin(7,12);
        goLayer.setTranslation(150,300);
        layer.add(goLayer);

        root1 = iface.createRoot(
                AxisLayout.vertical().gap(15),
                SimpleStyles.newSheet(), layer);
        root1.setSize(1075, 50);

        root1.add(new Label(String.valueOf(MyGame.money)).addStyles(Style.FONT.is(ShopScreen.TITLE_FONT)));

        root = iface.createRoot(
                AxisLayout.vertical().gap(15),
                SimpleStyles.newSheet(), layer);
        root.setSize(1200, 200);

        root.add(new Label(String.valueOf(MyGame.ppp)).addStyles(Style.FONT.is(ShopScreen.TITLE_FONT)));
        root2 = iface.createRoot(
                AxisLayout.vertical().gap(15),
                SimpleStyles.newSheet(), layer);
        root2.setSize(505, 895);

        root2.add(new Label(String.valueOf(sd)).addStyles(Style.FONT.is(ShopScreen.TITLE_FONT)));
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
                ss.remove(ShopScreen.this);
            }
        });

        sepLayer.addListener(new Pointer.Adapter(){
            @Override
            public void onPointerStart(Pointer.Event event) {
                super.onPointerStart(event);
                if(MyGame.money>=spp){
                MyGame.ppp +=1;
                root.removeAll();
                orp = true;
                    MyGame.money-=spp;
                }

            }
        });
        se1Layer.addListener(new Pointer.Adapter(){
            @Override
            public void onPointerStart(Pointer.Event event) {
                super.onPointerStart(event);

                sef1Image = assets().getImage("images/sellf.png");
                sef1Layer =graphics().createImageLayer(sef1Image);
                sef1Layer.setSize(120,20);
                sef1Layer.setOrigin(7,12);
                sef1Layer.setTranslation(50,430);
                layer.add(sef1Layer);
            }
        });
        se2Layer.addListener(new Pointer.Adapter(){
            @Override
            public void onPointerStart(Pointer.Event event) {
                super.onPointerStart(event);
                if(MyGame.money>=sd){
                sef2Image = assets().getImage("images/sellf.png");
                sef2Layer =graphics().createImageLayer(sef2Image);
                sef2Layer.setSize(120,20);
                sef2Layer.setOrigin(7, 12);
                sef2Layer.setTranslation(200, 430);
                MyGame.money-=sd;
                root1.removeAll();
                orm = true;
                opdi =true;
                MyGame.opdi2=true;
                layer.add(sef2Layer);}
            }
        });

        dLayer.addListener(new Pointer.Adapter(){
            @Override
            public void onPointerStart(Pointer.Event event) {
                super.onPointerStart(event);
                MyGame.cd=true;

                MyGame.statediver = MyGame.Statediver.d1;

            }
        });
        d2Layer.addListener(new Pointer.Adapter(){
            @Override
            public void onPointerStart(Pointer.Event event) {
                super.onPointerStart(event);
                if (opdi==true){
                MyGame.cd=true;

                MyGame.statediver = MyGame.Statediver.d2;
                }

            }
        });



        if (MyGame.statediver == MyGame.Statediver.d1){

            d1 = assets().getImage("images/diver.png");
            dl1 =graphics().createImageLayer(d1);
            dl1.setSize(60f,10f);
            dl1.setOrigin(47, 10 / 2f);
            dl1.setTranslation(220,155);
            layer.add(dl1);
            MyGame.d1 = 1;
            MyGame.d2 = 0;
        }
        else if (MyGame.statediver == MyGame.Statediver.d2){
            d2 = assets().getImage("images/diver2.png");
            dl2 =graphics().createImageLayer(d1);
            dl2.setSize(60f,10f);
            dl2.setOrigin(30,5);
            dl2.setTranslation(220,155);
            layer.add(dl2);
            MyGame.d1 = 0;
            MyGame.d2 = 1;
        }

        goLayer.addListener(new Pointer.Adapter(){
            @Override
            public void onPointerStart(Pointer.Event event) {
                super.onPointerStart(event);
                ss.push(new MapScreen(ss));
            }
        });

        c = new carshop(225,200);
        layer.add(c.layer());



    }

    @Override
    public void update(int delta) {
        super.update(delta);
        if (MyGame.statediver == MyGame.Statediver.d1){
            if (MyGame.cd ==true){
                if (MyGame.d2 == 1){
                    layer.remove(dl2);
                    d1 = assets().getImage("images/diver.png");
                    dl1 =graphics().createImageLayer(d1);
                    dl1.setSize(60f,10f);
                    dl1.setOrigin(47, 10 / 2f);
                    dl1.setTranslation(220,155);
                    layer.add(dl1);
                    MyGame.cd=false;
                    MyGame.d1 = 1;
                    MyGame.d2 = 0;
                }
            }

        }
        else if (MyGame.statediver == MyGame.Statediver.d2){
            if (MyGame.cd ==true){
                if (MyGame.d1 == 1){
                    layer.remove(dl1);
                    d2 = assets().getImage("images/diver2.png");
                    dl2 =graphics().createImageLayer(d2);
                    dl2.setSize(60f,10f);
                    dl2.setOrigin(30,5);
                    dl2.setTranslation(220,155);
                    layer.add(dl2);
                    MyGame.cd=false;
                    MyGame.d1 = 0;
                    MyGame.d2 = 1;
                }
            }

        }
        if(MyGame.opdi2){
            if(tt){
            sef2Image = assets().getImage("images/sellf.png");
            sef2Layer =graphics().createImageLayer(sef2Image);
            sef2Layer.setSize(120,20);
            sef2Layer.setOrigin(7, 12);
            sef2Layer.setTranslation(200, 430);
            layer.add(sef2Layer);
                tt=false;
            }

        }

        if (orm){
            root1.removeAll();
        root1 = iface.createRoot(
                AxisLayout.vertical().gap(15),
                SimpleStyles.newSheet(), layer);
        root1.setSize(1075, 50);

        root1.add(new Label(String.valueOf(MyGame.money)).addStyles(Style.FONT.is(ShopScreen.TITLE_FONT)));
            }
        if (orp){
            root.removeAll();
            root = iface.createRoot(
                    AxisLayout.vertical().gap(15),
                    SimpleStyles.newSheet(), layer);
            root.setSize(1200, 200);

            root.add(new Label(String.valueOf(MyGame.ppp)).addStyles(Style.FONT.is(ShopScreen.TITLE_FONT)));
            orp =false;

        }
        c.update(delta);

    }

    @Override
    public void paint(Clock clock) {
        super.paint(clock);
        c.paint(clock);
    }
}
