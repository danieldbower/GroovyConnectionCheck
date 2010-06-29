package com.bowerstudios.connectioncheck

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test
import static org.junit.Assert.*

class ApplicationTest {

	Application app
	
	@Before
	void setup(){
		app = new Application()
	}
	
	@Test
	void execute(){
		app.checkUrl = {url ->
			app.testedUrls.add url
			return new CheckNetwork(
					url:url,
					responseCodeForUrl:0,
					connectionExists:false) }
		
		app.execute(null)
		
		println app.testedUrls
		
		assertEquals(3, app.testedUrls.size())
		
		assertTrue(app.testedUrls[0]!=app.testedUrls[1])
		assertTrue(app.testedUrls[1]!=app.testedUrls[2])
		assertTrue(app.testedUrls[0]!=app.testedUrls[2])
	}
	
}
