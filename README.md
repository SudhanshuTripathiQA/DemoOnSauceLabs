############################################# Project description #######################################
1- I have created a maven project
2- Used selenium APIs with TestNG
3- I have used assertions with TestNG
4- As discussed I have created 3 test classes
5- I have created a config.properties file inside the src/main/resources sorce folder and one TestBase class for basic functionalities like opening the browser and closing that.

############################################## Inside POM.xml File #######################################
I have used maven dependecies for:
1- Selenium: 4.1.1
2- TestNG: 7.5
3- WebdriverManager: 5.0.3(for opening the browser)
4- OkHTTP: 4.3.1
5- JSON: 20211205(For read the json data)
6- Mongodb-driver: 4.0.6

############################################## Inside testng.xml File #####################################
1- I have created 2 <test> for running the test cases parallelly on windows and mac os because we are running the test cases on saucelabs so will handle this.
2- Currently I have enabled the 1 <test> because of the requirement, If it needs to be run parallelly please uncomment the 2nd <test> tag
3- I have used some parameters value for configuring the browser setup and platform
4- I have used parallel="tests" keyword in <suite> tag so that we can run the test parallelly if 2nd <test> is enabled then it will run parellelly.

############################################# Inside TestBase class ########################################
1- I have created TestBase class constructor inside it and defined the code for the properties file.
2- I have created one initialization() for local and remote webdriver(using MutableCapabilities) and closingBrowser() methods for closing the browser.

############################################# Inside Test classes class #####################################
1- Test Class AmazonTest
    a)- used @BeforeMethod for the browser initialization and launching the browser
    b)- I have created 2 tests inside @Test and used @AfterMethod for closing the browser.

2- Test Class APITest
    a)- I have created 3 private methods used for test classes
    b)- Created 2 tests(for GET and POST request) inside it using private methods

3- Test Class DatabaseTest
    a)- This test class is not completed because I am not aware of the mongodb and I have tried to connect with the database but not able to connect with it.





