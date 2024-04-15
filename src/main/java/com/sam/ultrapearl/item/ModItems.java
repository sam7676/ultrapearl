package com.sam.ultrapearl.item;

import com.sam.ultrapearl.UltraPearl;
import com.sam.ultrapearl.item.custom.ultrapearl;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, UltraPearl.MOD_ID);

    public static final RegistryObject<Item> ULTRAPEARL = ITEMS.register("ultra_pearl",
            () -> new ultrapearl(new Item.Properties().group(ItemGroup.TOOLS).maxStackSize(1)
            ));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
