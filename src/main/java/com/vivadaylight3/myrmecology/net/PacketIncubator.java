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

    public PacketIncubator(final int x, final int y, final int z,
	    final int message) {
	this.x = x;
	this.y = y;
	this.z = z;
	this.message = message;
    }

    public void fromBytes(final ByteBuf buf) {
	x = buf.readInt();
	y = buf.readInt();
	z = buf.readInt();
	message = buf.readInt();
    }

    public IMessage onMessage(final PacketIncubator message,
	    final MessageContext ctx) {
	final TileEntity tile = ctx.getServerHandler().playerEntity.worldObj
		.getTileEntity(message.x, message.y, message.z);
	if (tile == null) return null;
	if (tile instanceof TileEntityIncubator) {
	    ((TileEntityIncubator) tile).updateProductType(message.message);
	    ctx.getServerHandler().playerEntity.worldObj.markBlockForUpdate(
		    message.x, message.y, message.z);
	}
	return null;
    }

    public void toBytes(final ByteBuf buf) {
	buf.writeInt(x);
	buf.writeInt(y);
	buf.writeInt(z);
	buf.writeInt(message);
    }

}
