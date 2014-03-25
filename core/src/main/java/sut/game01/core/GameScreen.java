package sut.game01.core;

import org.jbox2d.callbacks.ContactImpulse;
import org.jbox2d.callbacks.ContactListener;
import org.jbox2d.callbacks.DebugDraw;
import org.jbox2d.collision.Manifold;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.MathUtils;
import org.jbox2d.dynamics.*;
import org.jbox2d.dynamics.contacts.Contact;
import org.jbox2d.dynamics.joints.*;
import playn.core.*;
import playn.core.SurfaceImage;
import playn.core.SurfaceLayer;
import playn.core.util.Clock;
import sut.game01.sprite.*;
import tripleplay.game.ScreenStack;
import tripleplay.game.Screen;
import org.jbox2d.common.Vec2;
import sut.game01.sprite.go;
import sut.game01.core.DebugDrawBox2D;


import playn.core.util.Callback;
import react.UnitSlot;
import sut.game01.sprite.zealot;
import tripleplay.game.ScreenStack;
import tripleplay.game.Screen;
import tripleplay.game.UIScreen;
import tripleplay.ui.*;
import tripleplay.ui.layout.AxisLayout;
import playn.core.Pointer;
import org.jbox2d.collision.*;
import org.jbox2d.collision.shapes.*;
import org.jbox2d.common.MathUtils.*;
import sut.game01.sprite.dragon1;
import sut.game01.sprite.firemon1;
import sut.game01.core.MapScreen;


import java.util.ArrayList;

import static playn.core.PlayN.*;

/**
 * Created by rst706 on 2/4/14.
 */
public class GameScreen extends UIScreen {

    public static float M_PER_PIXEL =1 / 26.666667f;

    private static int width =24;
    private static int height = 18;
    private ScreenStack ss;
    private Body ground,ground1 ;
    public enum Statep{PLAY,PE};
    public Statep statep = Statep.PLAY;


    private DebugDrawBox2D debugDraw;
    private World world;
    private boolean showDebugDraw = false,opdr=false;
    private Body body1,body,body4;
    private Body body2;
    private zealot z ;
    private car c;
    private diver d;
    public diver2 d2;
    public firemon1 fm1;
    public MapScreen map;
    private fire f;
    private int a=0,time=0,time1=0,ST=0,time2=0,bgmo,b=0,cc=0;
    public float xm,ym;
    private RevoluteJoint joint3,joint9,joint10,joint11,joint12,joint13,joint14,joint15;
    private LineJoint joint;
    private RevoluteJointDef jd3 = new RevoluteJointDef();
    private RevoluteJointDef jd4 = new RevoluteJointDef();
    private RevoluteJointDef jd5 = new RevoluteJointDef();
    private RevoluteJointDef jd6 = new RevoluteJointDef();
    private LineJointDef jd2 = new LineJointDef();
    public static boolean firebox = true;
    public static boolean firexy = true,kk=true,check=false;
    public static float fireX,fireY,angle=0;
    public static Root root,root1,root2;
    public boolean closepe = true;
    public ImageLayer bg1ImageLayer;
    private Sound s1 = assets().getSound("images/m1");
    private Sound s2 = assets().getSound("images/m2");
    private Sound s3 = assets().getSound("images/m3");
    private Sound s4 = assets().getSound("images/m4");



    ArrayList<fire> fireArrayList = new ArrayList<fire>();
    ArrayList<firemon1> firemon1ArrayList = new ArrayList<firemon1>();
    ArrayList<dragon1> dra1ArrayList = new ArrayList<dragon1>();

    public GameScreen(ScreenStack ss) {
        this.ss = ss;
    }
    public static final Font TITLE_FONT = PlayN.graphics().createFont(
            "Helvetica",
            Font.Style.BOLD,
            24
    );

