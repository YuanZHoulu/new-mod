package tutorial.components;

import mindustry.gen.Building;

public class ComponentBase<Build extends Building> {
    public void onUpdate(Build b){
        for (Building other : b.proximity){
            if (other.block != b.block)
                continue;
            float thisH = b.health;
            float otherH = other.health;
            if (thisH > otherH && thisH > 1 && otherH < other.maxHealth){
                other.health += 1;
                b.health -= 1;
            }
        }
    }
}
