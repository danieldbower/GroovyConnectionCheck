package com.bowerstudios.connectioncheck

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class CheckNetwork {

	String url
	int responseCodeForUrl
	boolean connectionExists
	
	public void execute(String url){
		this.url = url;
		responseCodeForUrl = getResponseCodeForUrl(url)
		connectionExists = doesConnectionExist(responseCodeForUrl)
	}
	
	private Logger logger
	
	private Logger getLogger(){
		if(!logger){
			logger = LoggerFactory.getLogger("com.bowerstudios.connectioncheck.CheckNetwork")
		}
		return logger
	}
		
	/**
	 * Does the external Connection Exist?
	 * @param url
	 * @return response code or null if no connection
	 */
	protected int getResponseCodeForUrl(String url){
		int responseCode = 0
		URL server = new URL(url);
		
		try{
			HttpURLConnection connection = (HttpURLConnection) server.openConnection()
			responseCode = connection.getResponseCode()
		}catch(Exception e){
			responseCode = 0
			getLogger().info "Invalid Connection", e
		}
		
		return responseCode
	}
	
	protected boolean doesConnectionExist(int code){
		switch(code){
			case 200:
				return true
		    default :
				getLogger().info "Return code was: $code"
				return false
		}
	}
}
