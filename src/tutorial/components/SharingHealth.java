package tutorial.components;

import mindustry.gen.Building;
import tutorial.blocks.StatedWall;

public class SharingHealth extends ComponentBase<StatedWall.StatedWallBuild> {

    @Override
    public void onUpdate(StatedWall.StatedWallBuild b, StatedWall[] availableblocks) {
        for (Building other : b.proximity) {
            for (int i = 0; i < availableblocks.length; i++) {
                if (other.block == availableblocks[i]) {
                    float thisH = b.health;
                    float blostHealthPct = b.health / b.maxHealth;
                    float otherH = other.health;
                    float otherlostHealthPct = other.health / other.maxHealth;
                    if (blostHealthPct > otherlostHealthPct && thisH > 100 && otherH < (other.maxHealth - 100)) {
                        other.health += 100;
                        b.health -= 100;
                    }
                }
            }
        }
    }
}
