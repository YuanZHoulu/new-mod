package tutorial.blocks;

import arc.Core;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.TextureRegion;
import mindustry.world.blocks.defense.Wall;
import tutorial.components.ComponentBase;

public class StatedWall extends Wall {
    public TextureRegion[] states;
    public int stateNumber;
    public ComponentBase<StatedWallBuild> component;

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

            public class StatedWallBuild extends WallBuild{
        @Override
        public void updateTile(){
            if (component != null) {
                component.onUpdate(this);
            }
            }
        }


    public class StatedBuild extends WallBuild {
        @Override
        public void draw() {
            int curIndex = (int) (lostHealthPct() * stateNumber);
            curIndex = Math.min(curIndex, stateNumber - 1);
            Draw.rect(states[curIndex], x, y);
            this.drawTeamTop();
        }
        public float lostHealthPct(){
            return 1f - health / maxHealth;
        }
    }
}


