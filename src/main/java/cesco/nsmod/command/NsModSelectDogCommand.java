package cesco.nsmod.command;

import cesco.nsmod.block.DogBedBlock;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import java.util.UUID;

public class NsModSelectDogCommand {

    public static LiteralArgumentBuilder<ServerCommandSource> register() {
        return CommandManager.literal("nsmod_selectdog")
                .then(CommandManager.argument("uuid", net.minecraft.command.argument.UuidArgumentType.uuid())
                    .then(CommandManager.argument("x", IntegerArgumentType.integer())
                        .then(CommandManager.argument("y", IntegerArgumentType.integer())
                            .then(CommandManager.argument("z", IntegerArgumentType.integer())
                                .executes(context -> {

                                    UUID dogUuid = net.minecraft.command.argument.UuidArgumentType.getUuid(context, "uuid");
                                    int x = IntegerArgumentType.getInteger(context, "x");
                                    int y = IntegerArgumentType.getInteger(context, "y");
                                    int z = IntegerArgumentType.getInteger(context, "z");

                                    ServerWorld world = context.getSource().getWorld();
                                    BlockPos targetPos = new BlockPos(x, y, z);

                                    WolfEntity wolf = world.getEntitiesByClass(WolfEntity.class,
                                            new Box(targetPos).expand(20),
                                            w -> w.getUuid().equals(dogUuid))
                                            .stream().findFirst().orElse(null);

                                    if (wolf == null) {
                                        context.getSource().sendError(Text.literal("Cane non trovato!"));
                                        return 0;
                                    }

                                    // Il cane cammina verso la cuccia
                                    wolf.getNavigation().startMovingTo(x + 0.5, y, z + 0.5, 1.0);

                                    // Salviamo che questo cane è assegnato a questa cuccia
                                    DogBedBlock.ASSIGNED_DOGS.put(dogUuid, targetPos);

                                    context.getSource().sendFeedback(() -> Text.literal("Il cane si sta dirigendo verso la cuccia!"), false);
                                    return 1;
                                })))));
    }
}
