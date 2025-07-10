package miyucomics.sparkle.mixin;

import miyucomics.sparkle.Sparkle;
import miyucomics.sparkle.SparkleConfig;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Block.class)
public class BlockMixin {
	@Inject(method = "randomDisplayTick", at = @At("HEAD"))
	private void addParticles(BlockState state, World world, BlockPos pos, Random random, CallbackInfo ci) {
		if (!world.isClient())
			return;
		if (random.nextFloat() < 0.2 && SparkleConfig.SPARKLY_BLOCKS.contains(state.getBlock())) {
			Direction direction = Direction.random(random);
			Direction.Axis axis = direction.getAxis();
			double x = axis == Direction.Axis.X ? 0.5 + 0.5625 * (double) direction.getOffsetX() : (double) random.nextFloat();
			double y = axis == Direction.Axis.Y ? 0.5 + 0.5625 * (double) direction.getOffsetY() : (double) random.nextFloat();
			double z = axis == Direction.Axis.Z ? 0.5 + 0.5625 * (double) direction.getOffsetZ() : (double) random.nextFloat();
			world.addParticle(Sparkle.SPARKLE_PARTICLE, (double) pos.getX() + x, (double) pos.getY() + y, (double) pos.getZ() + z, 0, 0, 0);
		}
	}
}