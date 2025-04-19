package cesco.nsmod.block;

import cesco.nsmod.entity.custom.CucciaInteractionEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class DogBedBlock extends Block {

    public static final EnumProperty<WoodType> WOOD_TYPE = EnumProperty.of("wood_type", WoodType.class);

    public DogBedBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getStateManager().getDefaultState().with(WOOD_TYPE, WoodType.OAK));
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient()) {
            player.sendMessage(Text.literal("Cuccia utilizzata!"), false);
            // Interagisci con l'entità qui
            CucciaInteractionEntity interactionEntity = CucciaInteractionEntity.create((ServerWorld) world, pos);
            world.spawnEntity(interactionEntity); // Crea l'entità nella posizione corretta
        }
        return ActionResult.SUCCESS;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(WOOD_TYPE);
    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (newState.getBlock() != state.getBlock()) {
            super.onStateReplaced(state, world, pos, newState, moved);
            // Puoi aggiungere la logica di pulizia o altre operazioni qui
        }
    }
}
