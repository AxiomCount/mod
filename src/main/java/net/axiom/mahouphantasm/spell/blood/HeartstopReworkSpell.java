//package net.axiom.mahouphantasm.spell.blood;
//
//import io.redspace.ironsspellbooks.api.config.DefaultConfig;
//import io.redspace.ironsspellbooks.api.magic.MagicData;
//import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
//import io.redspace.ironsspellbooks.api.spells.AbstractSpell;
//import io.redspace.ironsspellbooks.api.spells.AutoSpellConfig;
//import io.redspace.ironsspellbooks.api.spells.CastSource;
//import io.redspace.ironsspellbooks.api.spells.CastType;
//import io.redspace.ironsspellbooks.api.spells.SpellRarity;
//import io.redspace.ironsspellbooks.api.util.Utils;
//import io.redspace.ironsspellbooks.registries.MobEffectRegistry;
//import io.redspace.ironsspellbooks.registries.SoundRegistry;
//import java.util.List;
//import java.util.Optional;
//import net.minecraft.network.chat.Component;
//import net.minecraft.network.chat.MutableComponent;
//import net.minecraft.resources.ResourceLocation;
//import net.minecraft.sounds.SoundEvent;
//import net.minecraft.world.effect.MahouEffects;
//import net.minecraft.world.effect.MobEffectInstance;
//import net.minecraft.world.entity.LivingEntity;
//import net.minecraft.world.level.Level;
//
//@AutoSpellConfig
//public class HeartstopReworkSpell extends AbstractSpell {
//    private final ResourceLocation spellId = ResourceLocation.fromNamespaceAndPath("irons_spellbooks", "heartstop");
//    private final DefaultConfig defaultConfig;
//
//    public List<MutableComponent> getUniqueInfo(int spellLevel, LivingEntity caster) {
//        return List.of(Component.translatable("ui.irons_spellbooks.effect_length", new Object[]{Utils.timeFromTicks(this.getSpellPower(spellLevel, caster), 1)}));
//    }
//
//    public HeartstopReworkSpell() {
//        this.defaultConfig = (new DefaultConfig()).setMinRarity(SpellRarity.COMMON).setSchoolResource(SchoolRegistry.BLOOD_RESOURCE).setMaxLevel(10).setCooldownSeconds(120.0).build();
//        this.manaCostPerLevel = 10;
//        this.baseSpellPower = 300;
//        this.spellPowerPerLevel = 30;
//        this.castTime = 0;
//        this.baseManaCost = 50;
//    }
//
//    public CastType getCastType() {
//        return CastType.INSTANT;
//    }
//
//    public DefaultConfig getDefaultConfig() {
//        return this.defaultConfig;
//    }
//
//    public ResourceLocation getSpellResource() {
//        return this.spellId;
//    }
//
//    public Optional<SoundEvent> getCastStartSound() {
//        return Optional.empty();
//    }
//
//    public Optional<SoundEvent> getCastFinishSound() {
//        return Optional.of((SoundEvent)SoundRegistry.HEARTSTOP_CAST.get());
//    }
//
//    public void onCast(Level world, int spellLevel, LivingEntity entity, CastSource castSource, MagicData playerMagicData) {
//        entity.addEffect(new MobEffectInstance((MahouEffects)MobEffectRegistry.HEARTSTOP.get(), (int)this.getSpellPower(spellLevel, entity), 0, false, false, true));
//        super.onCast(world, spellLevel, entity, castSource, playerMagicData);
//    }
//}
