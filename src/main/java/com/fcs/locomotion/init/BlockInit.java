package com.fcs.locomotion.init;

import com.fcs.locomotion.LocoMotionMain;
import com.fcs.locomotion.blocks.EndPortalBlock;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class BlockInit {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, LocoMotionMain.MOD_ID);

    public static final RegistryObject<Block> END_PORTAL_FRAME = BLOCKS.register("end_portal_frame",
            () -> new Block(Block.Properties.copy(Blocks.BEDROCK)));

    public static final RegistryObject<Block> END_PORTAL_BLOCK = BLOCKS.register("end_portal",
            () -> new EndPortalBlock(Block.Properties.copy(Blocks.NETHER_PORTAL)));

    @SubscribeEvent
    public static void onRegisterItems(final RegistryEvent.Register<Item> event){
        final IForgeRegistry<Item> registry = event.getRegistry();

        BLOCKS.getEntries().stream().map(RegistryObject::get).forEach((block -> {
            final Item.Properties properties = new Item.Properties().tab(ItemInit.ModCreativeTab.instance);
            final BlockItem blockItem = new BlockItem(block, properties);
            blockItem.setRegistryName(block.getRegistryName());
            registry.register(blockItem);
        }));
    }
}
