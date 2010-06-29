package com.bowerstudios.connectioncheck

class Application {

	/**
	* static method to start the application from the command line
	* @param args
	*/
   static void main(String[] args){
	   new Application().execute(args)
   }
   
   static final def domains = ["http://google.com", "http://slashdot.org", "http://yahoo.com", 
	   "http://microsoft.com", "http://cnet.com", "http://apple.com", 
	   "http://dell.com", "http://espn.com", "http://facebook.com", 
	   "http://springsource.org"]
   
   protected List testedUrls
   
   /**
    * See if the external connection is working.  return a list of urls attempted.
    * @return list
    */
   void execute(String[] args){
   		if(args){
			new PersistStatistic().printContents()
		}else{
			checkConnectivity()
		}
   }
   
   protected void checkConnectivity(){
	   //set empty list of urls tested
	   testedUrls = []
	   
	   //try your own service first, just to be neighborly...
	   CheckNetwork cn = checkUrl("http://bowerstudios.com/ping.txt")
	   
	   //if failed, try another
	   if(!cn.connectionExists){
		   int randomDomainIndex = Math.random() * 10
		   cn = checkUrl(domains[randomDomainIndex])
		   
		   //if still failed, try one more time
		   if(!cn.connectionExists){
			   cn = checkUrl(domains[randomDomainIndex+1])
		   }
	   }
   }
   
   protected checkUrl = { String url ->
	   CheckNetwork cn = new CheckNetwork()
	   cn.execute(url)
	   
	   testedUrls.add(cn.url)
	   
	   PersistStatistic persist = new PersistStatistic();
	   persist.execute(cn.url, cn.responseCodeForUrl, cn.connectionExists)
	   
	   return cn
   }
   
}
