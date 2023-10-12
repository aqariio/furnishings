package aqario.furnishings.common.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

public class RedPaperLanternBlock extends PaperLanternBlock {
    protected static final VoxelShape STANDING_SHAPE = VoxelShapes.union(
		createCuboidShape(4, 1, 4, 12, 8, 12),
		createCuboidShape(6, 8, 6, 10, 9, 10),
		createCuboidShape(6, 0, 6, 10, 1, 10)
	);
    protected static final VoxelShape HANGING_SHAPE = VoxelShapes.union(
		createCuboidShape(4, 6, 4, 12, 13, 12),
		createCuboidShape(6, 13, 6, 10, 14, 10),
		createCuboidShape(6, 5, 6, 10, 6, 10)
	);

    public RedPaperLanternBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(HANGING, false).with(WATERLOGGED, false));
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return state.get(HANGING) ? HANGING_SHAPE : STANDING_SHAPE;
    }
}
