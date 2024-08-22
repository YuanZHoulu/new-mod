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
                float blostHealthPct = b.health / b.maxHealth;
                float otherH = other.health;
                float otherlostHealthPct = other.health / other.maxHealth;
                if (blostHealthPct > otherlostHealthPct && thisH > 100 && otherH < (other.maxHealth-100)) {
                    other.health += 100;
                    b.health -= 100;
            }
        }
    }
}
