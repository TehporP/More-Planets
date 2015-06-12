/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.plugin.asm;

import java.io.IOException;
import java.util.Iterator;

import net.minecraft.launchwrapper.IClassTransformer;
import net.minecraft.launchwrapper.Launch;

import org.apache.logging.log4j.LogManager;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.VarInsnNode;

public class MorePlanetsTransformer implements IClassTransformer
{
	private boolean foundOnce = false;
	private static boolean obfuscated;
	private static String iBlockStateName;
	private static String blockModelShapesName;
	private static String getTextureName;
	private static String textureAtlasSpriteName;
	private static String textureHelperName;

	static
	{
		boolean obfuscated = true;

		try
		{
			obfuscated = Launch.classLoader.getClassBytes("net.minecraft.world.World") == null;
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		MorePlanetsTransformer.obfuscated = obfuscated;
		iBlockStateName = obfuscated ? "bec" : "net/minecraft/block/state/IBlockState";
		blockModelShapesName = obfuscated ? "clc" : "net/minecraft/client/renderer/BlockModelShapes";
		getTextureName = obfuscated ? "a" : "getTexture";
		textureAtlasSpriteName = obfuscated ? "cue" : "net/minecraft/client/renderer/texture/TextureAtlasSprite";
		textureHelperName = "stevekung/mods/moreplanets/plugin/asm/BlockTextureHelper";
	}

	@Override
	public byte[] transform(String name, String transformedName, byte[] clazz)
	{
		if (name.equals(blockModelShapesName.replace("/", ".")))
		{
			LogManager.getLogger().info("Patching features into game...");
			ClassNode classNode = this.getClassNode(clazz);
			MethodNode renderMethodNode = this.getMethodNode(classNode, getTextureName, "(L" + iBlockStateName + ";)L" + textureAtlasSpriteName + ";");
			InsnList instructions = new InsnList();
			instructions.add(new VarInsnNode(Opcodes.ALOAD, 1));
			instructions.add(new MethodInsnNode(Opcodes.INVOKESTATIC, textureHelperName, "getTexture", "(L" + iBlockStateName + ";)L" + textureAtlasSpriteName + ";", false));
			instructions.add(new InsnNode(Opcodes.ARETURN));
			renderMethodNode.instructions.insertBefore(this.getInsertionPoint(renderMethodNode, "getTexture()"), instructions);
			LogManager.getLogger().info(MorePlanetsTransformer.obfuscated ? "Successfully patched clc" : "Successfully patched " + blockModelShapesName);
			return this.getBytes(classNode);
		}
		return clazz;
	}

	private AbstractInsnNode getInsertionPoint(MethodNode methodNode, String name)
	{
		Iterator<AbstractInsnNode> iterator = methodNode.instructions.iterator();
		AbstractInsnNode returnNode = null;

		while (iterator.hasNext())
		{
			AbstractInsnNode currentNode = iterator.next();

			if (currentNode.getOpcode() == Opcodes.INVOKEINTERFACE && !this.foundOnce)
			{
				this.foundOnce = true;
			}
			if (currentNode.getOpcode() == Opcodes.INVOKEINTERFACE && this.foundOnce)
			{
				returnNode = currentNode.getPrevious();
			}
		}
		if (returnNode != null)
		{
			return returnNode;
		}
		throw new RuntimeException("Couldn't find the insertion point for " + name);
	}

	private ClassNode getClassNode(byte[] classBytes)
	{
		ClassReader reader = new ClassReader(classBytes);
		ClassNode node = new ClassNode();
		reader.accept(node, 0);
		return node;
	}

	private byte[] getBytes(ClassNode classNode)
	{
		ClassWriter writer = new ClassWriter(0);
		classNode.accept(writer);
		return writer.toByteArray();
	}

	private MethodNode getMethodNode(ClassNode classNode, String methodName, String desc)
	{
		for (MethodNode method : classNode.methods)
		{
			if (method.name.equals(methodName) && method.desc.equals(desc))
			{
				return method;
			}
		}
		throw new RuntimeException(methodName + " doesn't exist in " + classNode.name + "!");
	}
}