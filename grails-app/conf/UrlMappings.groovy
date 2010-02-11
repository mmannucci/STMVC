class UrlMappings {
    static mappings = {
      "/$controller/$action?/$id?"{
	      constraints {
			 // apply constraints here
		  }
		
	  }
      "/"(view:"/login")
	  "500"(view:'/error')
	}
}
