package tutorial;

import mindustry.type.Category;
import mindustry.type.ItemStack;
import mindustry.world.meta.BuildVisibility;
import tutorial.blocks.StatedWall;
import tutorial.components.SharingHealth;

public class ModBlocks {
    public static StatedWall
            //墙
            测试wall;

    public static SharingHealth sharingHealth = new SharingHealth();

    public void load() {
        //墙
        测试wall = new StatedWall("测试-wall") {{
            requirements(Category.defense, BuildVisibility.shown, new ItemStack[]{
            });
            health = 2000;
            insulated = true;
            absorbLasers = true;
            armor=10;
            size = 2;
            buildCostMultiplier = 0.1f;
            stateNumber = 2;
            update = true;
            components.add(sharingHealth);
        }};
    }
}
