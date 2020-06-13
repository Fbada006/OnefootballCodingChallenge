# Android Take Home Test

The purpose of this test is to evaluate your technical skills, communication and mindset.

You should exemplify the :muscle: best work you can do with the provided example and perhaps :boom: surprise us with your solution. You have *one* week time to work on the test, but our expectations are that a fraction of the time is needed for the test.

You are expected to refactor and improve the project to avoid the mistakes it has.

## Procedure

1. After completing the challenge, clean the project (builds, .gradle, etc). But remember: The project should still be runnable.
2. Compress project file as a ".zip" file.
3. Send the `zip` file back to the person you have been in contact with (from the recruiting team).
4. You will receive feedback for this project file which you sent us during the live interview.

Notes :
   - Meaningful commit history (git) will be a plus.
   - Feel free to add some comments or provide information in `README.md` file about things you would like to improve but didn't have time for this.
   - The project zip file size should't be more 15 MB.

## Project

In this project, you will have a news feed activity. It retrieves the data from the local JSON file.
JSON is parsed manually and elements has been created from the parsed data. 

All the logical operations are happening in `MyNewsActivity.kt` and the layout for the list items is 
stored in `news_item.xml` file. There is only one data class for `News` element and it's in `model` folder.

The Application is written in Kotlin.

You are free to suggest and implement any library or code snippet that you think that is going to be useful 
for the codebase. But please be sure to add detailed information about your suggestions and why they would be useful. 

## Questions

Feel free to reach out to us if you have any issues or questions. You may contact the person that sent the 
code challenge to you.

![](https://media.giphy.com/media/uADx98ByhpOwcE7KhW/giphy.gif)

:rocket: Have fun!

## Refactor process
This project had multiple issues so I followed the following steps to make it better:
1. Move the logic for parsing the JSON to the repository. The actual parsing happens in a background
 thread using coroutines
2. Introduce ViewModel and LiveData to make sure data survives configuration changes while the latter
presents us reactive programming for easily updating the UI using the Observer pattern
3. Use Dagger to inject dependencies easily. The news Hilt library is promising but I am yet to
explore that
4. Add tests to the app
5. Fix the failing CI checks by adding an extra step. These GitHub Actions are just awesome.
6. Improve the aesthetics with stuff like loading spinners, rounded cards, and dark mode.
7. Use DataBinding and ViewBinding to keep .kts clear
8. Organize the build.gradle file (I think I could have gone for a Kotlin DSL but the project does not
have that many dependencies so my approach is alright)
9. Improve code documentation in general
10. I would have loved to use the github api and parse the JSON from my repo but I felt that may be
overkill. Why get the same data from the internet yet you have it offline already. However, this would
have showcased my skills wit Retrofit and Coroutines even further. It would have necessitated the need
for room as well so ultimately overkill. However, getting live news from the API is better
11. I wanted to break it down into modules but like 9, I felt it was overkill.
12. If I could, I would add timestamps to the news. This would provide a better user experience because
the settings could be updated to factor this in
13. I also suggest adding videos to these news to play on the app to make for a more immersive
experience

Note: I have been working on this code on my GitHub repo. However, the code is private and I would
not like to make it public without your explicit permission. To circumvent this, I have created a
new github user who has access to the repo.\
        username: guruMasterTest\
        password: onefootballcode\

\My official GitHub profile is https://github.com/Fbada006

Thanks!

