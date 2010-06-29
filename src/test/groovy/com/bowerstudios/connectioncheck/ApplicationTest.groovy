package com.bowerstudios.connectioncheck

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test
import static org.junit.Assert.*

class ApplicationTest {

	@Test
	void execute(){
		Application app = new Application()
		
		app.check = { url ->
			return false
		}
		
		List urls = app.execute()
		
		println urls
		
		assertEquals(3, urls.size())
		
		assertTrue(urls[0]!=urls[1])
		assertTrue(urls[1]!=urls[2])
		assertTrue(urls[0]!=urls[2])
	}
	
	@Test
	void check(){
		Application app = new Application()
		app.checkNetwork = [externalConnectionExists:{url -> return true }]
		app.persistStatistic = [persistStatistic:{url, up, date -> }]
		
		assertTrue(app.check("test.com"))
		
		app.checkNetwork = [externalConnectionExists:{url -> return false }]
		assertTrue(!app.check("test.com"))
	}
	
}
