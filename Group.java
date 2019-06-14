package schedule_generator;

import java.util.ArrayList;

public class Group 
{
	//Initialize Some Properties
	String name;
	String faculty;
	String year;
	String tier;
	//ArrayList<String> people = new ArrayList<String>();
	
	int shareTimes = 0;
	//int daysRemainingUntilNextShare = 0;
	
	//Constructor
	public Group (String Name, String Faculty, String Year, String Tier)
	{
		name = Name;
		faculty = Faculty;
		tier = Tier;
		year = Year;
		//people = People;
		
		// STEP 3:
		// Determine the amount of times this groups needs to be shared based on its tier
		
		// MAX 3 TIMES SYSTEM:
		/*
		switch (tier)
		{
			case "A":
				shareTimes = 3;
				break;
			case "B":
				shareTimes = 2;
				break;
			case "C":
				shareTimes = 1;
				break;
		}*/
		
		// MAX 2 TIMES SYSTEM:
		switch (tier)
		{
			case "T":
				shareTimes = 2;
				break;
			case "N":
				shareTimes = 1;
				break;
			/*
			case "C":
				shareTimes = 1;
				break; */
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
	
	/*
	public void update()
	{
		if (daysRemainingUntilNextShare > 0)
		{
			daysRemainingUntilNextShare--;
		}
		else
		{
			daysRemainingUntilNextShare = 0;
		}
		
	}*/
	
}
