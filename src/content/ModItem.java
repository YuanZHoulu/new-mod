package content;

import arc.graphics.*;
import arc.struct.*;
import mindustry.type.*;

class ModItem {
    public static Item
            //矿物
            赤铁矿;

    public static final Seq<Item> serpuloItems = new Seq<>(), erekirItems = new Seq<>(), erekirOnlyItems = new Seq<>();

    public static void load(){
赤铁矿 = new Item("赤铁矿", Color.valueOf("d99d73")){{
hardness =1;
cost =0.5f;
alwaysUnlocked =true;
}};}}