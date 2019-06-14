package schedule_generator;

import java.util.*;

public class GroupList 
{
	ArrayList<Group> all = new ArrayList<Group>();
	ArrayList<Group> topTier = new ArrayList<Group>();
	ArrayList<Group> midTier = new ArrayList<Group>();
	
	public GroupList()
	{
		
	}
	
	public void add(Group newGroup)
	{
		all.add(newGroup);
		
		switch (newGroup.tier)
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
