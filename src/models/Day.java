package models;

import java.time.LocalDate;
import java.util.*;

public class Day 
{
    // ------------------------------- FIELDS --------------------------------
	private LocalDate date;
	private String dayofWeek;
	private Boolean hotDay = false;
	private int shareSlots = 0;

	private List<String> sharedGroups = new ArrayList<String>();
	private List<String> sharedFaculties = new ArrayList<String>();
	private List<String> sharedYears = new ArrayList<String>();

	private List<Group> addedGroups = new ArrayList<Group>();

    // ----------------------------- CONSTRUCTOR -----------------------------
	public Day (LocalDate Date, Boolean Hot, int Slots)
	{
		date = Date;
		hotDay = Hot;
		shareSlots = Slots;
		dayofWeek = Date.getDayOfWeek().name();
	}

    // ------------------------------ FUNCTIONS ------------------------------
	public void addGroup(Group group)
	{

		addedGroups.add(group);
		sharedGroups.add(group.getName());
		sharedFaculties.add(group.getFaculty());
		sharedYears.add(group.getYear());
		
		// Decrease the amount of shares left for this day
		shareSlots --;
		group.shareTimes --;
		
	}
	
	public boolean canAdd(Group group)
	{
		// Count how many appearances of this group's faculty are in this day
        int sameFaculty = countAppearances(group.getFaculty(), sharedFaculties);
		
		// Count how many appearances of this group's year are in this day
		int sameYear = countAppearances(group.getYear(), sharedYears);
		
		return (shareSlots > 0 && sameFaculty < 2 && sameYear < 1 && group.shareTimes > 0);
	}

	public int countAppearances(String target, List<String> list) {

	    int sameCount = 0;

        for (String current: list)
        {
            if (current.equalsIgnoreCase(target))
            {
                sameCount ++;
            }
        }

	    return sameCount;
    }

    public void deleteAllGroups()
    {
        sharedGroups.clear();
        sharedFaculties.clear();
        sharedYears.clear();
        addedGroups.clear();
    }

    // ------------------------- GETTERS AND SETTERS -------------------------

    public List<String> getSharedGroups() {
	    return sharedGroups;
    }

    public int getShareSlots() {
	    return shareSlots;
    }

    public boolean isHotDay() {
	    return hotDay;
    }

    public LocalDate getDate() {
	    return date;
    }
}
