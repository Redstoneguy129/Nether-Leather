package me.cameronwhyte.mods.netherleather;

import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.monster.ZombiePigmanEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.Tags;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;

@Mod("netherleather")
public class NetherLeather {
    private static final String MODID = "netherleather";
    private static final Logger LOGGER = LogManager.getLogger();

    public NetherLeather() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {
        LOGGER.info(MODID+" is setup!");
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        LOGGER.info(MODID+" client is setup!");
    }

    @SubscribeEvent
    public void addLoot(LivingDeathEvent event) {
        if(!(event.getEntity() instanceof ZombiePigmanEntity)) return;
        Random random = new Random();
        if((random.nextInt(4 - 1 + 1) + 1) != 1) return;
        World world = event.getEntity().getEntityWorld();
        BlockPos pos = event.getEntity().getPosition();
        int amount = random.nextInt(3 - 1 + 1) + 1;
        for(int i=0; i <= amount-1;) {
            ItemEntity leather = new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(Items.LEATHER));
            world.addEntity(leather);
            i+=1;
        }
    }

}