    @Override
    public void wasAdded() {
        super.wasAdded();
        Image bgImage = assets().getImage("images/bg1.png");
        bg1ImageLayer = graphics().createImageLayer(bgImage);
        layer.add(bg1ImageLayer);

        Vec2 gravity = new Vec2(0.0f,10.0f);
        world = new World(gravity, true);
        world.setWarmStarting(true);
        world.setAutoClearForces(true);
        world.setContactListener(new ContactListener() {
            @Override
            public void beginContact(Contact contact) {
                for (dragon1 dra1 : dra1ArrayList){
                for (fire f : fireArrayList)
                    if (contact.getFixtureB().getBody() == f.getbody() && contact.getFixtureA().getBody() == dra1.getbody()) {
                        dra1.contact(contact);



                        s1.play();
                        dra1.HP = dra1.HP - 50;
                        f.closebody();


                    }
                if(contact.getFixtureB().getBody()==dra1.getbody()&&contact.getFixtureA().getBody()==ground){
                    dra1.closebody();
                    ST+=1;
                    dra1.HP=100;
                    dra1.reset();
                    dra1.setbb();
                    s2.play();
                    MyGame.money+=100;
                    root1.removeAll();
                    root1 = iface.createRoot(
                            AxisLayout.vertical().gap(15),
                            SimpleStyles.newSheet(), layer);
                    root1.setSize(1075, 50);

                    root1.add(new Label(String.valueOf(MyGame.money)).addStyles(Style.FONT.is(ShopScreen.TITLE_FONT)));

                }}

                if (MyGame.statediver== MyGame.Statediver.d1){
                if (contact.getFixtureA().getBody()==body&&contact.getFixtureB().getBody()==d.getbody()){
                if (firebox==false){
                    if (firexy==true){
                    f = new fire(world,90f,310f);
                    fireArrayList.add(f);
                        s3.play();
                    layer.add(f.layer());
                    firexy=false;
                    }
                }
                }
//



                }
                for (firemon1 fi1 : firemon1ArrayList){
                    if (contact.getFixtureB().getBody()==fi1.getbody()&&contact.getFixtureA().getBody()==ground1){
                        car.HP = car.HP - 100;
                        fi1.closebody();

                    }
                    if (contact.getFixtureB().getBody()==fi1.getbody()){
                        fi1.closebody();

                    }
                    if (contact.getFixtureA().getBody()==fi1.getbody()){
                        fi1.closebody();

                    }
                    if (MyGame.statediver== MyGame.Statediver.d2){
                        if (contact.getFixtureA().getBody()==d2.getbody()&&contact.getFixtureB().getBody()==fi1.getbody()){
                            d2.vi2();
                        }
                    }


                }
                if (MyGame.statediver== MyGame.Statediver.d1){
                if (contact.getFixtureA().getBody()==c.getbody()&&contact.getFixtureB().getBody()==d.getbody()){

                        firebox=true;
                        firexy=true;


                }
                if (contact.getFixtureA().getBody()==d.getbody()&&contact.getFixtureB().getBody()==c.getbody()){

                    firebox=true;
                    firexy=true;


                }
                }



            }

            @Override
            public void endContact(Contact contact) {
//                for (fire f : fireArrayList){
//
//
//                if (contact.getFixtureA().getBody()==f.getbody()||contact.getFixtureB().getBody()==f.getbody()){
////                    f.closebody();
//
//                }
//                }
//                System.out.println(contact.getFixtureA().getBody());
//                System.out.println(contact.getFixtureB().getBody());


            }

            @Override
            public void preSolve(Contact contact, Manifold manifold) {

            }

            @Override
            public void postSolve(Contact contact, ContactImpulse contactImpulse) {

            }
        });
        c.HP=1000;

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
        ground = world.createBody(new BodyDef());
        ground1 = world.createBody(new BodyDef());
        PolygonShape groundShape = new PolygonShape();
        groundShape.setAsEdge(new Vec2(0.3f, height-2),new Vec2(width+10,height-2));
        PolygonShape groundShape1 = new PolygonShape();
        PolygonShape groundShape2 = new PolygonShape();
        PolygonShape groundShape3 = new PolygonShape();
        PolygonShape groundShape4 = new PolygonShape();
        PolygonShape groundShape5 = new PolygonShape();
        groundShape1.setAsEdge(new Vec2(0.3f, height-2),new Vec2(width-23.68f,height-5));
        groundShape2.setAsEdge(new Vec2(4.3f, height-4.1f),new Vec2(width-20.75f,height-5.9f));
        groundShape3.setAsEdge(new Vec2(4.3f, height-2f),new Vec2(width-19.75f,height-3.9f));
        groundShape4.setAsEdge(new Vec2(4.3f, height-2f),new Vec2(width-19.75f,height-3.9f));
        groundShape5.setAsEdge(new Vec2(4.3f, height-4.1f),new Vec2(width-20.25f,height-4.8f));
        ground.createFixture(groundShape1, 0.0f);
        if (MyGame.statediver== MyGame.Statediver.d1){
        ground1.createFixture(groundShape2, 0.0f);
        ground1.createFixture(groundShape3, 0.0f);
        }
        if (MyGame.statediver== MyGame.Statediver.d2){ground1.createFixture(groundShape4, 0.0f);
                                                        ground1.createFixture(groundShape5, 0.0f);
        }
        ground.createFixture(groundShape, 0.0f);

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
                ss.remove(GameScreen.this);
            }
        });
        Image bbImage = assets().getImage("images/money.png");
        ImageLayer bbLayer =graphics().createImageLayer(bbImage);
        bbLayer.setSize(30,30);
        bbLayer.setOrigin(15,15);
        bbLayer.setTranslation(440,30);
        layer.add(bbLayer);
        root = iface.createRoot(
                AxisLayout.vertical().gap(15),
                SimpleStyles.newSheet(), layer);
        root.setSize(660, 895);

        root.add(new Label(String.valueOf(MyGame.ppp)).addStyles(Style.FONT.is(ShopScreen.TITLE_FONT)));

        if (MyGame.statediver== MyGame.Statediver.d1){
            createBox();

       createBox2();
        createBox3();
        createBox4();
        }

