package tutorial.blocks;

import arc.Core;
import arc.graphics.g2d.*;
import arc.math.Mathf;
import arc.math.geom.Geometry;
import arc.math.geom.Rect;
import arc.math.geom.Vec2;
import arc.scene.ui.layout.Table;
import arc.util.Eachable;
import arc.util.Tmp;
import mindustry.entities.units.BuildPlan;
import mindustry.gen.Building;
import mindustry.gen.Icon;
import mindustry.graphics.Drawf;
import mindustry.graphics.Layer;
import mindustry.graphics.Pal;
import mindustry.ui.Styles;
import mindustry.world.Block;
import mindustry.world.Build;
import mindustry.world.Tile;
import mindustry.world.draw.DrawBlock;
import mindustry.world.draw.DrawDefault;

import static mindustry.Vars.*;
import static mindustry.Vars.tilesize;
import static mindustry.graphics.Pal.accent;
import static tutorial.ModBlocks.*;


public class TestMultiCube extends Block {
    public TextureRegion topRegion;
    float blockx =5;
    float blocky =5;

    public DrawBlock drawer = new DrawDefault();
    public String Availableblocks;
    public int width = 9;
    public int height = 9;

    public TestMultiCube(String name) {
        super(name);

        rotateDraw = false;
        rotate = true;
        configurable = true;

    }

    public void load() {
        super.load();

        topRegion = Core.atlas.find(name + "-top");
    }

    @Override
    public void drawPlanRegion(BuildPlan plan, Eachable<BuildPlan> list){
        Draw.rect(region, plan.drawx(), plan.drawy());
        Draw.rect(topRegion, plan.drawx(), plan.drawy(), plan.rotation * 90);
        drawer.drawPlan(this, plan, list);
    }

    @Override
    public TextureRegion[] icons(){
        return new TextureRegion[]{region, topRegion};
    }


    @Override
    public void drawPlace(int x, int y, int rotation, boolean valid){
        super.drawPlace(x, y, rotation, valid);

        x *= tilesize;
        y *= tilesize;
        x += offset;
        y += offset;

        Rect rect = getRect(Tmp.r1, x, y, rotation);

        float widthlen = 0;
        float heightlen = 0;
        if (rotation % 2 == 1){
            widthlen = tilesize * height / 2f;
            heightlen = tilesize * width / 2f;
        }else {
             widthlen = tilesize * width / 2f;
             heightlen = tilesize * height / 2f;
        }

        Drawf.dashRect(valid ? accent : Pal.remove, rect);
        indexer.eachBlock(player.team(), Tmp.r1.setCentered(rect.x + widthlen, rect.y + heightlen, widthlen * 2, heightlen * 2 ), b -> true, t -> {
            Drawf.selected(t, Tmp.c1.set(valid ? accent : Pal.remove).a(Mathf.absin(4f, 1f)));
        });
    }

    public Rect getRect(Rect rect, float x, float y, int rotation){
        float widtha = 0;
        float heighta= 0;
        if (rotation % 2 == 1){
            widtha = tilesize * height / 2f;
            heighta = tilesize * width / 2f;
        }else {
            widtha = tilesize * width / 2f;
            heighta = tilesize * height / 2f;
        }
        rect.setCentered(x, y, widtha * tilesize, heighta * tilesize);
        float widthlen = tilesize * (width + size)/2f;
        float heightlen = tilesize * (height + size)/2f;

        if (rotation % 2 == 1) {
            rect.x += Geometry.d4x(rotation) * heightlen;
            rect.y += Geometry.d4y(rotation) * widthlen;
        }else {
            rect.x += Geometry.d4x(rotation) * widthlen;
            rect.y += Geometry.d4y(rotation) * heightlen;
        }

        return rect;
    }



    public class TestMultiCubeBuild extends Building {

        @Override
        public void draw(){
            Draw.rect(block.region, x, y);
            Draw.rect(topRegion, x, y, rotdeg());
            Vec2 spawn = getUnitSpawn();

            float widthlen = 0;
            float heightlen = 0;
            if (rotation % 2 == 1){
                widthlen = tilesize * height / 2f;
                heightlen = tilesize * width / 2f;
            }else {
                widthlen = tilesize * width / 2f;
                heightlen = tilesize * height / 2f;
            }

            Draw.z(Layer.buildBeam);

            Lines.stroke(2f, accent);
            Drawf.dashRectBasic(spawn.x - widthlen, spawn.y - heightlen, widthlen*2f, heightlen*2f);

            indexer.eachBlock(player.team(), Tmp.r1.setCentered(spawn.x, spawn.y, widthlen*2f, heightlen*2f), b -> true, t -> {
                Drawf.selected(t, Tmp.c1.set(accent).a(Mathf.absin(4f, 1f)));
            });
        }

