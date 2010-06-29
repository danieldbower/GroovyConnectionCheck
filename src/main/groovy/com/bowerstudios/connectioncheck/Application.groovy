package com.bowerstudios.connectioncheck

class Application {

	/**
	* static method to start the application from the command line
	* @param args
	*/
   static void main(String[] args){
	   new Application().execute()
   }
   
   def domains = ["google.com", "slashdot.org", "yahoo.com", "microsoft.com", 
	   "cnet.com", "apple.com", "dell.com", "espn.com", "facebook.com", "springsource.org"]
   
   /**
    * See if the external connection is working.  return a list of urls attempted.
    * @return list
    */
   List execute(){
	   List urls = ["bowerstudios.com"]
	   
	   boolean up = check("bowerstudios.com");
	   
	   if(!up){
		   int i = Math.random() * 10
		   up = check(domains[i])
		   urls.add(domains[i])
		   
		   if(!up){
			   up = check(domains[i+1])
			   urls.add(domains[i+1])
		   }
	   }
	   
	   return urls
   }
   
   def check = {String url ->
	   boolean up = getCheckNetwork().externalConnectionExists(url)
	   getPersistStatistic().persistStatistic(url, up, new Date())
	   return up;
   }
   
   protected def checkNetwork
   protected def getCheckNetwork(){
	   if(!checkNetwork){
		   checkNetwork = new CheckNetwork()
	   }
	   return checkNetwork
   }
   
   protected def persistStatistic
   protected def getPersistStatistic(){
	   if(!persistStatistic){
		   persistStatistic = new PersistStatistic()
	   }
	   return persistStatistic
   }
   
}