//        z = new zealot(world,100f,100f);
        c = new car(world,80f,400f);
        if (MyGame.statediver== MyGame.Statediver.d1){
        d = new diver(world,80f,355f);
        }


        layer.add(c.layer());
        if (MyGame.statediver== MyGame.Statediver.d2){
            d2 = new diver2(world,80f,355f);
            layer.add(d2.layer());
            check=true;

        }
        if (MyGame.statediver== MyGame.Statediver.d1){
        layer.add(d.layer());
        }


//        layer.add(z.layer());
        root2 = iface.createRoot(
                AxisLayout.vertical().gap(15),
                SimpleStyles.newSheet(), layer);
        root2.setSize(600, 50);

        root2.add(new Label(String.valueOf(c.HP)).addStyles(Style.FONT.is(ShopScreen.TITLE_FONT)));

        root1 = iface.createRoot(
                AxisLayout.vertical().gap(15),
                SimpleStyles.newSheet(), layer);
        root1.setSize(1075, 50);

        root1.add(new Label(String.valueOf(MyGame.money)).addStyles(Style.FONT.is(ShopScreen.TITLE_FONT)));
        Image pImage = assets().getImage("images/62956.jpg");
        ImageLayer pLayer =graphics().createImageLayer(pImage);
        pLayer.setSize(40,40);
        pLayer.setOrigin(20,20);
        pLayer.setTranslation(280,460);
        layer.add(pLayer);

        pLayer.addListener(new Pointer.Adapter(){
            @Override
            public void onPointerStart(Pointer.Event event) {
                super.onPointerStart(event);
                if(MyGame.ppp>0){
                    car.HP+=100;
                    MyGame.ppp-=1;
                }
            }
        });






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



        pointer().setListener(new Pointer.Adapter(){
            @Override
            public void onPointerEnd(Pointer.Event event) {



            }

            @Override
            public void onPointerStart(Pointer.Event event) {
                super.onPointerStart(event);
                if (MyGame.statediver== MyGame.Statediver.d1){

                if (statep == Statep.PLAY){
                if (firebox==true){
                Vec2 delta = new Vec2(event.x()-90,event.y()-310);
                angle = MathUtils.atan2(delta.y, delta.x);


                body1.applyLinearImpulse(new Vec2(0f, -1.5f), body1.getPosition());
                firebox = false;}
            }
                }
                if (MyGame.statediver== MyGame.Statediver.d2){
                    if (check){
                    Vec2 delta = new Vec2(event.x()-90,event.y()-310);
                    angle = MathUtils.atan2(delta.y, delta.x);

                    d2.rdiver(angle);
                    f = new fire(world,120-(MathUtils.cos(GameScreen.angle)/ GameScreen.M_PER_PIXEL),(355-(MathUtils.cos(GameScreen.angle)/ GameScreen.M_PER_PIXEL)));
                    fireArrayList.add(f);
                        s3.play();
                    layer.add(f.layer());
                    }

                }


            }

        });

    }
    private void createBox(){
        BodyDef bf =new BodyDef();
        bf.type = BodyType.DYNAMIC;
        bf.position =new Vec2(0,0);

        body2 = world.createBody(bf);
        PolygonShape shape =new PolygonShape();
        shape.setAsBox(0.1f,1.3f);
        FixtureDef fd = new FixtureDef();
        fd.shape = shape;
        fd.density = 0.1f;
        fd.friction = 0.1f;
        fd.restitution = 1f;
        body2.createFixture(fd);
        body2.setLinearDamping(0.5f);
        body2.setTransform(new Vec2(60f* GameScreen.M_PER_PIXEL ,330f * GameScreen.M_PER_PIXEL ), 0);



    }
    private void createBox4(){
        BodyDef bf =new BodyDef();
        bf.type = BodyType.DYNAMIC;
        bf.position =new Vec2(0,0);

        body4 = world.createBody(bf);
        PolygonShape shape =new PolygonShape();
        shape.setAsBox(0.1f,0.1f);
        FixtureDef fd = new FixtureDef();
        fd.shape = shape;
        fd.density = 0.1f;
        fd.friction = 0.1f;
        fd.restitution = 1f;
        body4.createFixture(fd);
        body4.setLinearDamping(0.5f);
        body4.setTransform(new Vec2(80f* GameScreen.M_PER_PIXEL ,395f * GameScreen.M_PER_PIXEL ), 0);}

    private void createBox3(){
        BodyDef bf =new BodyDef();
        bf.type = BodyType.DYNAMIC;
        bf.position =new Vec2(0,0);

        body = world.createBody(bf);
        PolygonShape shape =new PolygonShape();
        shape.setAsBox(0.1f,1f);
        FixtureDef fd = new FixtureDef();
        fd.shape = shape;
        fd.density = 0.1f;
        fd.friction = 0.1f;
        fd.restitution = 1f;
        body.createFixture(fd);
        body.setLinearDamping(0.5f);
        body.setTransform(new Vec2(90 * GameScreen.M_PER_PIXEL, 350f * GameScreen.M_PER_PIXEL), 0);



    }

    private void createBox2(){
        BodyDef bf =new BodyDef();
        bf.type = BodyType.DYNAMIC;
        bf.position =new Vec2(0,0);

        body1 = world.createBody(bf);
        PolygonShape shape =new PolygonShape();
        shape.setAsBox(0.1f,0.1f);
        FixtureDef fd = new FixtureDef();
        fd.shape = shape;
        fd.density = 0.1f;
        fd.friction = 0.1f;
        fd.restitution = 0f;
        body1.createFixture(fd);
        body1.setLinearDamping(0.5f);
        body1.setTransform(new Vec2(80f* GameScreen.M_PER_PIXEL ,355f * GameScreen.M_PER_PIXEL), 0);





    }

    @Override
    public void update(int delta) {
        if(c.getHasLoaded()==true){
            if (a==0)  {if (MyGame.statediver== MyGame.Statediver.d1){
                jd4.initialize(body2, body, body2.getWorldCenter());
                jd4.enableLimit = true;
                jd4.enableMotor = true;
                jd4.maxMotorTorque = 1000.0f;
//                jd2.maxMotorForce = 20.f;
                jd4.motorSpeed = 1.0f;
                jd4.localAnchorA = new Vec2(0f,0f);
                jd4.localAnchorB = new Vec2(-0.3f,3f);
                joint11 = (RevoluteJoint)world.createJoint(jd4);

                jd5.initialize(c.getbody(), body4, c.body.getWorldCenter());
                jd5.enableLimit = true;
                jd5.enableMotor = true;
                jd5.maxMotorTorque = 1000.0f;
//                jd2.maxMotorForce = 20.f;
                jd5.motorSpeed = 1.0f;
                jd5.localAnchorA = new Vec2(-0.2f,0f);
                jd5.localAnchorB = new Vec2(0f,0.3f);
                joint10 = (RevoluteJoint)world.createJoint(jd5);

                jd5.initialize(body4, body2, body4.getWorldCenter());
                jd5.enableLimit = true;
                jd5.enableMotor = true;
                jd5.maxMotorTorque = 1000.0f;
//                jd2.maxMotorForce = 20.f;
                jd5.motorSpeed = 1.0f;
                jd5.localAnchorA = new Vec2(-0.5f,0f);
                jd5.localAnchorB = new Vec2(-0.5f,0f);
                joint9 = (RevoluteJoint) world.createJoint(jd5);

                jd3.initialize(c.getbody(), body2, c.body.getWorldCenter());
                jd3.enableLimit = true;
                jd3.enableMotor = true;
                jd3.maxMotorTorque = 1000.0f;
//                jd2.maxMotorForce = 20.f;
                jd3.motorSpeed = 1.0f;
                jd3.localAnchorA = new Vec2(-0.2f,0);
                jd3.localAnchorB = new Vec2(0f,0.7f);
                 joint3 = (RevoluteJoint) world.createJoint(jd3);





                LineJointDef joint_def = new LineJointDef();
                joint_def.bodyA = d.getbody();
                joint_def.bodyB = body2;
                joint_def.enableLimit=true;
                joint_def.enableMotor=true;
                joint_def.maxMotorForce = 00.0f;
                joint_def.motorSpeed =0.0f;
                //connect the centers - center in local coordinate - relative to body is 0,0
                joint_def.localAnchorA.addLocal(new Vec2(0f, 0));
                joint_def.localAnchorB.addLocal(new Vec2(0f, -1.05f));


                //add the joint to the world
                world.createJoint(joint_def);

                RevoluteJointDef joint_def1 = new RevoluteJointDef();
                joint_def1.bodyA = body1;
                joint_def1.bodyB = d.getbody();
                joint_def1.enableLimit=true;
                joint_def1.enableMotor=true;
                joint_def1.maxMotorTorque = 1000.0f;
                joint_def1.motorSpeed =1.0f;
                //connect the centers - center in local coordinate - relative to body is 0,0
                joint_def1.localAnchorA = new Vec2(0, 0);
                joint_def1.localAnchorB = new Vec2(-1.8f, 0f);

                //add the joint to the world
                joint13 = (RevoluteJoint)world.createJoint(joint_def1);



                a++;}

            }


                if (MyGame.statediver== MyGame.Statediver.d2){
                    if(c.getHasLoaded()==true&&d2.getHasLoaded()==true){
                    if (b==0){
                    RevoluteJointDef joint_def10 = new RevoluteJointDef();
                    joint_def10.bodyA = c.getbody();
                    joint_def10.bodyB = d2.getbody();
                    joint_def10.enableLimit=true;
                    joint_def10.enableMotor=true;
                    joint_def10.maxMotorTorque = 1000.0f;
                    joint_def10.motorSpeed =1.0f;
                    //connect the centers - center in local coordinate - relative to body is 0,0
                    joint_def10.localAnchorA = new Vec2(0, -1.8f);
                    joint_def10.localAnchorB = new Vec2(0f, 0f);

                    //add the joint to the world
                    joint14 = (RevoluteJoint)world.createJoint(joint_def10);


                    b++;

                    }

                }

            }
            super.update(delta);



       world.step(0.033f,10,10);
//        z.update(delta);
        c.update(delta);
            if (MyGame.statediver== MyGame.Statediver.d1){
        d.update(delta);
            }
            if (MyGame.statediver== MyGame.Statediver.d2){
                d2.update(delta);
            }
            for (dragon1 dra1 : dra1ArrayList){
            dra1.update(delta);
            }
        for (fire f : fireArrayList){
                f.update(delta);
            }
            time+=delta;
            time1+=delta;
            time2+=delta;
            cc+=delta;

            bgmo-=delta/32.5;
            if (time1>=10000){
                if(ST<2){
                    if (statep == Statep.PLAY){

                dragon1 dra1 = new dragon1(world,750,200);
                dra1.HP=100;
                dra1.reset();
                dra1ArrayList.add(dra1);
                layer.add(dra1.layer());
                opdr=true;
                time1=0;
                    }
                }


            }





            if (time>=1500){
                for (dragon1 dra1 : dra1ArrayList){


                if (dra1.gethasload()==true){
                    if(dra1.getbb()==true){
                        if (statep==Statep.PLAY){

                xm = dra1.getbody().getPosition().x;
                ym = dra1.getbody().getPosition().y;
                fm1 = new firemon1(world,(xm/GameScreen.M_PER_PIXEL)-75,(ym/GameScreen.M_PER_PIXEL)+75);
                firemon1ArrayList.add(fm1);
                layer.add(fm1.layer());
                time=0;}
                    }
                }




            }
            }
            }
            for (firemon1 fi1 : firemon1ArrayList){

                fi1.update(delta);
            }
            if (c.HP<=0){statep = Statep.PE;}
            if (cc>=45000){statep = Statep.PE;}
            if (statep == Statep.PE){
                if (closepe==true){
                    Image se2Image = assets().getImage("images/5555.png");
                    ImageLayer se2Layer =graphics().createImageLayer(se2Image);
                    se2Layer.setSize(322,222);
                    se2Layer.setOrigin(161,111);
                    se2Layer.setTranslation(320,240);
                    layer.add(se2Layer);
                    Image reImage = assets().getImage("images/reload.png");
                    ImageLayer reLayer =graphics().createImageLayer(reImage);
                    reLayer.setSize(44,44);
                    reLayer.setOrigin(22,22);
                    reLayer.setTranslation(320,320);
                    layer.add(reLayer);
                    s4.play();
                    reLayer.addListener(new Pointer.Adapter(){
                        @Override
                        public void onPointerStart(Pointer.Event event) {
                            super.onPointerStart(event);
                            ss.remove(GameScreen.this);
                        }
                    });
                    closepe=false;

                }

            }

        if(ST>=2){

            MyGame.sta+=1;
                MyGame.v12=true;
                MyGame.v13=true;
                MyGame.v14=true;
            ss.remove(GameScreen.this);
                kk=false;


        }
        root.removeAll();
        root = iface.createRoot(
                AxisLayout.vertical().gap(15),
                SimpleStyles.newSheet(), layer);
        root.setSize(660, 895);

        root.add(new Label(String.valueOf(MyGame.ppp)).addStyles(Style.FONT.is(ShopScreen.TITLE_FONT)));
        root2.removeAll();
        root2 = iface.createRoot(
                AxisLayout.vertical().gap(15),
                SimpleStyles.newSheet(), layer);
        root2.setSize(600, 50);

        root2.add(new Label("HP : "+String.valueOf(c.HP)).addStyles(Style.FONT.is(ShopScreen.TITLE_FONT)));



        }







    @Override
    public void paint(Clock clock) {
        super.paint(clock);
        if (showDebugDraw){
        debugDraw.getCanvas().clear();
            world.drawDebugData();
        }
//        z.paint(clock);
        c.paint(clock);
        if (MyGame.statediver== MyGame.Statediver.d1){
        d.paint(clock);}
        if (MyGame.statediver== MyGame.Statediver.d2){
            d2.paint(clock);
        }
        for (dragon1 dra1 : dra1ArrayList){
        dra1.paint(clock);
        }
        for (fire f : fireArrayList){
            f.paint(clock);
        }
        for (firemon1 fi1 : firemon1ArrayList){
            fi1.paint(clock);
        }
        bg1ImageLayer.setTranslation(bgmo,0);



    }
    public float getangle(){

        return angle;
    }



}
