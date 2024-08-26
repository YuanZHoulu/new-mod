package tutorial.blocks;

import arc.Core;
import arc.Input;
import arc.graphics.Color;
import arc.graphics.g2d.*;
import arc.math.Mathf;
import arc.math.geom.Geometry;
import arc.math.geom.Rect;
import arc.math.geom.Vec2;
import arc.scene.ui.TextArea;
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
import mindustry.ui.dialogs.BaseDialog;
import mindustry.world.Block;
import mindustry.world.draw.DrawBlock;
import mindustry.world.draw.DrawDefault;

import static mindustry.Vars.*;
import static mindustry.Vars.tilesize;
import static mindustry.graphics.Pal.accent;


public class TestMultiCube extends Block {
    public TextureRegion topRegion;

    public int range = 14;
    public int maxNewlines = 24;

    public DrawBlock drawer = new DrawDefault();

    public TestMultiCube(String name) {
        super(name);

        rotateDraw = false;
        rotate = true;
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
        public StringBuilder message = new StringBuilder();

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

        @Override
        public void buildConfiguration(Table table) {
            table.button(Icon.pencil, Styles.cleari, () -> {
                if (mobile) {
                    Core.input.getTextInput(new Input.TextInput() {{
                        text = message.toString();
                        multiline = true;
                        maxLength = maxTextLength;
                        accepted = str -> {
                            if (!str.equals(text)) configure(str);
                        };
                    }});
                } else {
                    BaseDialog dialog = new BaseDialog("@editmessage");
                    dialog.setFillParent(false);
                    TextArea a = dialog.cont.add(new TextArea(message.toString().replace("\r", "\n"))).size(380f, 160f).get();
                    a.setFilter((textField, c) -> {
                        if (c == '\n') {
                            int count = 0;
                            for (int i = 0; i < textField.getText().length(); i++) {
                                if (textField.getText().charAt(i) == '\n') {
                                    count++;
                                }
                            }
                            return count < maxNewlines;
                        }
                        return true;
                    });
                    a.setMaxLength(maxTextLength);
                    dialog.cont.row();
                    dialog.cont.label(() -> a.getText().length() + " / " + maxTextLength).color(Color.lightGray);
                    dialog.buttons.button("@ok", () -> {
                        if (!a.getText().equals(message.toString())) configure(a.getText());
                        dialog.hide();
                    }).size(130f, 60f);
                    dialog.update(() -> {
                        if (tile.build != this) {
                            dialog.hide();
                        }
                    });
                    dialog.closeOnBack();
                    dialog.show();
                }
                deselect();
            }).size(40f);
        }
    }
}
