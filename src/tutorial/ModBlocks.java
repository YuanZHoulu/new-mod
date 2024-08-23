package tutorial;

import mindustry.type.Category;
import mindustry.type.ItemStack;
import mindustry.world.Block;
import mindustry.world.meta.BuildVisibility;
import tutorial.blocks.StatedWall;
import tutorial.blocks.TestMultiCube;
import tutorial.components.SharingHealth;

import static mindustry.content.Blocks.*;

public class ModBlocks {
    public static StatedWall
            //墙
            A测试wall,B测试wall;

    public static TestMultiCube
            //多方块
            测试多方块;

    public static SharingHealth sharingHealth = new SharingHealth();

    public void load() {

        //墙
        A测试wall = new StatedWall("A测试-wall") {{
            requirements(Category.defense, BuildVisibility.shown, new ItemStack[]{
            });
            health = 1000;
            insulated = true;
            absorbLasers = true;
            armor=10;
            size = 1;
            buildCostMultiplier = 0.1f;
            stateNumber = 2;
            update = true;
            components.add(sharingHealth);
            Availableblocks = new Block[]{A测试wall,B测试wall,copperWall,copperWallLarge};
        }};
        B测试wall = new StatedWall("B测试-wall") {{
            requirements(Category.defense, BuildVisibility.shown, new ItemStack[]{
            });
            health = 4000;
            insulated = true;
            absorbLasers = true;
            armor=10;
            size = 2;
            buildCostMultiplier = 0.1f;
            stateNumber = 2;
            update = true;
            components.add(sharingHealth);
            Availableblocks = new Block[]{A测试wall,B测试wall,copperWall,copperWallLarge};
        }};

        //多方块结构
        测试多方块 = new TestMultiCube("测试多方块") {{
            requirements(Category.defense, BuildVisibility.shown, new ItemStack[]{
            });
            health = 4000;
            insulated = true;
            absorbLasers = true;
            armor = 10;
            size = 1;
            buildCostMultiplier = 0.1f;
            update = true;
            range = 5;
        }};
    }
}
