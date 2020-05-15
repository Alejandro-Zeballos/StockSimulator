Stock Trade Simulator

I have choosen to use the following patterns for this project:

Singletton
  Singleton of a subject class will allow me to access to this class from anywhere without 
  having to create a new instance of it all the time I want to use it.
  
Commander
  Commander pattern will help me encapsulate logic from the client by using invokers (carriers) and receiver,
  class that will be in charge to execute commands.

Observer
  Observer pattern will allow to update companies state to the inversors, in order for them to have
  the most up to date share price and availability.

Iterator
  Iterator pattern was used with Investors iterator and it will help to hide the eligibility logic 
  from the client side, which will make the code more legible. and plus I can alter the Collection to iterate.
  
 Finally, I could have done this project without the use of these patterns but I have choosen them for the
 sake of  practicing, understanding better, and getting familiar with them also.
