package sut.game01.core;

import playn.core.*;
import playn.core.util.Callback;
import playn.core.util.Clock;
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
    public static Root root,root1,root2;
    public static boolean orm = false,orp = false;
    public static Image se1Image,sef1Image;
    public static ImageLayer se1Layer,sef1Layer;
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

        se1Image = assets().getImage("images/sell.png");
        se1Layer =graphics().createImageLayer(se1Image);
        se1Layer.setSize(120,20);
        se1Layer.setOrigin(7,12);
        se1Layer.setTranslation(50,430);
        layer.add(se1Layer);

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

        sepLayer.addListener(new Pointer.Adapter(){
            @Override
            public void onPointerStart(Pointer.Event event) {
                super.onPointerStart(event);
                MyGame.ppp +=1;
                root.removeAll();
                orp = true;


            }
        });
        se1Layer.addListener(new Pointer.Adapter(){
            @Override
            public void onPointerStart(Pointer.Event event) {
                super.onPointerStart(event);
                layer.remove(se1Layer);
                sef1Image = assets().getImage("images/sellf.png");
                sef1Layer =graphics().createImageLayer(sef1Image);
                sef1Layer.setSize(120,20);
                sef1Layer.setOrigin(7,12);
                sef1Layer.setTranslation(50,430);
                layer.add(sef1Layer);
            }
        });

        goLayer.addListener(new Pointer.Adapter(){
            @Override
            public void onPointerStart(Pointer.Event event) {
                super.onPointerStart(event);
                ss.push(new MapScreen(ss));
            }
        });



    }

    @Override
    public void update(int delta) {
        super.update(delta);

        if (orm){
        root1 = iface.createRoot(
                AxisLayout.vertical().gap(15),
                SimpleStyles.newSheet(), layer);
        root1.setSize(1075, 50);

        root1.add(new Label(String.valueOf(MyGame.money)).addStyles(Style.FONT.is(ShopScreen.TITLE_FONT)));
            }
        if (orp){
            root = iface.createRoot(
                    AxisLayout.vertical().gap(15),
                    SimpleStyles.newSheet(), layer);
            root.setSize(1200, 200);

            root.add(new Label(String.valueOf(MyGame.ppp)).addStyles(Style.FONT.is(ShopScreen.TITLE_FONT)));
            orp =false;

        }
    }

    @Override
    public void paint(Clock clock) {
        super.paint(clock);
    }
}
