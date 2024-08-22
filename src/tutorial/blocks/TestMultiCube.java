package tutorial.blocks;

import arc.graphics.Color;
import arc.math.Mathf;
import arc.util.Tmp;
import mindustry.graphics.Drawf;
import mindustry.graphics.Pal;
import mindustry.world.Block;
import static mindustry.Vars.*;
import static mindustry.Vars.tilesize;

public class TestMultiCube extends Block {
    public Color baseColor = Pal.accent;
    public int range = 14;

    public TestMultiCube(String name) {
        super(name);

        rotateDraw = false;
        rotate = true;
    }

    @Override
    public void drawPlace(int x, int y, int rotation, boolean valid){
        super.drawPlace(x, y, rotation, valid);

        x *= tilesize;
        y *= tilesize;
        x += range * tilesize;
        y += range * tilesize;

        Drawf.dashSquare(baseColor, x, y, range * tilesize);
        indexer.eachBlock(player.team(), Tmp.r1.setCentered(x, y, range * tilesize), b -> true, t -> {
            Drawf.selected(t, Tmp.c1.set(baseColor).a(Mathf.absin(4f, 1f)));
        });
    }
}
