package net.axiom.mahouphantasm.spell.blood;

import io.redspace.ironsspellbooks.api.config.DefaultConfig;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.spells.*;
import io.redspace.ironsspellbooks.api.util.AnimationHolder;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.capabilities.magic.MagicManager;
import io.redspace.ironsspellbooks.capabilities.magic.RecastInstance;
import io.redspace.ironsspellbooks.registries.MobEffectRegistry;
import io.redspace.ironsspellbooks.registries.SoundRegistry;
import io.redspace.ironsspellbooks.util.ParticleHelper;
import net.axiom.mahouphantasm.MahouPhantasm;
import net.axiom.mahouphantasm.registries.MahouSounds;
import net.axiom.mahouphantasm.spell.MSpellAnimations;
import net.axiom.mahouphantasm.spell.MahouSchools;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import org.jetbrains.annotations.Nullable;

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
            .setCooldownSeconds(120)
            .build();

    public BloodBoilSpell() {
        this.manaCostPerLevel = 15;
        this.baseSpellPower = 50;
        this.spellPowerPerLevel = 5;
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


    }
}
//    @Override
//    public void onCast(Level level, int spellLevel, LivingEntity caster, CastSource source, MagicData data) {
//        final double RANGE = 2;
//        final double HITBOX_RADIUS = 1;
//        float damage = getSpellPower(spellLevel, caster);
//
//        Vec3 eye = caster.getEyePosition();
//        Vec3 look = caster.getLookAngle().normalize();
//        Vec3 center = eye.add(look.scale(RANGE));
//
//        if (!level.isClientSide) {
//            AABB box = new AABB(
//                    center.x - HITBOX_RADIUS, center.y - HITBOX_RADIUS, center.z - HITBOX_RADIUS,
//                    center.x + HITBOX_RADIUS, center.y + HITBOX_RADIUS, center.z + HITBOX_RADIUS
//            );
//
//            // Hitbox
//            for (double x : new double[]{box.minX, box.maxX}) {
//                for (double y : new double[]{box.minY, box.maxY}) {
//                    for (double z : new double[]{box.minZ, box.maxZ}) {
//                        MagicManager.spawnParticles(level, ParticleHelper.EMBERS,
//                                x, y, z,
//                                5,
//                                0.01, 0.01, 0.01,
//                                0.0, false
//                        );
//                    }
//                }
//            }
////            // Filled Hitbox
////            MagicManager.spawnParticles(level, ParticleHelper.FIRE,
////                    box.getCenter().x, box.getCenter().y, box.getCenter().z,
////                    250,
////                    box.getXsize() / 2,
////                    box.getYsize() / 2,
////                    box.getZsize() / 2,
////                    0.0001, false
////            );
//
//            List<LivingEntity> targets = level.getEntitiesOfClass(
//                    LivingEntity.class,
//                    box,
//                    e -> e != caster && e.isAlive() && e.isPickable()
//            );
//
//            level.playSound(null, caster.blockPosition(), SoundEvents.PLAYER_ATTACK_SWEEP, caster.getSoundSource(), 1.0f, 1.0f);
//
//            if (!targets.isEmpty()) {
//                LivingEntity target = targets.get(0);
//                target.hurt(level.damageSources().mobAttack(caster), damage);
//
//                MagicManager.spawnParticles(level, ParticleHelper.BLOOD,
//                        target.getX(),
//                        target.getY() + target.getBbHeight() * 0.4,
//                        target.getZ(), 30,
//                        target.getBbWidth() * 0.5,
//                        target.getBbHeight() * 0.5,
//                        target.getBbWidth() * 0.5,
//                        0.03, false
//                );
//            }
//        }
//    }
//}
