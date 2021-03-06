package sut.game01.core;

import static playn.core.PlayN.*;

import playn.core.Game;
import playn.core.Image;
import playn.core.ImageLayer;
import playn.core.util.Clock;
import tripleplay.game.ScreenStack;

public class MyGame extends Game.Default {

    public static final int UPDATE_RATE = 33;
    private ScreenStack ss = new ScreenStack();
    protected final Clock.Source clock = new Clock.Source(UPDATE_RATE);
    public static int money = 300;
    public static int ppp=3;
    public static enum Statediver{d1,d2};
    public static Statediver statediver = Statediver.d1;
    public static boolean cd = false;
    public static int d1 ,d2;
    public static int sta=1;
    public static boolean opdi2=false,v12=true,v13=true,v14=true;


  public MyGame() {
    super(UPDATE_RATE); // call update every 33ms (30 times per second)
  }

  @Override
  public void init() {
    ss.push(new StartScreen(ss));
  }

  @Override
  public void update(int delta) {
    ss.update(delta);
  }

  @Override
  public void paint(float alpha) {
    clock.paint(alpha);
    ss.paint(clock);

  }


}
