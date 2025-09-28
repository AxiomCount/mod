package net.axiom.mahouphantasm.spell.blood;

import io.redspace.ironsspellbooks.api.config.DefaultConfig;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.spells.*;
import io.redspace.ironsspellbooks.api.util.AnimationHolder;
import io.redspace.ironsspellbooks.api.util.Utils;
import net.axiom.mahouphantasm.MahouPhantasm;
import net.axiom.mahouphantasm.registries.MahouEffects;
import net.axiom.mahouphantasm.registries.MahouSounds;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import java.util.List;
import java.util.Optional;

@AutoSpellConfig
public class BloodBoilSpell extends AbstractSpell {
    private static final ResourceLocation SPELL_ID = new ResourceLocation(MahouPhantasm.MOD_ID, "blood_boil");


    @Override
    public List<MutableComponent> getUniqueInfo(int spellLevel, LivingEntity caster) {
//        float damage = getSpellPower(spellLevel, caster);
        return List.of(
                Component.translatable("ui.mahouphantasm.returned_damage", Utils.stringTruncation(this.getSpellPower(spellLevel, caster), 2)),
                Component.translatable("ui.irons_spellbooks.effect_length", Utils.timeFromTicks(this.getSpellPower(spellLevel, caster), 1))
        );
    }

    private final DefaultConfig defaultConfig = new DefaultConfig()
            .setMinRarity(SpellRarity.RARE)
            .setSchoolResource(SchoolRegistry.BLOOD_RESOURCE)
            .setMaxLevel(5)
            .setCooldownSeconds(60)
            .build();

    public BloodBoilSpell() {
        this.manaCostPerLevel = 15;
        this.baseSpellPower = 40;
        this.spellPowerPerLevel = 10;
        this.castTime = 0;
        this.baseManaCost = 40;
    }

    @Override
    public SpellRarity getRarity(int spellLevel) {
        return switch (spellLevel) {
            case 1, 2 -> SpellRarity.RARE;
            case 3, 4 -> SpellRarity.EPIC;
            case 5 -> SpellRarity.LEGENDARY;
            default -> SpellRarity.COMMON;
        };
    }

    @Override
    public boolean canBeCraftedBy(Player player) {return false; }

    @Override
    public boolean allowLooting() {
        return false;
    }

    @Override
    public DefaultConfig getDefaultConfig() {
        return defaultConfig;
    }

    @Override
    public CastType getCastType() {
        return CastType.INSTANT;
    }

    @Override
    public ResourceLocation getSpellResource() {
        return SPELL_ID;
    }

    @Override
    public Optional<SoundEvent> getCastStartSound() { return Optional.empty(); }

    @Override
    public Optional<SoundEvent> getCastFinishSound() {
        return Optional.of(MahouSounds.BLOOD_BOIL_STEAM.get());
    }

    @Override
    public AnimationHolder getCastFinishAnimation() { return SpellAnimations.SELF_CAST_ANIMATION; }

    @Override
    public void onCast(Level level, int spellLevel, LivingEntity caster, CastSource castSource, MagicData playerMagicData) {
        int duration = 200 + spellLevel * 40; // 10–20 sec
        caster.addEffect(new MobEffectInstance(
                MahouEffects.BLOODBOIL.get(), duration, spellLevel - 1,
                false, true, true));

        MagicData.getPlayerMagicData(caster)
                .getSyncedData()
                .setHeartstopAccumulatedDamage(0f);
    }
}
