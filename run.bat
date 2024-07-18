set projectLocation=C:\JavaSelenium
cd %projectLocation%
set classpath=%projectLocation%\bin;%projectLocation%\lib\*
java org.testng.TestNG %projectLocation%\Module\Tests\Resources\testng.xml
pause