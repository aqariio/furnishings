package aqario.furnishings.mixin;

import net.minecraft.screen.ScreenHandlerSyncHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(ServerPlayerEntity.class)
public interface ServerPlayerEntityAccessor {
	@Accessor
	int getScreenHandlerSyncId();

	@Accessor
	ScreenHandlerSyncHandler getScreenHandlerSyncHandler();

	@Invoker
	void callIncrementScreenHandlerSyncId();
}
