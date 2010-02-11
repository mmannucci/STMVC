dataSource {
	logSql=true
	/*
	pooled = true
	driverClassName = "org.hsqldb.jdbcDriver"
	username = "sa"
	password = ""
	*/
	url =  "jdbc:mysql://localhost:3306/prova_mvc"
	pooled = false
	driverClassName = "com.mysql.jdbc.Driver"
	username = "root" // "sa"
	password = "" //   ""
	dialect = "org.hibernate.dialect.MySQL5InnoDBDialect"
}
hibernate {
    cache.use_second_level_cache=true
    cache.use_query_cache=true
    cache.provider_class='net.sf.ehcache.hibernate.EhCacheProvider'
}
// environment specific settings
environments {
	development {
		dataSource {
			dbCreate = "create-drop" // one of 'create', 'create-drop','update'
			//url = "jdbc:hsqldb:mem:devDB"
		}
	}
	test {
		dataSource {
			dbCreate = "update"
			url = "jdbc:hsqldb:mem:testDb"
		}
	}
	production {
		dataSource {
			dbCreate = "update"
			url = "jdbc:hsqldb:file:prodDb;shutdown=true"
		}
	}
}