package com.fcs.locomotion.util;

import com.mojang.authlib.GameProfile;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;

public abstract class DimensionalEntity extends Entity {
    public enum PortalType{
        Overworld, Sky, Nether, Amplified, End
    }

    public DimensionalEntity(EntityType<?> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public void handleInsidePortal(BlockPos pPos, PortalType type) {
        if (this.isOnPortalCooldown()) {
            this.setPortalCooldown();
        } else {
            if (!this.level.isClientSide && !pPos.equals(this.portalEntrancePos)) {
                this.portalEntrancePos = pPos.immutable();
            }

            this.isInsidePortal = true;
        }
    }

    @Override
    protected void handleNetherPortal() {
        if (this.level instanceof ServerLevel) {
            int i = this.getPortalWaitTime();
            ServerLevel serverlevel = (ServerLevel)this.level;
            if (this.isInsidePortal) {
                MinecraftServer minecraftserver = serverlevel.getServer();
                ResourceKey<Level> resourcekey = this.level.dimension() == Level.END ? Level.OVERWORLD : Level.END;
                ServerLevel serverlevel1 = minecraftserver.getLevel(resourcekey);
                if (serverlevel1 != null && minecraftserver.isNetherEnabled() && !this.isPassenger() && this.portalTime++ >= i) {
                    this.level.getProfiler().push("portal");
                    this.portalTime = i;
                    this.setPortalCooldown();
                    this.changeDimension(serverlevel1);
                    this.level.getProfiler().pop();
                }

                this.isInsidePortal = false;
            } else {
                if (this.portalTime > 0) {
                    this.portalTime -= 4;
                }

                if (this.portalTime < 0) {
                    this.portalTime = 0;
                }
            }

            this.processPortalCooldown();
        }
    }

    @Override
    public void baseTick() {

        super.baseTick();
    }
}
