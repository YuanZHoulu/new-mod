package tutorial.components;

import mindustry.gen.Building;
import tutorial.blocks.StatedWall;

import static tutorial.ModBlocks.A测试wall;
import static tutorial.ModBlocks.B测试wall;

public class SharingHealth extends ComponentBase<StatedWall.StatedWallBuild>{
    @Override
    public void onUpdate(StatedWall.StatedWallBuild b){
            for (Building other : b.proximity) {
                if (other.block != A测试wall && other.block!= B测试wall){
                    continue;
                }
                float thisH = b.health;
                float otherH = other.health;
                if (thisH > otherH && thisH > 50 && otherH < (other.maxHealth-50)) {
                    other.health += 50;
                    b.health -= 50;
            }
        }
    }
}
