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
            A测试wall,B测试wall,C测试wall,D测试wall;

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
            armor=50;
            size = 1;
            buildCostMultiplier = 0.1f;
            stateNumber = 2;
            update = true;
            components.add(sharingHealth);
            Availableblocks = ("A测试wall");
            Deliveryrate = 2f;
        }};
        B测试wall = new StatedWall("B测试-wall") {{
            requirements(Category.defense, BuildVisibility.shown, new ItemStack[]{
            });
            health = 4000;
            insulated = true;
            absorbLasers = true;
            armor=50;
            size = 2;
            buildCostMultiplier = 0.1f;
            stateNumber = 2;
            update = true;
            components.add(sharingHealth);
            Availableblocks = ("B测试wall");
            Deliveryrate = 1f;
        }};
        C测试wall = new StatedWall("C测试-wall") {{
            requirements(Category.defense, BuildVisibility.shown, new ItemStack[]{
            });
            health = 9000;
            insulated = true;
            absorbLasers = true;
            armor=50;
            size = 3;
            buildCostMultiplier = 0.1f;
            stateNumber = 2;
            update = true;
            components.add(sharingHealth);
            Availableblocks = ("C测试wall");
            Deliveryrate = 0.75f;
        }};
        D测试wall = new StatedWall("D测试-wall") {{
            requirements(Category.defense, BuildVisibility.shown, new ItemStack[]{
            });
            health = 16000;
            insulated = true;
            absorbLasers = true;
            armor=50;
            size = 4;
            buildCostMultiplier = 0.1f;
            stateNumber = 2;
            update = true;
            components.add(sharingHealth);
            Availableblocks = ("D测试wall");
            Deliveryrate = 0.5f;
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
            range = 11;
        }};
    }
}
