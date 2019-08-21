package models;

import java.util.*;

public class GroupList 
{
    // ------------------------------- FIELDS --------------------------------
	private ArrayList<Group> all = new ArrayList<Group>();
	private ArrayList<Group> topTier = new ArrayList<Group>();
	private ArrayList<Group> midTier = new ArrayList<Group>();

    // ------------------------------ FUNCTIONS ------------------------------
	public void add(Group newGroup) {
		all.add(newGroup);
		
		switch (newGroup.getTier()) {
			case "T":
				topTier.add(newGroup);
				break;
			case "N":
				midTier.add(newGroup);
				break;
		}
	}
	
	public void compile() {
		all.clear();
		all.addAll(topTier);
		all.addAll(midTier);
	}
	
	public void deleteAll() {
		all.clear();
		topTier.clear();
		midTier.clear();
	}

    // ------------------------- GETTERS AND SETTERS -------------------------

    public ArrayList<Group> getAll() {
	    return all;
    }

    public ArrayList<Group> getTopTier() {
        return topTier;
    }

    public ArrayList<Group> getMidTier() {
        return midTier;
    }


}
