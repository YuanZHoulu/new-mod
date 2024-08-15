package tutorial;

import mindustry.type.Category;
import mindustry.type.ItemStack;
import mindustry.world.blocks.defense.Wall;
import mindustry.world.meta.BuildVisibility;
import tutorial.blocks.StatedWall;

public class ModBlocks {
    public static Wall
            //墙
            测试wall;

    public void load() {
        //墙
        测试wall = new StatedWall("测试-wall") {{
            requirements(Category.defense, BuildVisibility.shown, new ItemStack[]{});
            health = 1000;
            size = 2;
            buildCostMultiplier = 0.1f;
            armor =10.0f;
            insulated = true;
            absorbLasers = true;
            stateNumber = 2;
        }};
    }
}
