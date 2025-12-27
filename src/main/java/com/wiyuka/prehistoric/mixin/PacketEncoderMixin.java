package com.wiyuka.prehistoric.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.wiyuka.prehistoric.memory.LeakSensitiveAllocatorHelper;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.network.PacketEncoder;
import net.minecraft.network.PacketListener;
import net.minecraft.network.protocol.Packet;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(PacketEncoder.class)
public class PacketEncoderMixin<T extends PacketListener> {

    @WrapMethod(method = "encode(Lio/netty/channel/ChannelHandlerContext;Lnet/minecraft/network/protocol/Packet;Lio/netty/buffer/ByteBuf;)V")
    private void modifyArg(ChannelHandlerContext p_130545_, Packet<T> p_130546_, ByteBuf p_130547_, Operation<Void> original) {
        // Use a safer buffer on encoding since the direct buffer are fix sized.
        original.call(p_130545_, p_130546_, LeakSensitiveAllocatorHelper.allocateBuffer(p_130547_));
    }
}
