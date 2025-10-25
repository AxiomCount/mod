package net.axiom.mahouphantasm.spell.redmist;

import io.redspace.ironsspellbooks.api.magic.SpellSelectionManager;
import io.redspace.ironsspellbooks.api.spells.*;
import io.redspace.ironsspellbooks.api.config.DefaultConfig;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.util.AnimationHolder;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.capabilities.magic.MagicManager;
import io.redspace.ironsspellbooks.capabilities.magic.RecastInstance;
import io.redspace.ironsspellbooks.entity.spells.flame_strike.FlameStrike;
import io.redspace.ironsspellbooks.registries.MobEffectRegistry;
import io.redspace.ironsspellbooks.registries.SoundRegistry;
import io.redspace.ironsspellbooks.util.ParticleHelper;
import net.axiom.mahouphantasm.MahouPhantasm;
import net.axiom.mahouphantasm.entity.spells.redmist_slash.RedmistSlash;
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
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

@AutoSpellConfig
public class UpstandingSlashSpell extends AbstractSpell {
    private static final ResourceLocation SPELL_ID = new ResourceLocation(MahouPhantasm.MOD_ID, "upstanding_slash");

    public List<MutableComponent> getUniqueInfo(int spellLevel, LivingEntity caster) {
        float damage = getSpellPower(spellLevel, caster);
        return List.of(
                Component.translatable("ui.irons_spellbooks.damage", Utils.stringTruncation(damage, 2))
        );
    }

    private final DefaultConfig defaultConfig = new DefaultConfig()
            .setMinRarity(SpellRarity.RARE)
            .setSchoolResource(MahouSchools.REDMIST_RESOURCE)
            .setMaxLevel(3)
            .setCooldownSeconds(20)
            .build();

    public UpstandingSlashSpell() {
        this.manaCostPerLevel = 15;
        this.baseSpellPower = 20;
        this.spellPowerPerLevel = 6;
        this.castTime = 21;
        this.baseManaCost = 70;
    }

    @Override
    public SpellRarity getRarity(int spellLevel) {
        return switch (spellLevel) {
            case 1 -> SpellRarity.RARE;
            case 2 -> SpellRarity.EPIC;
            case 3 -> SpellRarity.LEGENDARY;
            default -> SpellRarity.COMMON;
        };
    }

    public boolean canBeCraftedBy(Player player) {
        return true;
    }

    public boolean allowLooting() {
        return false;
    }

    public DefaultConfig getDefaultConfig() {
        return defaultConfig;
    }

    public CastType getCastType() {
        return CastType.LONG;
    }

//    @Override
//    public int getEffectiveCastTime(int spellLevel, @Nullable LivingEntity entity) {
//        int base = getCastTime(spellLevel);
//
//        if (entity != null && entity.hasEffect(MahouEffects.MANIFESTED.get())) {
//            // each level is reducing 20%
//            int amplifier = entity.getEffect(MahouEffects.MANIFESTED.get()).getAmplifier();
//            double multiplier = 1.0 - (0.2 * (amplifier + 1));
//            return (int) Math.max(1, base * multiplier); // Not 0
//        }
//
//        return base;
//    }

    public ResourceLocation getSpellResource() {
        return SPELL_ID;
    }

    //    change it to MahouEffects.MANIFESTED later
    public boolean canBeInterrupted(@Nullable Player player) {
        return player == null || !player.hasEffect(MobEffectRegistry.FORTIFY.get());
    }

    public int getRecastCount(int spellLevel, @Nullable LivingEntity entity) { return 2; }

    public Optional<SoundEvent> getCastStartSound() {
        return Optional.of(MahouSounds.DICE.get());
    }

    public Optional<SoundEvent> getCastFinishSound() {
        return Optional.of(SoundRegistry.BLOOD_EXPLOSION.get());
    }

    public AnimationHolder getCastStartAnimation() {
        return MSpellAnimations.MIMICRY_UPSWING;  // SpellAnimations.OVERHEAD_MELEE_SWING_ANIMATION;
    }

    public AnimationHolder getCastFinishAnimation() {
        return MSpellAnimations.MIMICRY_SLASH;
    }

    public void onCast(Level level, int spellLevel, LivingEntity caster, CastSource castSource, MagicData playerMagicData) {
        if (!playerMagicData.getPlayerRecasts().hasRecastForSpell(getSpellId()) & playerMagicData.getMana() > this.baseManaCost + (this.manaCostPerLevel * (spellLevel - 1)))
        {
            playerMagicData.getPlayerRecasts().addRecast
                    (new RecastInstance(getSpellId(), spellLevel, getRecastCount(spellLevel, caster),
                            60, castSource, null), playerMagicData);

        }

        final double LENGTH = 4.0;
        final double WIDTH = 1.5;
        final double HEIGHT = 2.5;
        float damage = getSpellPower(spellLevel, caster);
        Vec3 casterMid = caster.position().add(0, caster.getBbHeight() * 0.5, 0);
        Vec3 forward = caster.getLookAngle().normalize();
        Vec3 hitboxCenter = casterMid.add(forward.scale(LENGTH / 2.0));

        Vec3 worldUp = new Vec3(0, 1, 0);
        Vec3 right = forward.cross(worldUp);
        // if looking precisely up or down use X vec
        if (right.lengthSqr() < 1e-6) {
            right = forward.cross(new Vec3(1, 0, 0));
        }
        right = right.normalize();
        Vec3 up = right.cross(forward).normalize();

        AABB searchBox = caster.getBoundingBox().inflate(LENGTH*2, HEIGHT*2, LENGTH*2);

        List<LivingEntity> candidates = level.getEntitiesOfClass(
                LivingEntity.class,
                searchBox,
                e -> e != caster && e.isAlive() && e.isPickable()
        );

        for (LivingEntity e : candidates) {
            // (e.getBbHeight() * 0.5)  is a target Y center
            Vec3 targetPos = e.position().add(0, e.getBbHeight() * 0.5, 0);

            Vec3 rel = targetPos.subtract(hitboxCenter);      // vector from hitbox center to target
            double f = rel.dot(forward);                // forward projection
            double r = rel.dot(right);                  // right projection
            double u = rel.dot(up);                     // up projection

            // is it exactly in the hitbox
            if (Math.abs(f) <= LENGTH / 2.0 &&
                    Math.abs(r) <= WIDTH / 2.0 &&
                    Math.abs(u) <= HEIGHT / 2.0) {

                // if in hitbox
                e.hurt(level.damageSources().mobAttack(caster), damage);
                MagicManager.spawnParticles(level, ParticleHelper.BLOOD,
                        e.getX(),
                        e.getY() + e.getBbHeight() * 0.5,
                        e.getZ(), 50,
                        e.getBbWidth() * 0.5,
                        e.getBbHeight() * 0.5,
                        e.getBbWidth() * 0.5,
                        0.03, false
                );
            }
        }
        boolean mirrored = playerMagicData.getCastingEquipmentSlot().equals(SpellSelectionManager.OFFHAND);
        boolean vertical = true;
        RedmistSlash slash = new RedmistSlash(level, mirrored, vertical);
        slash.moveTo(hitboxCenter.x, hitboxCenter.y, hitboxCenter.z);
        slash.setYRot(caster.getYRot());
        slash.setXRot(caster.getXRot());
        level.addFreshEntity(slash);

        super.onCast(level, spellLevel, caster, castSource, playerMagicData);
    }
}


