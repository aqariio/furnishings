package aqario.furnishings.common.block;

import aqario.furnishings.common.block.entity.FluidContainerBlockEntity;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionUtil;
import net.minecraft.potion.Potions;
import net.minecraft.registry.Registries;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings("deprecation")
public abstract class FluidContainerBlock extends BlockWithEntity implements Waterloggable {
    public static final IntProperty LUMINANCE = IntProperty.of("luminance", 0, 15);

    public FluidContainerBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(LUMINANCE, 0));
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (world.getBlockEntity(pos) instanceof FluidContainerBlockEntity container) {
            ItemStack stack = player.getStackInHand(hand);
            // TODO: Add support for other fluids

            if (!container.isEmpty()) {
                // Empty fluid container
                if (stack.getItem() == Items.GLASS_BOTTLE) {
                    Potion potion = container.getPotion();
                    if (potion == Potions.EMPTY) {
                        return ActionResult.PASS;
                    }

                    Identifier potionTypeResourceLocation = Identifier.tryParse(container.getPotionType());
                    if (potionTypeResourceLocation == null) {
                        return ActionResult.PASS;
                    }

                    container.setPotion(Potions.EMPTY);

                    Item potionType = Registries.ITEM.get(potionTypeResourceLocation);
                    player.setStackInHand(hand, ItemUsage.exchangeStack(stack, player, PotionUtil.setPotion(new ItemStack(potionType), potion)));
                    player.incrementStat(Stats.USED.getOrCreateStat(stack.getItem()));
                    world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_FILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
                    world.emitGameEvent(null, GameEvent.FLUID_PICKUP, pos);
                    player.sendMessage(Text.literal("Filled bottle"), true);

                    if (!world.isClient) {
                        container.markDirty();
                    }
                    return ActionResult.success(world.isClient);
                }
                // Drink from container
                if (stack.isEmpty() && stack.getItem() != Items.POTION) {
                    Potion potion = container.getPotion();
                    if (potion == Potions.EMPTY) {
                        return ActionResult.PASS;
                    }

                    container.setPotion(Potions.EMPTY);
                    if (!world.isClient) {
                        for (StatusEffectInstance statusEffectInstance : potion.getEffects()) {
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

                    if (!world.isClient) {
                        container.markDirty();
                    }
                    return ActionResult.success(world.isClient);
                }
                return ActionResult.PASS;
            }

            // Fill fluid container
            if (stack.getItem() == Items.POTION) {
                Identifier potionTypeResource = Identifier.tryParse(stack.getItem().toString());
                if (potionTypeResource == null) {
                    return ActionResult.PASS;
                }

                Potion potionInHand = PotionUtil.getPotion(stack);
                String potionTypeInHand = potionTypeResource.toString();

                if (potionInHand == Potions.EMPTY || potionTypeInHand == null) {
                    return ActionResult.PASS;
                }

                container.setPotion(potionInHand);
                container.setPotionType(potionTypeInHand);

                player.setStackInHand(hand, ItemUsage.exchangeStack(stack, player, new ItemStack(Items.GLASS_BOTTLE)));
                player.incrementStat(Stats.USED.getOrCreateStat(stack.getItem()));
                world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);
                player.sendMessage(Text.literal("Emptied bottle"), true);

                if (!world.isClient) {
                    container.markDirty();
                }
                return ActionResult.success(world.isClient);
            }

            return ActionResult.PASS;
        }
        return ActionResult.PASS;
    }

    @Override
    public boolean hasComparatorOutput(BlockState state) {
        return true;
    }

    @Override
    public int getComparatorOutput(BlockState state, World world, BlockPos pos) {
        if (world.getBlockEntity(pos) instanceof FluidContainerBlockEntity container) {
            return container.isEmpty() ? 0 : 15;
        }
        return 0;
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new FluidContainerBlockEntity(pos, state);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(LUMINANCE);
    }
}
