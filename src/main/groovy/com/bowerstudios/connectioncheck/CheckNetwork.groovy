package com.bowerstudios.connectioncheck

class CheckNetwork {

	String url
	int responseCodeForUrl
	boolean connectionExists
	
	public void execute(String url){
		this.url = url;
		responseCodeForUrl = getResponseCodeForUrl(url)
		connectionExists = doesConnectionExist(responseCodeForUrl)
	}
	
	/**
	 * Does the external Connection Exist?
	 * @param url
	 * @return response code or null if no connection
	 */
	protected int getResponseCodeForUrl(String url){
		int responseCode = 0;
		URL server = new URL(url);
		
		try{
			HttpURLConnection connection = (HttpURLConnection) server.openConnection()
			responseCode = connection.getResponseCode()
		}catch(ConnectException ce){
			responseCode = 0;
		}catch(UnknownHostException uhe){
			responseCode = 0;
		}
		
		return responseCode
	}
	
	protected boolean doesConnectionExist(int code){
		switch(code){
			case 200:
				return true
		    default :
				return false
		}
	}
}