        public Vec2 getUnitSpawn(){
            float widthlen = tilesize * (width + size)/2f;
            float heightlen = tilesize * (height + size)/2f;

            float unitX = 0;
            float unitY = 0;
            if (rotation % 2 == 1) {
                unitX = x + Geometry.d4x(rotation) * heightlen;
                unitY = y + Geometry.d4y(rotation) * widthlen;
            }else {
                unitX = x + Geometry.d4x(rotation) * widthlen;
                unitY = y + Geometry.d4y(rotation) * heightlen;
            }

            return Tmp.v4.set(unitX, unitY);
        }


        public void buildConfiguration(Table table){
            table.button(Icon.hammer, Styles.cleari, () -> {
                BuildingStructures (Availableblocks,false);
            }).size(40f);
            table.button(Icon.hammer, Styles.cleari, () -> {
                BuildingStructures (Availableblocks,true);
            }).size(40f);
        }



        public void BuildingStructures (String Availableblocks, boolean c){

            float widthlen = tilesize * (width + size)/2f;
            float heightlen = tilesize * (height + size)/2f;

            int x,y;
            if (rotation % 2 == 1) {
                x = (int) ((this.x + Geometry.d4x[rotation] * heightlen) / tilesize - heightlen / 2f + 0.5);
                y = (int) ((this.y + Geometry.d4y[rotation] * widthlen) / tilesize + widthlen / 2f - 0.5);
            }else {
                x = (int) ((this.x + Geometry.d4x[rotation] * widthlen) / tilesize - widthlen / 2f + 0.5);
                y = (int) ((this.y + Geometry.d4y[rotation] * heightlen) / tilesize + heightlen / 2f - 0.5);
            }

            Block[][] B测试wall结构 = new Block[][]{
                    {A测试wall,A测试wall},
                    {A测试wall,A测试wall}
            };
            Block[][] C测试wall结构 = new Block[][]{
                    {A测试wall,A测试wall,A测试wall},
                    {A测试wall,A测试wall,A测试wall},
                    {A测试wall,A测试wall,A测试wall}
            };
            Block[][] D测试wall结构A = new Block[][]{
                    {A测试wall,A测试wall,A测试wall,A测试wall},
                    {A测试wall,B测试wall,B测试wall,A测试wall},
                    {A测试wall,B测试wall,B测试wall,A测试wall},
                    {A测试wall,A测试wall,A测试wall,A测试wall}
            };
            Block[][] D测试wall结构B = new Block[][]{
                    {B测试wall,B测试wall,B测试wall,B测试wall},
                    {B测试wall,B测试wall,B测试wall,B测试wall},
                    {B测试wall,B测试wall,B测试wall,B测试wall},
                    {B测试wall,B测试wall,B测试wall,B测试wall}
            };

            Block[][][] Structurename = new Block[][][]{};
            Block[] blocks = new Block[]{};
            switch (Availableblocks){
                case "A测试多方块":
                    Structurename = new Block[][][]{C测试wall结构,B测试wall结构};
                           blocks = new Block[]    {C测试wall,   B测试wall   };
                    break;
                case "B测试多方块":
                    Structurename = new Block[][][]{C测试wall结构,B测试wall结构,D测试wall结构A,D测试wall结构B};
                           blocks = new Block[]    {C测试wall,   B测试wall    ,D测试wall    ,D测试wall    };
                    break;
            }

            for (boolean b = true; b;) {
                if (c){
                    b =false;
                }
                boolean build = false;
                int i = 0;
                for (i = 0; i < Structurename.length; i++) {
                    boolean a = FindingtheStructure(Structurename[i], x, y, x, y);
                    if (a) {
                        build = true;
                        break;
                    }
                }
                if (build) {
                    Build.beginPlace(null, blocks[i], this.team, (int) blockx, (int) blocky, 0);
                } else {
                    b = false;
                }
            }
        }

        public boolean FindingtheStructure (Block[][] Structurename,int x,int y,int X,int Y){
            int heightlen = height;
            int widthlen = width;
            if (rotation % 2 == 1){
                heightlen = width;
                widthlen = height;
            }
            for (int i = 0; i < heightlen; i++){
                for (int j = 0; j < widthlen; j++){
                    Tile other = world.tile( x , y );
                    if (Structurename[0][0] == other.block()){
                        boolean a =Structureinspection(Structurename,x,y,X,Y);
                        if (a){
                            return true;
                        }
                    }
                    x += 1;
                }
                y -= 1;
                x -= (widthlen);
            }
            return false;
        }

        public boolean Structureinspection (Block[][] Structurename,int x,int y,int X,int Y){
            int heightlen = height;
            int widthlen = width;
            if (rotation % 2 == 1){
                heightlen = width;
                widthlen = height;
            }
            int n = 0;
            int m = 0;
            for ( n = 0; Structurename.length > n; n++){
                for ( m = 0; Structurename[n].length > m; m++){
                    if (x + m >= X + widthlen || y - n <= Y - heightlen) {
                        return false;
                    }
                    Tile other = world.tile( x + m , y - n);
                    if (Structurename[n][m] != other.block()){
                        return false;
                    }
                }
            }
            blockx = x + m/2f - 0.5f;
            blocky = y - n/2f + 0.5f;
            return true;
        }
    }
}
