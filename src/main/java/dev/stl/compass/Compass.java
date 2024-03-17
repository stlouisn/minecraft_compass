package dev.stl.compass;

import dev.stl.compass.registry.CreativeTab;
import dev.stl.compass.registry.ModItems;
import net.mehvahdjukaar.moonlight.api.platform.RegHelper;
import net.minecraftforge.fml.common.Mod;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(Compass.MOD_ID)
public class Compass {

    public static final String MOD_ID = "compass";

    @SuppressWarnings("unused")
    private static final Logger LOGGER = LogManager.getLogger("Compass");

    public Compass() {

        ModItems.init();
        RegHelper.addItemsToTabsRegistration(CreativeTab::addCreativeTabItems);

    }

}