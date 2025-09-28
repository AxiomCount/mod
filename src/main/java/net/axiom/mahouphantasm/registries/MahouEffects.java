package net.axiom.mahouphantasm.registries;

import net.axiom.mahouphantasm.effect.*;
import net.axiom.mahouphantasm.MahouPhantasm;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class MahouEffects {
    public static final DeferredRegister<net.minecraft.world.effect.MobEffect> MOB_EFFECT_DEFERRED_REGISTER;
    public static final RegistryObject<net.minecraft.world.effect.MobEffect> BLOODBOIL;

    public MahouEffects() {
    }

    public static void register(IEventBus eventBus) {
        MOB_EFFECT_DEFERRED_REGISTER.register(eventBus);
    }

    static {
        MOB_EFFECT_DEFERRED_REGISTER = DeferredRegister.create(Registries.MOB_EFFECT, MahouPhantasm.MOD_ID);
        BLOODBOIL = MOB_EFFECT_DEFERRED_REGISTER.register("blood_boil", () -> {
            return new BloodBoilEffect(MobEffectCategory.BENEFICIAL, 4393481);
        });
    }
}
