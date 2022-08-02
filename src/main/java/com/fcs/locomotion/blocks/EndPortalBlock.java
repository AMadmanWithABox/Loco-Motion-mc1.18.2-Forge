package com.fcs.locomotion.blocks;


import com.fcs.locomotion.util.DimensionalEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.NetherPortalBlock;
import net.minecraft.world.level.block.state.BlockState;

public class EndPortalBlock extends NetherPortalBlock {
    public EndPortalBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        if (!entity.isPassenger() && !entity.isVehicle() && entity.canChangeDimensions()) {
            DimensionalEntity dimensional = (DimensionalEntity) entity;
            dimensional.handleInsidePortal(pos, DimensionalEntity.PortalType.End);
        }
    }


}
