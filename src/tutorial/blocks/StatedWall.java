package tutorial.blocks;

import arc.Core;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.TextureRegion;
import mindustry.world.Block;
import mindustry.world.blocks.defense.Wall;
import tutorial.components.ComponentBase;

import java.util.ArrayList;

import static mindustry.content.Blocks.copperWall;
import static mindustry.content.Blocks.copperWallLarge;
import static tutorial.ModBlocks.A测试wall;
import static tutorial.ModBlocks.B测试wall;

public class StatedWall extends Wall {
    public TextureRegion[] states;
    public int stateNumber;
    public String Availableblocks;
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
            for (ComponentBase<StatedWallBuild> component : components) {
                component.onUpdate(this);
            }
        }


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


        public boolean Sharingdetect(Block block) {
            switch (Availableblocks){
                case "A测试wall":
                    if (block == A测试wall || block == B测试wall || block == copperWall){
                        return true;
                    }else {
                        return false;
                    }
                case "B测试wall":
                    if (block == A测试wall || block == B测试wall || block == copperWallLarge){
                        return true;
                    }else {
                        return false;
                    }
            }
            return false;
        }
    }
}