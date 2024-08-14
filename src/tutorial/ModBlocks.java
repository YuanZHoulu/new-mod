package tutorial;

import mindustry.type.Category;
import mindustry.type.ItemStack;
import mindustry.world.blocks.defense.Wall;
import mindustry.world.meta.BuildVisibility;

public class ModBlocks {
    public static Wall
            //墙
            测试wall;

    public void load() {
        //墙
        测试wall = new Wall("测试wall") {{
            requirements(Category.defense, BuildVisibility.shown, new ItemStack[]{});
            health = 10;
            size = 2;
            buildCostMultiplier = 0.1f;
            armor =1000.0f;
            insulated = true;
            absorbLasers = true;
        }};
    }
}
