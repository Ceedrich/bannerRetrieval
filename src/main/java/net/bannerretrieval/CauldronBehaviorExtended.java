package net.bannerretrieval;

import net.minecraft.block.LeveledCauldronBlock;
import net.minecraft.block.cauldron.CauldronBehavior;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;

import static net.bannerretrieval.Utils.*;
import static net.minecraft.item.BlockItem.BLOCK_ENTITY_TAG_KEY;

public interface CauldronBehaviorExtended {
    CauldronBehavior WASH_SHIELD = (state, world, pos, player, hand, stack) -> {
        Item item = stack.getItem();
        NbtCompound nbtData = stack.getNbt();
        if (
                nbtData == null ||
                nbtData.isEmpty() ||
                !nbtData.contains(BLOCK_ENTITY_TAG_KEY)
        ) return ActionResult.PASS;

        NbtCompound shieldBlockEntityNbt = BlockItem.getBlockEntityNbt(stack);
        NbtCompound shieldDecorationNbt = getDecorationNbt(stack);

        // No banner on the shield
        if (shieldBlockEntityNbt == null || shieldBlockEntityNbt.isEmpty()) return ActionResult.PASS;

        // extract banner block entity nbt
        NbtCompound bannerBlockEntityNbt = shieldBlockEntityNbt.copy();
        stack.removeSubNbt(BLOCK_ENTITY_TAG_KEY);

        // extract banner color
        String bannerColor = COLOR_MAP.get(bannerBlockEntityNbt.getInt("Base"));
        bannerBlockEntityNbt.remove("Base");

        // extract decorationNbt
        NbtCompound bannerNbt = shieldDecorationNbt == null ? null : shieldDecorationNbt.copy();
        stack.removeSubNbt(DECORATION_TAG_KEY);

        // create item
        NbtCompound bannerItemNbt = new NbtCompound();
        bannerItemNbt.putString("id", "minecraft:" + bannerColor + "_banner");
        bannerItemNbt.putInt("Count", 1);
        if (bannerNbt != null && !bannerNbt.isEmpty()) bannerItemNbt.put("tag", bannerNbt); // add bannerNbt
        ItemStack banner = ItemStack.fromNbt(bannerItemNbt);

        // Add bannerBlockItemNbt
        BlockItem.setBlockEntityNbt(banner, BlockEntityType.BANNER, bannerBlockEntityNbt);

        // Spawn Item
        world.spawnEntity(new ItemEntity(world, pos.getX() + 0.5, pos.getY() + Y_OFFSET, pos.getZ() + 0.5, banner, 0, Y_VELOCITY, 0));;

        // Update Cauldron
        LeveledCauldronBlock.decrementFluidLevel(state, world, pos);

        // Update Stats
        player.incrementStat(Stats.USE_CAULDRON);
        player.incrementStat(Stats.USED.getOrCreateStat(item));

        return ActionResult.SUCCESS;
    };
}
