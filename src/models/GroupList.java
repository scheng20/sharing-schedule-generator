package models;

import java.util.*;

public class GroupList 
{
	public ArrayList<Group> all = new ArrayList<Group>();
	public ArrayList<Group> topTier = new ArrayList<Group>();
	public ArrayList<Group> midTier = new ArrayList<Group>();
	
	public GroupList()
	{
		
	}
	
	public void add(Group newGroup)
	{
		all.add(newGroup);
		
		switch (newGroup.getTier())
		{
			case "T":
				topTier.add(newGroup);
				break;
			case "N":
				midTier.add(newGroup);
				break;
		}
	}
	
	public void compile()
	{
		all.clear();
		all.addAll(topTier);
		all.addAll(midTier);
	}
	
	public void deleteAll()
	{
		all.clear();
		topTier.clear();
		midTier.clear();
	}
	
}