package com.bowerstudios.connectioncheck

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test
import static org.junit.Assert.*

class PersistStatisticTest {
	PersistStatistic stats = new PersistStatistic()
	
	@Before
	void setup(){
		stats.dbLocation = "jdbc:sqlite:/code/groovy/projects/groovyspace/ConnectionCheck/dist/connCheckTest.db"
	}
	
	@Test
	void getDb(){
		def sql = stats.getDb()
		assertNotNull(sql);
		
		int rowCount = sql.firstRow("""select count(*) as rowCount from checks""").rowCount
		assertNotNull(rowCount)
	}
	
	@Test
	void execute(){
		def sql = stats.getDb()
		
		int rowCount = sql.firstRow("""select count(*) as rowCount from checks""").rowCount
		stats.execute "test.com", 0, false
		int rowCount2 = sql.firstRow("""select count(*) as rowCount from checks""").rowCount
		
		assertEquals(rowCount+1, rowCount2)
	}
}
