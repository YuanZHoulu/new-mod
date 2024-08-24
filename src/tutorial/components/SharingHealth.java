package tutorial.components;

import mindustry.gen.Building;
import tutorial.blocks.StatedWall;



public class SharingHealth extends ComponentBase<StatedWall.StatedWallBuild> {

    @Override
    public void onUpdate(StatedWall.StatedWallBuild b, float deliveryrate) {
        for (Building other : b.proximity) {
            if (b.Sharingdetect(other.block)) {
                float thisH = b.health;
                float blostHealthPct = b.health / b.maxHealth;
                float otherH = other.health;
                float otherlostHealthPct = other.health / other.maxHealth;
                if (blostHealthPct > otherlostHealthPct ) {
                    if (thisH > deliveryrate * 100 && otherH < (other.maxHealth - deliveryrate * 100)){
                        b.health -= deliveryrate * 100;
                        other.health += deliveryrate * 100;
                    }else if (thisH > deliveryrate * 50 && otherH < (other.maxHealth - deliveryrate * 50)){
                        b.health -= deliveryrate * 50;
                        other.health += deliveryrate * 50;
                    }else if (thisH > deliveryrate * 10 && otherH < (other.maxHealth - deliveryrate * 10)){
                        b.health -= deliveryrate * 10;
                        other.health += deliveryrate * 10;
                    }
                }
            }
        }
    }
}

