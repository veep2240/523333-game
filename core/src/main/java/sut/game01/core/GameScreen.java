package sut.game01.core;

import org.jbox2d.callbacks.ContactImpulse;
import org.jbox2d.callbacks.ContactListener;
import org.jbox2d.callbacks.DebugDraw;
import org.jbox2d.collision.Manifold;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.dynamics.*;
import org.jbox2d.dynamics.contacts.Contact;
import playn.core.*;
import playn.core.util.Clock;
import sut.game01.sprite.go;
import tripleplay.game.ScreenStack;
import tripleplay.game.Screen;
import org.jbox2d.common.Vec2;
import sut.game01.sprite.zealot;
import sut.game01.sprite.go;
import sut.game01.core.DebugDrawBox2D;

import static playn.core.PlayN.assets;
import static playn.core.PlayN.graphics;
import static playn.core.PlayN.keyboard;


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

/**
 * Created by rst706 on 2/4/14.
 */
public class GameScreen extends Screen {

    public static float M_PER_PIXEL =1 / 26.666667f;

    private static int width =24;
    private static int height = 18;
    private ScreenStack ss;



    private DebugDrawBox2D debugDraw;
    private World world;
    private boolean showDebugDraw = true;
    private Body body1;
    private zealot z ;
    public GameScreen(ScreenStack ss) {
        this.ss = ss;
    }

    @Override
    public void wasAdded() {
        super.wasAdded();
        Image bgImage = assets().getImage("images/bg.png");
        ImageLayer bgLayer = graphics().createImageLayer(bgImage);
        layer.add(bgLayer);

        Vec2 gravity = new Vec2(0.0f,10.0f);
        world = new World(gravity, true);
        world.setWarmStarting(true);
        world.setAutoClearForces(true);
        world.setContactListener(new ContactListener() {
            @Override
            public void beginContact(Contact contact) {
                if(contact.getFixtureA().getBody() == z.getbody()||contact.getFixtureB().getBody() == z.getbody()){
                    z.contact(contact);

                }

            }

            @Override
            public void endContact(Contact contact) {

            }

            @Override
            public void preSolve(Contact contact, Manifold manifold) {

            }

            @Override
            public void postSolve(Contact contact, ContactImpulse contactImpulse) {

            }
        });


        if(showDebugDraw){
            CanvasImage image = graphics().createImage((int) (width / GameScreen.M_PER_PIXEL), (int) (height / GameScreen.M_PER_PIXEL));
            layer.add(graphics().createImageLayer(image));
            debugDraw = new DebugDrawBox2D();
            debugDraw.setCanvas(image);
            debugDraw.setFlipY(false);
            debugDraw.setStrokeAlpha(150);
            debugDraw.setFillAlpha(75);
            debugDraw.setStrokeWidth(2.0f);
            debugDraw.setFlags(DebugDraw.e_shapeBit| DebugDraw.e_jointBit|DebugDraw.e_aabbBit);
            debugDraw.setCamera(0, 0, 1f / GameScreen.M_PER_PIXEL);
            world.setDebugDraw(debugDraw);

        }
        Body ground = world.createBody(new BodyDef());
        PolygonShape groundShape = new PolygonShape();
        groundShape.setAsEdge(new Vec2(2f, height-2),new Vec2(width-2f,height-2));
        ground.createFixture(groundShape, 0.0f);

        Image bg1Image = assets().getImage("images/back.png");
        ImageLayer backLayer = graphics().createImageLayer(bg1Image);


        backLayer.setSize(150,99);
        backLayer.setOrigin(30,30);
        backLayer.setTranslation(300,99);
        layer.add(backLayer);
        backLayer.addListener(new Pointer.Adapter() {
            @Override
            public void onPointerEnd(Pointer.Event event) {
                body1.applyLinearImpulse(new Vec2(-100f, 0f), body1.getPosition());


            }
        });
        createBox();
        createBox2();
        z = new zealot(world,100f,100f);

        layer.add(z.layer());

        keyboard().setListener(new Keyboard.Adapter(){
            @Override
            public void onKeyDown(Keyboard.Event event) {
                super.onKeyDown(event);
                switch (event.key()){
                    case A:
                        z.vee();
                        break;
                }

            }
        });
    }
    private void createBox(){
        BodyDef bf =new BodyDef();
        bf.type = BodyType.DYNAMIC;
        bf.position =new Vec2(0,0);

        Body body = world.createBody(bf);
        PolygonShape shape =new PolygonShape();
        shape.setAsBox(1f,1f);
        FixtureDef fd = new FixtureDef();
        fd.shape = shape;
        fd.density = 0.1f;
        fd.friction = 0.1f;
        fd.restitution = 1f;
        body.createFixture(fd);
        body.setLinearDamping(0.5f);
        body.setTransform(new Vec2(10f, 10f), 0);



    }

    private void createBox2(){
        BodyDef bf =new BodyDef();
        bf.type = BodyType.DYNAMIC;
        bf.position =new Vec2(0,0);

        body1 = world.createBody(bf);
        PolygonShape shape =new PolygonShape();
        shape.setAsBox(1f,1f);
        FixtureDef fd = new FixtureDef();
        fd.shape = shape;
        fd.density = 0.1f;
        fd.friction = 0.1f;
        fd.restitution = 0f;
        body1.createFixture(fd);
        body1.setLinearDamping(0.5f);
        body1.setTransform(new Vec2(13f, 14f), 0);
        



    }

    @Override
    public void update(int delta) {
        super.update(delta);
       world.step(0.033f,10,10);
        z.update(delta);


    }

    @Override
    public void paint(Clock clock) {
        super.paint(clock);
        if (showDebugDraw){
        debugDraw.getCanvas().clear();
            world.drawDebugData();
        }
        z.paint(clock);

    }



}
