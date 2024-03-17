package dev.stl.compass;

import dev.stl.compass.items.*;
import net.mehvahdjukaar.moonlight.api.platform.RegHelper;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;
import net.minecraftforge.fml.common.Mod;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.function.Supplier;

@Mod(Compass.MOD_ID)
public class Compass {

    public static final String MOD_ID = "compass";

    @SuppressWarnings("unused")
    private static final Logger LOGGER = LogManager.getLogger("Compass");

    public static ResourceLocation res(String name) {
        return new ResourceLocation(MOD_ID, name);
    }

    public static final ResourceLocation ANCIENT_COMPASS_NAME = res("ancient_compass");
    public static final Supplier<Item> ANCIENT_COMPASS_ITEM = RegHelper.registerItem(ANCIENT_COMPASS_NAME, () ->
        new AncientCompassItem(new Item.Properties().rarity(Rarity.RARE).stacksTo(1).setNoRepair()));

    public Compass() {
        RegHelper.addItemsToTabsRegistration(Compass::addCreativeTabItems);
    }

    private static void addCreativeTabItems(RegHelper.ItemToTabEvent event) {

        event.addAfter(CreativeModeTabs.TOOLS_AND_UTILITIES, i -> i.is(Items.COMPASS),
                ANCIENT_COMPASS_ITEM.get());

    }

}