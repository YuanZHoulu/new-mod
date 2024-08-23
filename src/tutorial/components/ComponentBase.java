package tutorial.components;

import mindustry.gen.Building;
import tutorial.blocks.StatedWall;

public abstract class ComponentBase<Build extends Building> {
    public void onUpdate(Build b) {
    }

}