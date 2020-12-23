# Capstone project - Tech Talent South
Twitter interface utilizing the [Twitter4j](http://twitter4j.org/en/index.html) library to build a  "Guess the Tweeter" game where user tries to match Tweets with profile names/pictures based on the search query of the User's choosing.   


## Motivation
To put together 8 weeks of material into one final project utilizing an unknown API, RESTful and MVC design concepts, Spring framework and an HTML/CSS based frontend utilzing Thymeleaf. 


## Tools used
[Thymeleaf](https://www.thymeleaf.org/) Java template engine.  
[Spring + Spring Boot](https://spring.io/projects/spring-boot) framework.

## Screenshots
Below is the initial screen after inputting a query. You are presented with 5 users and 5 randomized tweets numbered 1-5 for you to match to the users.   
<img src="https://github.com/WGlaser/TTSTwitterCapstone/blob/master/Readme1.jpg" width=50% height=50%>. 

After clicking "See how you did", the code will check the shuffled tweet numbers vs the actual tweet numbers, and tally your score. The tweets will also rearrange below their respective tweeters and color code your responses.  

<img src="https://github.com/WGlaser/TTSTwitterCapstone/blob/master/Readme2.jpg" width=50% height=50%>

## Future updates
- Fix HTML to adjust properly with varying window sizes/devices
- Include a difficulty setting to adjust number of users/tweets to match
- Add a "Tweet my results!" button to tweet from user's account.


