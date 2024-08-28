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

    public int range = 14;

    public DrawBlock drawer = new DrawDefault();
    public String Availableblocks;

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

        Drawf.dashRect(valid ? accent : Pal.remove, rect);
        indexer.eachBlock(player.team(), Tmp.r1.setCentered(rect.x + range/2f * tilesize, rect.y + range/2f  * tilesize, range * tilesize), b -> true, t -> {
            Drawf.selected(t, Tmp.c1.set(valid ? accent : Pal.remove).a(Mathf.absin(4f, 1f)));
        });
    }

    public Rect getRect(Rect rect, float x, float y, int rotation){
        rect.setCentered(x, y, range * tilesize);
        float len = tilesize * (range + size)/2f;

        rect.x += Geometry.d4x(rotation) * len;
        rect.y += Geometry.d4y(rotation) * len;

        return rect;
    }


    public class TestMultiCubeBuild extends Building {

        @Override
        public void draw(){
            Draw.rect(block.region, x, y);
            Draw.rect(topRegion, x, y, rotdeg());
            Vec2 spawn = getUnitSpawn();
            float fulls = range* tilesize/2f;

            Draw.z(Layer.buildBeam);

            Lines.stroke(2f, accent);
            Drawf.dashRectBasic(spawn.x - fulls, spawn.y - fulls, fulls*2f, fulls*2f);

            indexer.eachBlock(player.team(), Tmp.r1.setCentered(spawn.x, spawn.y, range * tilesize), b -> true, t -> {
                Drawf.selected(t, Tmp.c1.set(accent).a(Mathf.absin(4f, 1f)));
            });
        }

        public Vec2 getUnitSpawn(){
            float len = tilesize * (range + size)/2f;
            float unitX = x + Geometry.d4x(rotation) * len, unitY = y + Geometry.d4y(rotation) * len;
            return Tmp.v4.set(unitX, unitY);
        }


        public void buildConfiguration(Table table){
            table.button(Icon.hammer, Styles.cleari, () -> {
                BuildingStructures (Availableblocks);
            }).size(40f);
        }



        public void BuildingStructures (String Availableblocks){

            float len = tilesize * (range + size)/2f;

            int x,y;
            x = (int) (this.x + (Geometry.d4x(rotation) * len) );//+ range/2f * tilesize);
            y = (int) (this.y + (Geometry.d4y(rotation) * len) );//+ range/2f * tilesize);

            Block[][] B测试wall结构 = new Block[][]{
                    {A测试wall,A测试wall},
                    {A测试wall,A测试wall}
            };
            Block[][] C测试wall结构 = new Block[][]{
                    {A测试wall,A测试wall,A测试wall},
                    {A测试wall,A测试wall,A测试wall},
                    {A测试wall,A测试wall,A测试wall}
            };

            Block[][][] Structurename = new Block[][][]{};
            Block[] blocks = new Block[]{};
            switch (Availableblocks){
                case "A测试多方块":
                    Structurename = new Block[][][]{B测试wall结构};
                           blocks = new Block[]    {B测试wall   };
                    break;
                case "B测试多方块":
                    Structurename = new Block[][][]{C测试wall结构,B测试wall结构};
                           blocks = new Block[]    {C测试wall,   B测试wall    };
                    break;
            }

            boolean build = false;
            int i = 0;
            for (i = 0; i < Structurename.length; i++){
                //boolean a =FindingtheStructure(Structurename[i],x,y,x,y);
                boolean a =true;
                if (a){
                    build = true;
                    break;
                }
            }
            if (build){
                Build.beginPlace(null, blocks[i], this.team, (int) x, (int) y, 0);
            }
        }

        public boolean FindingtheStructure (Block[][] Structurename,int x,int y,int X,int Y){
            for (int i = 0; i < range; i++){
                for (int j = 0; j < range; j++){
                    Building other = world.build( x , y );
                    if (Structurename[0][0] == other.block()){
                        boolean a =Structureinspection(Structurename,x,y,X,Y);
                        if (a){
                            return true;
                        }
                    }
                    x += 1;
                }
                y -= 1;
                x -= (range);
            }
            return false;
        }

        public boolean Structureinspection (Block[][] Structurename,int x,int y,int X,int Y){
            int n = 0;
            int m = 0;
            for ( n = 0; Structurename.length > n; n++){
                for ( m = 0; Structurename[n].length > m; m++){
                    if (x + m >= X + range || y - n <= Y - range) {
                        return false;
                    }
                    Building other = world.build( x + m , y - n);
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
