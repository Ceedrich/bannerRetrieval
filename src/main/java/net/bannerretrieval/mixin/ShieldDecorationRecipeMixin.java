package net.bannerretrieval.mixin;

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
import static net.minecraft.item.BlockItem.BLOCK_ENTITY_TAG_KEY;

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

        // itemStack2 : shield
        // itemStack  : banner

        NbtCompound bannerBlockEntityNbt = BlockItem.getBlockEntityNbt(itemStack);
        NbtCompound bannerNbt = itemStack.getNbt();
        NbtCompound shieldNbt = itemStack2.getNbt();
        if (shieldNbt == null) shieldNbt = new NbtCompound();

        NbtCompound shieldBlockEntityNbt = bannerBlockEntityNbt == null ? new NbtCompound() : bannerBlockEntityNbt.copy();
        NbtCompound shieldDecorationNbt = bannerNbt == null ? new NbtCompound() : bannerNbt.copy();
        shieldDecorationNbt.remove(BLOCK_ENTITY_TAG_KEY); // remove BLOCK_ENTITY_TAG already covered

        shieldBlockEntityNbt.putInt("Base", ((BannerItem)itemStack.getItem()).getColor().getId());

        shieldNbt.put(DECORATION_TAG_KEY, shieldDecorationNbt);
        shieldNbt.put(BLOCK_ENTITY_TAG_KEY, shieldBlockEntityNbt);

        itemStack2.setNbt(shieldNbt);
        return itemStack2;
    }
}
