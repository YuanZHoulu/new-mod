package tutorial;

import mindustry.type.Category;
import mindustry.type.ItemStack;
import mindustry.world.blocks.defense.Wall;
import mindustry.world.meta.BuildVisibility;

public class ModBlocks {
    public static Wall 测试wall;

    public void load() {
        测试wall = new Wall("测试wall") {{
            requirements(Category.defense, BuildVisibility.shown, new ItemStack[]{});
            health = 1000;
            size = 2;
            buildCostMultiplier = 0.1f;
            crushDamageMultiplier =100.0f;
        }};
    }
}
