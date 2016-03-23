package ch.usi.star.bear.myualberta;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ch.usi.star.bear.annotations.BearFilter;
import ch.usi.star.bear.loader.LogLine;
import ch.usi.star.bear.model.Label;

public class Filters {


	@BearFilter(regex = "athletics")
	public static Set<Label> FilterCerca(LogLine logline) {
		Set<Label> result = new HashSet<Label>();
		result.add(new Label("athletics", 1L));
		return result;
	}
	
	@BearFilter(regex = "social")
	public static Set<Label> FilterCercaPagina(LogLine logline) {
		Set<Label> result = new HashSet<Label>();
		result.add(new Label("social", 2L));
		return result;
	}
	
	@BearFilter(regex = "home/search?")
	public static Set<Label> FilterAffittoPagina(LogLine logline) {
		Set<Label> result = new HashSet<Label>();
		
		//Pattern pattern = Pattern.compile("/annunci/affitto/\\?page=([0-9])+");
		//Matcher matcher = pattern.matcher(logline.getUrl());
		//matcher.find();
				
		//String number = matcher.group(1);
		//result.add(new Label("renting_page", 0l));
		result.add(new Label("search", 3L));

		return result;
	}
	
	@BearFilter(regex = "config=home")
	public static Set<Label> FilterVenditaPagina(LogLine logline) {
		Set<Label> result = new HashSet<Label>();
//		Pattern pattern = Pattern.compile("/annunci/vendita/\\?page=([0-9]+)");
	//	Matcher matcher = pattern.matcher(logline.getUrl());
	//	matcher.find();
		
	//	String number = matcher.group(1);
	//	result.add(new Label("sales_page", 0l));
		result.add(new Label("home", 4L));

		return result;
	}
	
	@BearFilter(regex = "trnst")
	public static Set<Label> FilterVenditaPaginaFacebook(LogLine logline) {
		Set<Label> result = new HashSet<Label>();
		result.add(new Label("transit", 5L));
		//result.add(new Label("facebook"));
		return result;
	}
	
	@BearFilter(regex = "news")
	public static Set<Label> FilterAffitto(LogLine logline) {
		Set<Label> result = new HashSet<Label>();
		result.add(new Label("news", 6L));
		//result.add(new Label("page_1", 0l));
		return result;
	}
	
	@BearFilter(regex = "video")
	public static Set<Label> FilterVendita(LogLine logline) {
		Set<Label> result = new HashSet<Label>();
		result.add(new Label("videoe", 7L));
		//result.add(new Label("page_1", 0l));
		return result;
	}
	
	@BearFilter(regex = "calendar")
	public static Set<Label> FilterAnnunvioAffitto(LogLine logline) {
		Set<Label> result = new HashSet<Label>();
		result.add(new Label("calendar", 8L));
		return result;
	}
	
	@BearFilter(regex = "emergency")
	public static Set<Label> FilterAnnunvioVendita(LogLine logline) {
		Set<Label> result = new HashSet<Label>();
		result.add(new Label("emergency", 9L));
		return result;
	}
	
	@BearFilter(regex = "people")
	public static Set<Label> FilterAnnunvioVendita2(LogLine logline) {
		Set<Label> result = new HashSet<Label>();
		result.add(new Label("people", 10L));
		return result;
	}
	
