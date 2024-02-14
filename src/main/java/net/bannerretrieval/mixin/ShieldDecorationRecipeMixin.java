package net.bannerretrieval.mixin;

import net.fabricmc.loader.impl.util.log.Log;
import net.fabricmc.loader.impl.util.log.LogCategory;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.inventory.RecipeInputInventory;
import net.minecraft.item.BannerItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.DynamicRegistryManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import static net.bannerretrieval.Utils.DECORATION_TAG_KEY;

@Mixin(net.minecraft.recipe.ShieldDecorationRecipe.class)
public class ShieldDecorationRecipeMixin {
    /**
     * @author Ceedrich
     * @reason make the crafting recipe store nbt data of the banner so it can be retrieved.
     */
    @Overwrite
    public ItemStack craft(RecipeInputInventory recipeInputInventory, DynamicRegistryManager dynamicRegistryManager) {
        ItemStack itemStack = ItemStack.EMPTY;
        ItemStack itemStack2 = ItemStack.EMPTY;
        for (int i = 0; i < recipeInputInventory.size(); ++i) {
            ItemStack itemStack3 = recipeInputInventory.getStack(i);
            if (itemStack3.isEmpty()) continue;
            if (itemStack3.getItem() instanceof BannerItem) {
                itemStack = itemStack3;
                continue;
            }
            if (!itemStack3.isOf(Items.SHIELD)) continue;
            itemStack2 = itemStack3.copy();
        }
        if (itemStack2.isEmpty()) {
            return itemStack2;
        }
        NbtCompound blockEntityNbt = BlockItem.getBlockEntityNbt(itemStack) == null ? new NbtCompound() :BlockItem.getBlockEntityNbt(itemStack);
        NbtCompound otherBannerNbt = itemStack.getNbt() == null ? new NbtCompound() : itemStack.getNbt().copy();
        otherBannerNbt.remove("BlockEntityTag");

        NbtCompound resultNbt = itemStack2.getNbt() == null ? new NbtCompound() : itemStack2.getNbt().copy();
        resultNbt.put(DECORATION_TAG_KEY, otherBannerNbt);
        resultNbt.put("BlockEntityTag", blockEntityNbt);

        Log.info(LogCategory.GENERAL, resultNbt.toString());
        Log.info(LogCategory.GENERAL, itemStack.getNbt() == null ? "{}" : itemStack.getNbt().toString());

        resultNbt.putInt("Base", ((BannerItem)itemStack.getItem()).getColor().getId());
        itemStack2.setNbt(resultNbt);
        return itemStack2;
    }
}
