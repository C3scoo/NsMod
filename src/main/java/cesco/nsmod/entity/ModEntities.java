package cesco.nsmod.entity;

import cesco.nsmod.entity.custom.CucciaInteractionEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

public class ModEntities {

    public static final EntityType<CucciaInteractionEntity> CUCCIA_INTERACTION = 
        Registry.register(Registry.ENTITY_TYPE, new Identifier("nsmod", "cuccia_interaction"), 
        EntityType.Builder.create(CucciaInteractionEntity::new, EntityType.CREATURE).build(null));

    public static void registerEntities() {
        // Questo è per assicurarsi che le entità siano registrate
    }
}
