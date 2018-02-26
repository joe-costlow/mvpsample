# mvpsample
Simple application utilizing MVP architecture

MVPSample is my first attempt to utilize MVP architecture in an application. It will be a test application for new features, while using MVP architecture.

#### 2018-Feb-26
At this point, the application utilizes Retrofit 2 to make a call to the GitHub API. The response from the call is used to load the repositories of searched user.

The button on the first screen has changed from ADD to SEARCH.

#### 2018-Jan-31
At this point, the application has two screens:

The first screen has an area to name text, a button (ADD), an area that displays the inputted text, and a list of inputs added.

The second screen displays the text of the item selected from the list of inputs from the previous screen.

> The list data is not persisted through orientation changes or application launches, but is persisted when returning from the second screen to the first screen.

![screenshot_1517426510_resize25](https://user-images.githubusercontent.com/28271026/35643362-10ae367c-0694-11e8-807b-0b5a1d6e49ff.png) 
![screenshot_1517427797-resize25](https://user-images.githubusercontent.com/28271026/35643767-423520d8-0695-11e8-9cbe-810b0b6c423b.png)
