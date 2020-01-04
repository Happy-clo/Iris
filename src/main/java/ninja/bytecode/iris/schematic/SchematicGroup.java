package ninja.bytecode.iris.schematic;

import ninja.bytecode.iris.util.Direction;
import ninja.bytecode.shuriken.collections.GList;

public class SchematicGroup
{
	private GList<Schematic> schematics;
	private GList<String> flags;
	private String name;
	private int priority;

	public SchematicGroup(String name)
	{
		this.schematics = new GList<>();
		this.flags = new GList<>();
		priority = 0;
		this.name = name;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public GList<Schematic> getSchematics()
	{
		return schematics;
	}

	public void setSchematics(GList<Schematic> schematics)
	{
		this.schematics = schematics;
	}

	public GList<String> getFlags()
	{
		return flags;
	}

	public void setFlags(GList<String> flags)
	{
		this.flags = flags;
	}

	public int getPriority()
	{
		return priority;
	}

	public void setPriority(int priority)
	{
		this.priority = priority;
	}

	public int size()
	{
		return getSchematics().size();
	}
	
	public void generateRotationVariants()
	{
		if(flags.contains("rotation=none"))
		{
			return;
		}
		
		GList<Schematic> variants = new GList<>();
		GList<Direction> directions = flags.contains("rotation=all") ? Direction.udnews() : Direction.news();
		directions.remove(Direction.N);
		
		for(Schematic i : getSchematics())
		{
			for(Direction j : directions)
			{
				Schematic rotated = i.copy();
				rotated.rotate(Direction.N, j);
				variants.add(rotated);
			}
		}
		
		getSchematics().add(variants);
	}
}
