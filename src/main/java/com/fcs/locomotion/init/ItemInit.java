package com.fcs.locomotion.init;

import com.fcs.locomotion.LocoMotionMain;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemInit {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, LocoMotionMain.MOD_ID);


    //items go here



    public static class ModCreativeTab extends CreativeModeTab{
        public static final ModCreativeTab instance = new ModCreativeTab(CreativeModeTab.TABS.length, LocoMotionMain.MOD_ID);
        private ModCreativeTab(int index, String label) {
            super(index, label);
        }

        @Override
        public ItemStack makeIcon() {
            return new ItemStack(Blocks.BEDROCK);
        }
    }
}
