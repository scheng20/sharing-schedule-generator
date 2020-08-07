# Sharing Shedule Generator
A marketing tool designed to help effectively share events with different groups on social media.
Built using Java.

<img src="screenshots/EventSettings1.PNG" width="285" title="Empty Event Settings"> <img src="screenshots/GroupSettings1.PNG" width="285" title="Empty Group Settings"> <img src="screenshots/Schedule1.PNG" width="285" title="Empty Schedule Output">

<img src="screenshots/EventSettings2.PNG" width="285" title="Filled Event Settings"> <img src="screenshots/GroupSettings2.PNG" width="285" title="Filled Group Settings"> <img src="screenshots/Schedule2.PNG" width="285" title="Filled Schedule Output">

[Download This Program Here](https://www.dropbox.com/s/uq6aao92wstuu13/SharingScheduleGenerator.exe?dl=0)

## :star: Introduction 

This program is designed to be used as a marketing tool for advertising events on social media platforms such as Facebook. One of the ways to maximize the exposure of your event is to post (share) it in different Facebook groups (Ex. student groups such as UBC 2022 or UBC Science 2021). However, if you share your event too often within the same group, its group members may get annoyed and form a negative impression on your event. The goal of this program is to automatically create a sharing schedule, so you can avoid oversharing and effectively promote your event within different Facebook groups to ensure maximum exposure. 

## :pushpin: Features 
* Automatically generates a 7-day marketing schedule detailing which groups you should share your event with on which day.
    - Currently the program has a standard 7 days of marketing (prior to the event day). This time length is what we have found to be the most effective for our BizTech events.

* Customizable groups, allowing you add and delete your own groups.

* Built-in marketing strategies such as:
    - Automatically categorizes input groups into different demographics based on group name, faculty, and year level. 
    - Schedules sufficient gaps between sharing your event with the same demographic in order to avoid spamming and annoying the same people. 
    - Customizable target groups, allowing you to maximize your event's exposure with the people that your event is targeted towards. 
    - Schedules more postings (aka sharing) on days with historically more active users. 

* User-friendly interface and display.

## :question: How-To-Use
To use this program, simply download it [here](https://www.dropbox.com/s/uq6aao92wstuu13/SharingScheduleGenerator.exe?dl=0). Your computer may tell you that the file is dangerous but trust me it's not (I swear I'm not trying to hack your computer), I just haven't found a way to certify or sign my program to make it accepted by windows security yet. 

Most of the functions of this generator are quite self-explanatory, but if you are stuck or confused, here is a quick 3 step guideline on how to create your first sharing schedule!

**Step 1:** Create at least 3 groups under “Group Settings” OR directly enable one of the built-in presets* under “Event Settings”.

**Step 2:** Pick an event name, date, and 3 target groups under “Event Settings”. Target groups are groups that your event will be shared with the most (higher frequency). 

**Step 3:** Click “Generate Schedule” under “Event Settings” and voila! Your generated schedule can be viewed under “Schedule”.

## :bulb: Origins 
During my time as one of the marketing directors for UBC BizTech (the largest technology club at the University of British Columbia), one of our main event marketing strategies is to share our events to different Facebook groups. In order to make sure our events receive enough exposure without spamming and annoying its members, we usually create a sharing schedule, detailing which Facebook groups we will share our events to on which day. 

However, the creation of these schedules is a very tedious and boring task. After having to go through such a dull process myself, I noticed that a lot of the steps involved could be automated. Instead of building these schedules myself, I decided that I was going to build a program that generates these schedules automatically, with a built in marketing algorithm and all. And voila! The sharing schedule generator was born.

Without sacrificing the marketing strategies embedded inside these schedules (such as selecting different target groups, and sharing more on days where our Facebook users are most active), I was able to build a program that automatically generates a sharing schedule given a list of groups and an event date. 

Although the current program is very tailored towards UBC BizTech, I believe that other clubs or organizations are still be able to benefit from its use. The group settings feature allows custom groups to be added, and with just a few clicks, a sharing schedule is generated and you are ready to start sharing!

Additionally, there are several new features that I want to add in the future in order to enhance this program’s customizability and accessibility, so stay tuned!

## :wave: More Questions?
If you have any further questions or found any bugs to report, please feel free to contact me [here!](http://scheng.ca/#contact)

## :information_source: External Libraries
* [JCalendar](https://toedter.com/jcalendar/) by Kai Tödter
