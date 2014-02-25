package sut.game01.core;

import playn.core.Font;
import react.UnitSlot;
import tripleplay.game.ScreenStack;
import tripleplay.ui.*;
import tripleplay.game.UIScreen;
import tripleplay.game.Screen;
import tripleplay.ui.layout.AxisLayout;

import static playn.core.PlayN.graphics;

/**
 * Created by all user on 22/1/2557.
 */
public class HomeScreen extends UIScreen{

    public static final Font TITLE_FONT = graphics().createFont(
            "Helvertica", Font.Style.BOLD,24);
    private final ScreenStack ss;

    private Root root;

    public HomeScreen(ScreenStack ss){
        this.ss = ss;
    }

    @Override
    public void wasShown() {
        super.wasShown();
        root = iface.createRoot(AxisLayout.vertical().gap(15)
                , SimpleStyles.newSheet(), layer);
        root.addStyles(Style.BACKGROUND
                        .is(Background.bordered(0xFFCCCCCC, 0xFF99CCFF, 5)
                        .inset(5, 10)));
        root.setSize(width(), height());

        root.add(new Label("Lab3 TEST")
                .addStyles(Style.FONT.is(HomeScreen.TITLE_FONT)));

        root.add(new Button("Start").onClick(new UnitSlot() {
            @Override
            public void onEmit() {
                ss.push(new TestScreen(ss));
            }
        }));
        root.add(new Button("Game").onClick(new UnitSlot() {
            @Override
            public void onEmit() {
                ss.push(new GameScreen(ss));
            }
        }));


    }
}
