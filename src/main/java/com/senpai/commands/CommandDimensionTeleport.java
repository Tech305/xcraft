package com.senpai.commands;

import java.util.List;

import com.google.common.collect.Lists;
import com.senpai.util.Reference;
import com.senpai.util.Teleport;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

public class CommandDimensionTeleport extends CommandBase
{

	private final List<String> aliases = Lists.newArrayList(Reference.MODID, "tp", "tpdim", "tpdimension", "teleportdimension", "teleport");
	
	@Override
	public String getName() {
		return "tpdimension";
	}

	@Override
	public String getUsage(ICommandSender sender) {
		return "tpdimension <id>";
	}

	@Override
	public List<String> getAliases (){
		return aliases;
	}
	
	@Override
	public boolean checkPermission (MinecraftServer server, ICommandSender sender)
	{
		return true;
	}

	
	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		if(args.length < 1) return;
		String s = args[0];
		int dimensionID;
		try
		{
			dimensionID = Integer.parseInt(s);
		} catch(NumberFormatException nfe)
		{
			sender.sendMessage(new TextComponentString(TextFormatting.RED + "Dimension ID Invalid"));
			return;
		}
		
		if(sender instanceof EntityPlayer)
		{
			if(dimensionID == 1) 
				Teleport.teleportToDimension((EntityPlayer)sender, dimensionID, 0, 64, 2);
			else if(dimensionID == Reference.FozDimensionID)		
				Teleport.teleportToDimension((EntityPlayer)sender, Reference.FozDimensionID, 7, 64, 7);		
			else 
				Teleport.teleportToDimension((EntityPlayer)sender, dimensionID, sender.getPosition().getX(), sender.getPosition().getY(), sender.getPosition().getZ());
		}
		
	}

}
	