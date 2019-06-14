package schedule_generator;

import java.util.ArrayList;

public class Group 
{
	//Initialize Some Properties
	String name;
	String faculty;
	String year;
	String tier;
	
	int shareTimes = 0;
	
	//Constructor
	public Group (String Name, String Faculty, String Year, String Tier)
	{
		name = Name;
		faculty = Faculty;
		tier = Tier;
		year = Year;
		
		// MAX 2 TIMES SYSTEM:
		switch (tier)
		{
			case "T":
				shareTimes = 2;
				break;
			case "N":
				shareTimes = 1;
				break;
		}
	}
	
	public void resetShareTimes()
	{
		if (tier.equalsIgnoreCase("T"))
		{
			shareTimes = 2;
		}
		else
		{
			shareTimes = 1;
		}
	}
}
