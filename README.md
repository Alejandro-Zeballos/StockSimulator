# Stock Trade Simulator
The program will create Companies and Investors dinamically and will simulate a trading day, finishing the day only when all investors have no budget left or all the companies have no shares left.

## What I used
* Singleton Pattern
  * *Singleton of a subject class will allow me to access to this class from anywhere without* 
  *having to create a new instance of it all the time I want to use it.*
  
* Commander Pattern
  * *It will help me encapsulate logic from the client by using invokers (carriers) and receiver,*
  *class that will be in charge to execute commands.*

* Observer Pattern
  * *It will allow to update companies state to the inversors, in order for them to have*
  *the most up to date share price and availability awareness.*

* Iterator Pattern
  * *It was used with Investors iterator and it will help to hide the eligibility logic* 
   *from the client side, which will make the code more legible. and plus I can alter the Collection to iterate.*
  
* Array Lists
  
 Finally, I could have done this project without the use of these patterns but I have choosen them for the
 sake of  practicing, understanding where to implement certain patterns better, and getting familiar with them also.
