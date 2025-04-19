package cesco.nsmod.entity;

import cesco.nsmod.NsMod;
import cesco.nsmod.entity.custom.CucciaInteractionEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class ModEntities {
    public static final EntityType<CucciaInteractionEntity> CUCCIA_INTERACTION = EntityType.Builder.<CucciaInteractionEntity>create(CucciaInteractionEntity::new, SpawnGroup.MISC)
            .dimensions(1.0f, 0.5f)
            .build();

    public static void registerEntities() {
        Registry.register(Registries.ENTITY_TYPE, new Identifier(NsMod.MOD_ID, "cuccia_interaction"), CUCCIA_INTERACTION);
    }
}
