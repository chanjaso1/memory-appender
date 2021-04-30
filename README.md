### a. Any special Instructions if needed  
When cloning the repository, the project should be added as a maven project when it is prompted.

#### It is assumed that VisualVM is already installed with the standalone and in Intellij
1. Open VisualVM
2. Run the main method for LogRunner.java with VisualVM
3. Switch to VisualVM
4. Click on ``nz.ac.wgtn.swen301.assignment2.example.LogRunner``
5. Open the MemAppenderMBean directory
6. Click on MemAppender 

The attributes should be visible and are updated when the refresh button is clicked. 
The ExportToJSON function is in the "Operations" tab, where the methods are located.


### b. A discussion why you chose a particular JSON library. Base your decision on your experience (if any), documentation, technical aspects (e.g. performance as shown in the stress tests, stability, number and size of direct and indirect dependencies), and social aspects (size and activity of developer community, license, support like mailing lists and stackoverflow topics, usage by others, … )  [3 marks]
I chose the Gson library. Gson was the preferred library as it is open source and well documented.

###### Performance
Gson provides advantages over libraries such as its simplicity of toJson/fromJson and its deserialization, as it 
does not require java entities[1]. Gson provides complete support to java generics. 
Compared to other libraries such as Jackson and JSONP, Gson performs much better when parsing smaller files at 1`KB. 
However, Gson falls behind in performance as the parsing speed for larger files, at 190 MB [2]. In this case, Gson is
preferred because the exported files are relatively small. Gson also deals well with micro-services such as formatting strings.
 

###### Usage
Such advantages Gson provides correlate with its popularity. According to AppBrain, a statistics website based on the Android ecosystem,
Gson comes at #1 on both data serialization and open-source libraries in terms of popularity [3,4]. Gson is used in over 242,000 apps, with a market share of
29.58%. On the other hand, Jackson is used in over 3,000 apps with a market share of 0.39% in apps. In stack overflow, there are over 48,000 results when searching "Gson."

###### License  
Gson uses the Apache License 2.0 which permits commercial and private use.

###### Experience
I had previous experience working with Gson during SWEN225, where I created JsonArrays and used reflection to convert Json
strings into classes. In my project, I worked in a persistence module where the primary library I worked with was Gson.
Since before starting this assignment, I had already known how to read, write and parse a json file.

References:  
[1] https://www.baeldung.com/jackson-vs-gson  
[2] https://www.overops.com/blog/the-ultimate-json-library-json-simple-vs-gson-vs-jackson-vs-json/  
[3] https://www.appbrain.com/stats/libraries/details/google_gson/google-gson 
[4] https://www.appbrain.com/info/about  
[5] https://www.appbrain.com/stats/libraries/details/jackson/jackson-json-processor  
[5] https://stackoverflow.com/search?q=gson

