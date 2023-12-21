package aqario.furnishings.common.block.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionUtil;
import net.minecraft.potion.Potions;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class FluidContainerBlockEntity extends BlockEntity {
    private boolean empty;
    private Potion potion;
    private String potionType;

    public FluidContainerBlockEntity(BlockPos pos, BlockState state) {
        super(FurnishingsBlockEntityType.FLUID_CONTAINER, pos, state);
        potion = Potions.EMPTY;
        potionType = Registry.ITEM.getId(Items.POTION).toString();
    }

    public @NotNull Potion getPotion() {
        return potion;
    }

    public void setPotion(Potion potion) {
        this.potion = potion;
    }

    public @NotNull String getPotionType() {
        return potionType;
    }

    public void setPotionType(String potionType) {
        this.potionType = potionType;
    }

//    @Override
//    public void markDirty() {
//        if (this.world == null) return;
//        int light = 10;
//        if (light != this.getCachedState().get(FluidContainerBlock.LUMINANCE)) {
//            this.world.setBlockState(this.pos, this.getCachedState().with(FluidContainerBlock.LUMINANCE, light), 2);
//        }
//        this.world.updateListeners(this.pos, this.getCachedState(), this.getCachedState(), Block.NOTIFY_LISTENERS);
//        super.markDirty();
//    }

    public boolean interact(PlayerEntity player, Hand hand, World world, @Nullable BlockPos pos) {
//        if (world.isClient) {
//            return false;
//        }
        ItemStack stack = player.getStackInHand(hand);
        // TODO: Add support for other fluids

        if (!this.isEmpty()) {
            // Empty fluid container
            if (stack.getItem() == Items.GLASS_BOTTLE) {
                Potion potion = this.getPotion();
                if (potion == Potions.EMPTY)
                    return false;

                Identifier potionTypeResourceLocation = Identifier.tryParse(this.getPotionType());
                if (potionTypeResourceLocation == null)
                    return false;

                this.setEmpty(true);

                Item potionType = Registry.ITEM.get(potionTypeResourceLocation);
                player.setStackInHand(hand, ItemUsage.exchangeStack(stack, player, PotionUtil.setPotion(new ItemStack(potionType), potion)));
                player.incrementStat(Stats.USED.getOrCreateStat(stack.getItem()));
                world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_FILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.emitGameEvent(null, GameEvent.FLUID_PICKUP, pos);
                player.sendMessage(Text.literal("Filled bottle"), true);
                return true;
            }
            // Drink from container
            if (stack.isEmpty()) {
                Potion potion = this.getPotion();
                if (potion == Potions.EMPTY)
                    return false;

                this.setEmpty(true);
                if (!world.isClient) {
                    for(StatusEffectInstance statusEffectInstance : potion.getEffects()) {
                        if (statusEffectInstance.getEffectType().isInstant()) {
                            statusEffectInstance.getEffectType().applyInstantEffect(player, player, player, statusEffectInstance.getAmplifier(), 1.0);
                            continue;
                        }
                        player.addStatusEffect(new StatusEffectInstance(statusEffectInstance));
                    }
                }
                world.playSound(null, pos, SoundEvents.ENTITY_GENERIC_DRINK, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.emitGameEvent(null, GameEvent.DRINK, pos);
                player.sendMessage(Text.literal("Drank successfully"), true);
                return true;
            }
            return false;
        }

        // Fill fluid container
        if (stack.getItem() == Items.POTION) {
            Identifier potionTypeResource = Identifier.tryParse(stack.getItem().toString());
            if (potionTypeResource == null)
                return false;

            Potion potionInHand = PotionUtil.getPotion(stack);
            String potionTypeInHand = potionTypeResource.toString();

            if (potionInHand == Potions.EMPTY || potionTypeInHand == null)
                return false;

            this.setPotion(potionInHand);
            this.setPotionType(potionTypeInHand);

            this.fill(stack.getNbt());
            player.setStackInHand(hand, ItemUsage.exchangeStack(stack, player, new ItemStack(Items.GLASS_BOTTLE)));
            player.incrementStat(Stats.USED.getOrCreateStat(stack.getItem()));
            world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
            world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);
            player.sendMessage(Text.literal("Emptied bottle"), true);
            return true;
        }

        return false;
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        if (potion != null) {
            Identifier potionId = Registry.POTION.getId(potion);
            String potionName = potionId.toString();

            nbt.putString("Potion", potionName);
            nbt.putString("PotionType", potionType);
        }
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        potion = Registry.POTION.get(Identifier.tryParse(nbt.getString("Potion")));
    }

    public void setEmpty(boolean isEmpty) {
        this.empty = isEmpty;
    }

    public boolean isEmpty() {
        return this.getPotion() == Potions.EMPTY;
    }

    public void fill(@Nullable NbtCompound fluid) {
        this.setEmpty(false);
//        this.fluid = null;
//        if (fluid != null) {
//            this.fluid = fluid.copy();
//        }
//        this.specialColor = 0;
//        if (this.fluid.isEmpty()) this.setCount(0);
//        this.needsColorRefresh = true;
    }
}
