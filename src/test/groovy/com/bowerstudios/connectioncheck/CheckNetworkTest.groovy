package com.bowerstudios.connectioncheck

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test
import static org.junit.Assert.*

class CheckNetworkTest {
	
	CheckNetwork checkNetwork = new CheckNetwork()
	
	@Test
	void getResponseCodeForUrl(){
		assertEquals(200, checkNetwork.getResponseCodeForUrl("http://google.com"))
	}
	
	@Test
	void responseCodeForUrlFail(){
		//check what should be nonexistent
		assertEquals(0, checkNetwork.getResponseCodeForUrl("http://localhost:64999"))
	}
	
	@Test
	void connectionExists(){
		assertTrue(checkNetwork.doesConnectionExist(200))
		assertFalse(checkNetwork.doesConnectionExist(0))
	}
	
}
