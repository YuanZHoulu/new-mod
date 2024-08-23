package tutorial.blocks;

import arc.Core;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.TextureRegion;
import mindustry.world.blocks.defense.Wall;
import tutorial.components.ComponentBase;

import java.util.ArrayList;

public class StatedWall extends Wall {
    public TextureRegion[] states;
    public int stateNumber;
    public StatedWall[] Availableblocks;
    public ArrayList<ComponentBase<StatedWallBuild>> components =
            new ArrayList<>();

    public StatedWall(String name) {
        super(name);
    }

    @Override
    public void load() {
        super.load();
        states = new TextureRegion[stateNumber];
        for (int i = 0; i < stateNumber; i++) {
            states[i] = Core.atlas.find(name + "-" + i);
        }
    }


    public class StatedWallBuild extends WallBuild {
        @Override
        public void updateTile() {
            for (ComponentBase<StatedWall.StatedWallBuild> component : components) {
                component.onUpdate(this);
            }
        }


        @Override
        public void draw() {
            int curIndex = (int) ((1f - health / maxHealth) * stateNumber);
            curIndex = Math.min(curIndex, stateNumber - 1);
            Draw.rect(states[curIndex], x, y);
            this.drawTeamTop();
        }


        public boolean detection() {
            for (int i = 0; i < Availableblocks.length; i++) {
                if (this.block == Availableblocks[i]) {
                    return false;
                }
            }
            return true;
        }
    }
}




