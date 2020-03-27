package me.cameronwhyte.mods.netherleather.mixins;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.PiglinEntity;
import net.minecraft.entity.mob.ZombifiedPiglinEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

import java.util.Random;

@Mixin({ZombifiedPiglinEntity.class, PiglinEntity.class})
public class PiglinMixin extends HostileEntity {

    private boolean debounce = false;
    private Random random = new Random();

    protected PiglinMixin(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    public void tickMovement() {
        if(!debounce) {
            if(!isAlive()) {
                debounce = true;
                World world = this.world;
                BlockPos pos = this.getBlockPos();
                if(random.nextInt((4 - 1 + 1) + 1) == 1) {
                    int amount = random.nextInt(3 - 1 + 1) + 1;
                    for(int i=0; i <= amount-1;) {
                        ItemEntity leather = new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(Items.LEATHER));
                        world.spawnEntity(leather);
                        i+=1;
                    }
                }
                new Thread(() -> {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException ignored) {}
                    debounce = false;
                }).start();
            }
        }
        super.tickMovement();
    }
}
