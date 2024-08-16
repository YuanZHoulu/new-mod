package tutorial.components;

import mindustry.gen.Building;
import tutorial.blocks.StatedWall;

public class SharingHealth extends ComponentBase<StatedWall.StatedWallBuild>{
    @Override
    public void onUpdate(StatedWall.StatedWallBuild b){
            for (Building other : b.proximity) {
                if (other.block != b.block){
                    continue;
                }
                float thisH = b.health;
                float otherH = other.health;
                if (thisH > otherH && thisH > 10 && otherH < other.maxHealth) {
                    other.health += 10;
                    b.health -= 10;
            }
        }
    }
}
