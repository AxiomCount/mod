package net.axiom.mahouphantasm.entity;

import net.axiom.mahouphantasm.MahouPhantasm;
import net.axiom.mahouphantasm.entity.spells.redmist_slash.RedmistSlash;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.Level;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class MahouEntityRegistry {
    private static final DeferredRegister<EntityType<?>> ENTITIES;

    public static final RegistryObject<EntityType<RedmistSlash>> REDMISTSLASH;

    public MahouEntityRegistry() {
    }

    public static void register(IEventBus eventBus) {
        ENTITIES.register(eventBus);
    }

    static {
        ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, MahouPhantasm.MOD_ID);

        REDMISTSLASH = ENTITIES.register("redmist_slash", () -> {
            return EntityType.Builder.of((EntityType<RedmistSlash> type, Level world) -> new RedmistSlash(type, world), MobCategory.MISC)
                    .sized(5.0F, 1.0F)
                    .clientTrackingRange(64)
                    .build(new ResourceLocation(MahouPhantasm.MOD_ID, "redmist_slash").toString());
        });
    }
}