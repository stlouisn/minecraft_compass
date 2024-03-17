package dev.stl.compass.items;

import com.mojang.datafixers.util.Pair;
import net.minecraft.client.renderer.item.CompassItemPropertyFunction;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.core.GlobalPos;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.levelgen.structure.BuiltinStructures;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;

import java.util.function.Consumer;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class AncientCompassItem extends Item {

    public AncientCompassItem(Properties properties) {
        super(properties);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int itemSlot, boolean isSelected) {
        if (!level.isClientSide) {
            BlockPos pos = null;
            CompoundTag tag = stack.getOrCreateTag();
            if (tag.contains("AncientCityPos")) {
                pos = BlockPos.of(tag.getLong("AncientCityPos"));
            }
            ServerLevel serverLevel = (ServerLevel) level;
            Pair<BlockPos, Holder<Structure>> nearestMapStructure = serverLevel.getChunkSource().getGenerator().findNearestMapStructure(serverLevel, HolderSet.direct(serverLevel.registryAccess().registryOrThrow(Registries.STRUCTURE).getHolderOrThrow(BuiltinStructures.ANCIENT_CITY)), entity.blockPosition(), 50, false);
            if (nearestMapStructure != null) {
                if (nearestMapStructure.getFirst() != pos) {
                    tag.putLong("AncientCityPos", nearestMapStructure.getFirst().asLong());
                }
                tag.putString("AncientCityLevel", level.dimension().location().toString());
            } else {
                tag.remove("AncientCityPos");
                tag.remove("AncientCityLevel");
            }
        }
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        ItemProperties.register(this, new ResourceLocation("angle"), new CompassItemPropertyFunction((level, stack, entity) -> {
            if (!stack.getOrCreateTag().contains("AncientCityPos") || !stack.getOrCreateTag().contains("AncientCityLevel")) {
                return null;
            }
            return GlobalPos.of(ResourceKey.create(Registries.DIMENSION, new ResourceLocation(stack.getOrCreateTag().getString("AncientCityLevel"))), BlockPos.of(stack.getOrCreateTag().getLong("AncientCityPos")));
        }));
    }

}