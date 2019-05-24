# Car Rental CMS in Java

The project is built on MVC architecture on simple Java Swing. It is a BSc second year - second semester assignment in Software Development Module.
<br><br>
The controller keeps track of all open views and possible changes made to any of them. View concurency is implemented, meaning that changes happening to one view, are applied real-time to any other open views. This is done with the use of concurrent hashmaps that keep all views stored.
<br><br>
The model is serialized to an external file as these were the requirements of the project. Additionaly, **partial** integration of the Dropbox API was implemented but it is not ready and if you use it you need to get your own access key from Dropbox and connect the application with your account as well as create the folder structure accordingly. 
<br><br>
Input validation on record insertion is done with Regular Expressions as the user types. 
<br><br>
For usage pelase see the <a href="https://github.com/antoniosTriant/Car-Rental-CMS-in-Java/wiki/User-Guide">User Guide</a>.
