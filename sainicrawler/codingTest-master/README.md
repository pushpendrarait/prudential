**Installtion/Build Steps**
    
1. Please clone Source code from git URL <code>prudential/sainicrawler</code>

+ Open Eclipse and do following steps
+ Window -> Preferences -> Run/Debug -> Console -> "Limit console output"<br/>
+ Uncheck the Checkbox - "It enables to see Big Logs on Console of Eclipse"
+ Import Maven Projects into Eclipse and do Maven Build
+ Run <code>Solution.java</code> with some url in Program Argument like "http://www.prudential.co.uk"
+ See Sample Output of Console - for Prudential URL in file - *Prudential_Co_Uk_TESTRUN.txt*

-------
**Please Refer Screenshot Files:**
- Eclipse_Console_Unlimited_Setting.png
- Run_Solution_Eclipse_Screenshot.png
-------

**Other Remarks**
- Added JUNIT Test Cases
- Added Validations for Input Argument
- Added Static Helper Utility
- We can change DEPTH_TRAVERSAL, which can lead to higher Coverage
-------
**Console Output**

- It Ignores URL having Domain outside original URL
- It Has NESTED_LEVEL = 3 (Depth Traversal)

-------

** Improvements **
- Instead of Logging to Console - We can cause each of the WebPage to write results in Separate TXT File
- We can use Multi-Threaded Approach for Worker Threads to run JSoup
 

-------
 
###Architecture Design showing Traversal Depth = 2
######HTMLPage1 = Level 0  -  JSOUP traverses _HTMLPage1_
######Link1/Link2/Link3 = Level 1  -  JSOUP traverses _Link1/Link2/Link3_
>HTMLPage1
* Link1-------------------Link1.1
        -------------------Link1.2
        -------------------Link1.3
 * Link2-------------------Link1.1
        -------------------Link2.2
        -------------------Link2.3
        -------------------Link2.4
        -------------------Link2.5
 * Link3-------------------Link3.1
        -------------------Link3.2
        -------------------Link3.3
        -------------------Link3.4 


   