	@BearFilter(regex = "login")
	public static Set<Label> FilterAnnunvioVendita3(LogLine logline) {
		Set<Label> result = new HashSet<Label>();
		result.add(new Label("login", 11L));
		return result;
	}	
	@BearFilter(regex = "eclass")
	public static Set<Label> FilterAnnunvioVendita4(LogLine logline) {
		Set<Label> result = new HashSet<Label>();
		result.add(new Label("eclass", 12L));
		return result;
	}
	@BearFilter(regex = "map")
	public static Set<Label> FilterAnnunvioVendita5(LogLine logline) {
		Set<Label> result = new HashSet<Label>();
		result.add(new Label("map", 13L));
		return result;
	}
	@BearFilter(regex = "events")
	public static Set<Label> FilterAnnunvioVendita6(LogLine logline) {
		Set<Label> result = new HashSet<Label>();
		result.add(new Label("events", 14L));
		return result;
	}
	@BearFilter(regex = "onecard")
	public static Set<Label> FilterAnnunvioVendita7(LogLine logline) {
		Set<Label> result = new HashSet<Label>();
		result.add(new Label("onecard", 15L));
		return result;
	}	
	@BearFilter(regex = "caps")
	public static Set<Label> FilterAnnunvioVendita8(LogLine logline) {
		Set<Label> result = new HashSet<Label>();
		result.add(new Label("caps", 16L));
		return result;
	}	
	@BearFilter(regex = "student-services")
	public static Set<Label> FilterAnnunvioVendita9(LogLine logline) {
		Set<Label> result = new HashSet<Label>();
		result.add(new Label("studentservices", 17L));
		return result;
	}	
	@BearFilter(regex = "caps")
	public static Set<Label> FilterAnnunvioVendita10(LogLine logline) {
		Set<Label> result = new HashSet<Label>();
		result.add(new Label("caps", 18L));
		return result;
	}
	@BearFilter(regex = "customize")
	public static Set<Label> FilterAnnunvioVendita11(LogLine logline) {
		Set<Label> result = new HashSet<Label>();
		result.add(new Label("customize", 19L));
		return result;
	}
	@BearFilter(regex = "about")
	public static Set<Label> FilterAnnunvioVendita12(LogLine logline) {
		Set<Label> result = new HashSet<Label>();
		result.add(new Label("about", 20L));
		return result;
	}
	
	@BearFilter(regex = "transit")
	public static Set<Label> FilterAnnunvioVendita13(LogLine logline) {
		Set<Label> result = new HashSet<Label>();
		result.add(new Label("transit", 21L));
		return result;
	}
	@BearFilter(regex = "social-media")
	public static Set<Label> FilterAnnunvioVendita14(LogLine logline) {
		Set<Label> result = new HashSet<Label>();
		result.add(new Label("socialmedia", 22L));
		return result;
	}
	
	@BearFilter(regex = "photos")
	public static Set<Label> FilterAnnunvioVendita15(LogLine logline) {
		Set<Label> result = new HashSet<Label>();
		result.add(new Label("photos", 23L));
		return result;
	}
	
	@BearFilter(regex = "home-index")
	public static Set<Label> FilterAnnunvioVendita16(LogLine logline) {
		Set<Label> result = new HashSet<Label>();
		result.add(new Label("home", 24L));
		return result;
	}
	
	@BearFilter(regex = "homelink")
	public static Set<Label> FilterAnnunvioVendita17(LogLine logline) {
		Set<Label> result = new HashSet<Label>();
		result.add(new Label("home", 25L));
		return result;
	}
	@BearFilter(regex = "stusrv")
	public static Set<Label> FilterAnnunvioVendita18(LogLine logline) {
		Set<Label> result = new HashSet<Label>();
		result.add(new Label("studentservices", 26L));
		return result;
	}	
	
/*	@BearFilter(regex = "menu-button")
	public static Set<Label> FilterAnnunvioVendita19(LogLine logline) {
		Set<Label> result = new HashSet<Label>();
		result.add(new Label("menu-button", 27L));
		return result;
	}*/
	
	@BearFilter(regex = "fullweb")
	public static Set<Label> FilterAnnunvioVendita20(LogLine logline) {
		Set<Label> result = new HashSet<Label>();
		result.add(new Label("fullweb", 28L));
		return result;
	}	
	@BearFilter(regex = "home/modules")
	public static Set<Label> FilterAnnunvioVendita21(LogLine logline) {
		Set<Label> result = new HashSet<Label>();
		result.add(new Label("home", 29L));
		return result;
	}

	@BearFilter(regex = "home/ HTTP")
	public static Set<Label> FilterAnnunvioVendita22(LogLine logline) {
		Set<Label> result = new HashSet<Label>();
		result.add(new Label("home", 30L));
		return result;
	}
	
	@BearFilter(regex = "action-search")
	public static Set<Label> FilterAnnunvioVendita23(LogLine logline) {
		Set<Label> result = new HashSet<Label>();
		result.add(new Label("search", 31L));
		return result;
	}
	@BearFilter(regex = "error?")
	public static Set<Label> FilterAnnunvioVendita24(LogLine logline) {
		Set<Label> result = new HashSet<Label>();
		result.add(new Label("error", 32L));
		return result;
	}			
			
}
