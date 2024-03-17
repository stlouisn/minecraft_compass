package dev.stl.compass.registry;

import dev.stl.compass.Compass;
import dev.stl.compass.items.AncientCompassItem;
import net.mehvahdjukaar.moonlight.api.platform.RegHelper;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

import java.util.function.Supplier;

public class ModItems {

    public static ResourceLocation res(String name) {
        return new ResourceLocation(Compass.MOD_ID, name);
    }

    public static final ResourceLocation ANCIENT_COMPASS_NAME = res("ancient_compass");
    public static final Supplier<Item> ANCIENT_COMPASS_ITEM = RegHelper.registerItem(ANCIENT_COMPASS_NAME, () ->
            new AncientCompassItem(new Item.Properties().rarity(Rarity.RARE).stacksTo(1).setNoRepair()));

    public static void init() {
    }

}