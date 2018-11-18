CoyoApp!
Project for Coyo code challenge.

Main architecture decisions
 - MVP architecture pattern, because is which I have more experience, and it is which I know the best
 - Two simple activities accomplish with all requirements noted in GitHub file
 - Dependency injections with Dagger
 
List of third-party libraries

    Unit testing
        Mockito: for mocking all neccesary objects in presenter's testing classes
    
    Instrumental testing
        Espresso
        Cucumber
        UiAutomator
    
    RxAndroid: for working with reactive programming
    
    Retrofit: for configure API web service request
    
    Dagger: dependency injection
    
    Design support -> recyclerview
   
    Room: for persistence
    
Things to be improved
    Mainly the instrumental test class, which should be more complete, with more scenarios
    
This app accomplish with all requirements, but for persistence criteria, I have implemented
a very simple logic, which decides whether call to DB or API, depending on the time of the last request.
If this is older than 5 minutes, API is called, otherwise, DB is requested.  