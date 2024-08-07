package tutorial;

import arc.*;
import arc.util.*;
import mindustry.game.EventType.*;
import mindustry.mod.*;
import mindustry.ui.dialogs.*;

public class TutorialMod extends Mod{
    public static ModItems modItems;

    public TutorialMod(){
        Log.info("Loaded TutorialMod constructor.");

        //listen for game load event
        Events.on(ClientLoadEvent.class, e -> {
            //show dialog upon startup
            Time.runTask(10f, () -> {
                BaseDialog dialog = new BaseDialog("穿山甲");
                dialog.cont.add("我地任务完成啦").row();
                //mod sprites are prefixed with the mod name (this mod is called 'example-java-mod' in its config)
                dialog.cont.image(Core.atlas.find("tutorial-mod-csj")).pad(50f).row();
                dialog.cont.button("没了", dialog::hide).size(100f, 50f);
                dialog.show();
            });
        });
    }

    @Override
    public void loadContent(){
        Log.info("Loading some tutorial content.");
        modItems = new ModItems();
        modItems.load();
    }

}
