package com.vivadaylight3.myrmecology.net;

import io.netty.buffer.ByteBuf;
import net.minecraft.tileentity.TileEntity;

import com.vivadaylight3.myrmecology.tileentity.TileEntityIncubator;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class PacketIncubator implements IMessage,
		IMessageHandler<PacketIncubator, IMessage> {

	int x, y, z, message;

	public PacketIncubator() {
		// TODO Auto-generated constructor stub
	}

	public PacketIncubator(int x, int y, int z, int message) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.message = message;
	}

	public void fromBytes(ByteBuf buf) {
		x = buf.readInt();
		y = buf.readInt();
		z = buf.readInt();
		message = buf.readInt();
	}

	public void toBytes(ByteBuf buf) {
		buf.writeInt(x);
		buf.writeInt(y);
		buf.writeInt(z);
		buf.writeInt(message);
	}

	public IMessage onMessage(PacketIncubator message, MessageContext ctx) {
		TileEntity tile = ctx.getServerHandler().playerEntity.worldObj
				.getTileEntity(message.x, message.y, message.z);
		if (tile == null)
			return null;
		if (tile instanceof TileEntityIncubator) {
			((TileEntityIncubator) tile).updateProductType(message.message);
			ctx.getServerHandler().playerEntity.worldObj.markBlockForUpdate(
					message.x, message.y, message.z);
		}
		return null;
	}

}
