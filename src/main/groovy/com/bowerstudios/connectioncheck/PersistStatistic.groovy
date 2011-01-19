package com.bowerstudios.connectioncheck

class PersistStatistic {

	String dbLocation = "jdbc:sqlite:/home/daniel/apps/connCheck/connCheck.db"
	String dbDriver = "org.sqlite.JDBC"
	
	void execute(String url, int responseCode, boolean up){
		def sql = getDb() 
		sql.execute("insert into checks(url, responseCode, up, date) values(?, ?, ?, ?)", [url, responseCode, up, new Date()])
	}
	
	def getDb(){
		 def sql = groovy.sql.Sql.newInstance(dbLocation, dbDriver)
		 
		 //create table if not exist
		 sql.execute("""
		 	CREATE TABLE IF NOT EXISTS checks(url, responseCode, up, date);
		 """)
		 
		 return sql 
	}
	
	void printContents(){
		def sql = getDb()
		sql.rows("select * from checks order by date desc").each{
			println("url:" + it.url + " up:" + it.up + " response code:" + it.responseCode + " date: " + new Date(it.date.toLong()) )
		}
		
	}
	
	
}
