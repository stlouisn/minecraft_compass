package dev.stl.compass.registry;

import net.mehvahdjukaar.moonlight.api.platform.RegHelper;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Items;

public class CreativeTab {

    public static void addCreativeTabItems(RegHelper.ItemToTabEvent event) {

        event.addAfter(CreativeModeTabs.TOOLS_AND_UTILITIES, i -> i.is(Items.COMPASS),
                ModItems.ANCIENT_COMPASS_ITEM.get());

    }

}