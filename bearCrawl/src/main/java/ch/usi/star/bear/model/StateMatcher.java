package ch.usi.star.bear.model;

import ch.usi.star.bear.SpiderLeg;

public class StateMatcher {

	public String find(String input) {
		// TODO Auto-generated method stub
		String S;
		if (input.contains("athletics"))
		{
		     SpiderLeg spider= new SpiderLeg();
		        spider.crawl("https://myualberta.ualberta.ca/athletics");
		         S= spider.getLinks();
		        
		     	return (S);
		}
		
			else if (input.contains("sochial"))
			{
			    SpiderLeg spider= new SpiderLeg();
			       spider.crawl("https://myualberta.ualberta.ca/social");
			       S= spider.getLinks();
			       return (S);
			      
		}
			
			else if (input.contains("search"))
			{
				  SpiderLeg spider= new SpiderLeg();
				       spider.crawl("https://myualberta.ualberta.ca/home");
				         S= spider.getLinks();
				         return (S);       
		     }
					
			else if (input.contains("home"))
			{
				  SpiderLeg spider= new SpiderLeg();
				       spider.crawl("https://myualberta.ualberta.ca/home");
				         S= spider.getLinks();
				         return (S);       
		     }								
			else if (input.contains("transit"))
			{
				  SpiderLeg spider= new SpiderLeg();
				       spider.crawl("https://myualberta.ualberta.ca/trnst");
				         S= spider.getLinks();
				         return (S);       
		    }
			else if (input.contains("news"))
			{
				  SpiderLeg spider= new SpiderLeg();
				       spider.crawl("https://myualberta.ualberta.ca/news");
				         S= spider.getLinks();
				         return (S);       
		    }		
			else if (input.contains("video"))
			{
				  SpiderLeg spider= new SpiderLeg();
				       spider.crawl("https://myualberta.ualberta.ca/video");
				         S= spider.getLinks();
				         return (S);       
		    }
			else if (input.contains("calendar")|| input.contains("events"))
			{
				  SpiderLeg spider= new SpiderLeg();
				       spider.crawl("https://myualberta.ualberta.ca/calendar");
				         S= spider.getLinks();
				         return (S);       
		    }
			else if (input.contains("emergency"))
			{
				  SpiderLeg spider= new SpiderLeg();
				       spider.crawl("https://myualberta.ualberta.ca/uaemergency");
				         S= spider.getLinks();
				         return (S);       
		    }
			else if (input.contains("people"))
			{
				  SpiderLeg spider= new SpiderLeg();
				       spider.crawl("https://myualberta.ualberta.ca/people");
				         S= spider.getLinks();
				         return (S);       
		    }		
		
			else if (input.contains("login"))
			{
				  SpiderLeg spider= new SpiderLeg();
				       spider.crawl("https://myualberta.ualberta.ca/login/logoutConfirm?authority=shibboleth");
				         S= spider.getLinks();
				         return (S);       
		    }	
			else if (input.contains("eclass"))
			{
				  SpiderLeg spider= new SpiderLeg();
				       spider.crawl("https://eclass.srv.ualberta.ca/portal");
				         S= spider.getLinks();
				         return (S);       
		    }	
			else if (input.contains("maps"))
			{
				  SpiderLeg spider= new SpiderLeg();
				       spider.crawl("http://www.campusmap.ualberta.ca");
				         S= spider.getLinks();
				         return (S);       
		    }	
			else if (input.contains("onecard"))
			{
				  SpiderLeg spider= new SpiderLeg();
				       spider.crawl("https://www.myonecard.ualberta.ca");
				         S= spider.getLinks();
				         return (S);       
		    }		
			else if (input.contains("caps"))
			{
				  SpiderLeg spider= new SpiderLeg();
				       spider.crawl("https://capsconnections.ualberta.ca/caplet");
				         S= spider.getLinks();
				         return (S);       
		    }		
			else if (input.contains("studentservices"))
			{
				  SpiderLeg spider= new SpiderLeg();
				       spider.crawl("https://myualberta.ualberta.ca/stusrv");
				         S= spider.getLinks();
				         return (S);       
		    }	
			else if (input.contains("customize"))
			{
				  SpiderLeg spider= new SpiderLeg();
				       spider.crawl("https://myualberta.ualberta.ca/home/customize");
				         S= spider.getLinks();
				         return (S);       
		    }	
			else if (input.contains("socialmedia"))
			{
				  SpiderLeg spider= new SpiderLeg();
				       spider.crawl("https://myualberta.ualberta.ca/social");
				         S= spider.getLinks();
				         return (S);       
		    }		
			else if (input.contains("photos"))
			{
				  SpiderLeg spider= new SpiderLeg();
				       spider.crawl("https://myualberta.ualberta.ca/photos");
				         S= spider.getLinks();
				         return (S);       
		    }
			else if (input.contains("fullweb"))
			{
				  SpiderLeg spider= new SpiderLeg();
				       spider.crawl("http://ualberta.ca");
				         S= spider.getLinks();
				         return (S);       
		    }
			else if (input.contains("error"))
			{
				  SpiderLeg spider= new SpiderLeg();
				       spider.crawl("https://myualberta.ualberta.ca");
				         S= spider.getLinks();
				         return (S);       
		    }	
			else if (input.contains("library"))
			{
				  SpiderLeg spider= new SpiderLeg();
				       spider.crawl("https://myualberta.ualberta.ca/library");
				         S= spider.getLinks();
				         return (S);       
		    }
			else if (input.contains("feedback"))
			{
				  SpiderLeg spider= new SpiderLeg();
				       spider.crawl("https://www.surveymonkey.com/r/MyUAlbertaFeedback1?sm=YI69CrT4NFHEY7vJnonBjF7SZJkr11v29QxA6VJP8YY%3d");
				         S= spider.getLinks();
				         return (S);       
		    }
		
	else
	
	return (input);
	


}
}
