package cesco.nsmod.block;

import cesco.nsmod.entity.CucciaInteractionEntity;
import cesco.nsmod.entity.ModEntities;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.AbstractBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Hand;
import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.server.world.ServerWorld;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class DogBedBlock extends Block {

    public static final EnumProperty<WoodType> WOOD_TYPE = EnumProperty.of("wood_type", WoodType.class);
    public static final EnumProperty<DyeColor> COLOR = EnumProperty.of("color", DyeColor.class);
    public static final Map<UUID, BlockPos> ASSIGNED_DOGS = new HashMap<>();

    public DogBedBlock(AbstractBlock.Settings settings) {
        super(settings);
        this.setDefaultState(this.getStateManager().getDefaultState()
            .with(WOOD_TYPE, WoodType.OAK)
            .with(COLOR, DyeColor.WHITE));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(WOOD_TYPE, COLOR);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public boolean hasSidedTransparency(BlockState state) {
        return false;
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, net.minecraft.block.ShapeContext context) {
        return VoxelShapes.fullCube();
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, net.minecraft.entity.LivingEntity placer, net.minecraft.item.ItemStack itemStack) {
        if (!world.isClient() && world instanceof ServerWorld serverWorld) {
            CucciaInteractionEntity interaction = ModEntities.CUCCIA_INTERACTION.create(serverWorld);
            if (interaction != null) {
                interaction.refreshPositionAndAngles(pos.getX() + 0.5, pos.getY() + 0.2, pos.getZ() + 0.5, 0, 0);
                interaction.setOwnerPos(pos);
                serverWorld.spawnEntity(interaction);
            }
        }
        super.onPlaced(world, pos, state, placer, itemStack);
    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (!world.isClient() && world instanceof ServerWorld serverWorld && !state.isOf(newState.getBlock())) {
            CucciaInteractionEntity interaction = CucciaInteractionEntity.find(serverWorld, pos);
            if (interaction != null) {
                interaction.discard();
            }
        }
        super.onStateReplaced(state, world, pos, newState, moved);
    }

    public enum WoodType implements StringIdentifiable {
        OAK("oak"),
        SPRUCE("spruce"),
        BIRCH("birch"),
        JUNGLE("jungle"),
        ACACIA("acacia"),
        DARK_OAK("dark_oak"),
        MANGROVE("mangrove"),
        CHERRY("cherry"),
        BAMBOO("bamboo"),
        CRIMSON("crimson"),
        WARPED("warped");

        private final String name;

        WoodType(String name) {
            this.name = name;
        }

        @Override
        public String asString() {
            return this.name;
        }
    }
}
