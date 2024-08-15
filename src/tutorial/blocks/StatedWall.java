package tutorial.blocks;

import arc.Core;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.TextureRegion;
import mindustry.world.blocks.defense.Wall;

public class StatedWall extends Wall {
    public TextureRegion[] states;
    public int stateNumber;

    public StatedWall(String name) {
        super(name);
    }
    public class StatedBuild extends WallBuild {
        @Override
        public void draw() {

            int curIndex = (int) (lostHealthPct() * stateNumber);
            curIndex = Math.min(curIndex, stateNumber - 1);
            Draw.rect(states[curIndex], x, y);
            this.drawTeamTop();
        }
        public float lostHealthPct() {
            return 1f - health / maxHealth;
        }
    }

    @Override
    public void load() {
        super.load();
        states = new TextureRegion[stateNumber];
        for (int i = 0; i < stateNumber; i++) {
            states[i] = Core.atlas.find(name + "-" + i);

        }
    }
}


