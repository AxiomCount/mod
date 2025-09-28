// -----------------------------------
//first spell realisation
// -----------------------------------

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



// -----------------------------------
//upstanding slash hitboxes
// -----------------------------------

//            // Hitbox visualisation
//            Vec3[] corners = new Vec3[8];
//            int idx = 0;
//            for (int dx : new int[]{-1, 1}) {
//                for (int dy : new int[]{-1, 1}) {
//                    for (int dz : new int[]{-1, 1}) {
//                        corners[idx++] = hitboxCenter
//                                .add(forward.scale(dx * LENGTH / 2.0))
//                                .add(right.scale(dz * WIDTH / 2.0))
//                                .add(up.scale(dy * HEIGHT / 2.0));
//                    }
//                }
//            }
//            for (Vec3 c : corners) {
//                MagicManager.spawnParticles(level, ParticleHelper.EMBERS,
//                        c.x, c.y, c.z,
//                        5, 0.01, 0.01, 0.01,
//                        0.0, false
//                );
//            }

//            // aabb visualisation
//            for (double x : new double[]{searchBox.minX, searchBox.maxX}) {
//                for (double y : new double[]{searchBox.minY, searchBox.maxY}) {
//                    for (double z : new double[]{searchBox.minZ, searchBox.maxZ}) {
//                        MagicManager.spawnParticles(level, ParticleHelper.FIERY_SPARKS,
//                                x, y, z,
//                                5,
//                                0.01, 0.01, 0.01,
//                                0.0, false
//                        );
//                    }
//                }
//            }