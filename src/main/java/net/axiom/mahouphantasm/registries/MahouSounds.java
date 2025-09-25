package net.axiom.mahouphantasm.registries;

import net.axiom.mahouphantasm.MahouPhantasm;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class MahouSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, MahouPhantasm.MOD_ID);

    public static final RegistryObject<SoundEvent> DICE =
            SOUND_EVENTS.register("spells.dice",
                    () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(MahouPhantasm.MOD_ID, "spells.dice")));
    public static final RegistryObject<SoundEvent> BLOOD_BOIL_EXPLOSION =
            SOUND_EVENTS.register("spells.blood_boil_explosion",
                    () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(MahouPhantasm.MOD_ID, "spells.blood_boil_explosion")));
    public static final RegistryObject<SoundEvent> BLOOD_BOIL_STEAM =
            SOUND_EVENTS.register("spells.blood_boil_steam",
                    () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(MahouPhantasm.MOD_ID, "spells.blood_boil_steam")));



    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}