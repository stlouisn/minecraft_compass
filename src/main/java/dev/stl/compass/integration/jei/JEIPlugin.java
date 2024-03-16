package dev.stl.compass.integration.jei;

import dev.stl.compass.Compass;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.registration.*;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import javax.annotation.ParametersAreNonnullByDefault;
import net.minecraft.MethodsReturnNonnullByDefault;

@JeiPlugin
@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
@SuppressWarnings("unused")
public class JEIPlugin implements IModPlugin {

    private static final ResourceLocation ID = new ResourceLocation(Compass.MOD_ID, "jei_plugin");

    @Override
    public void registerRecipes(IRecipeRegistration registration) {

        registration.addIngredientInfo(new ItemStack(Compass.ANCIENT_COMPASS_ITEM.get()), VanillaTypes.ITEM_STACK,
                Component.translatable("jei.info.ancient_compass"));

    }

    @Override
    public ResourceLocation getPluginUid() {
        return ID;
    }

}