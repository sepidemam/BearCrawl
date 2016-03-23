package ch.usi.star.bear.findyourhouse;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ch.usi.star.bear.annotations.BearFilter;
import ch.usi.star.bear.loader.LogLine;
import ch.usi.star.bear.model.Label;

public class Filters {


	@BearFilter(regex = "/annunci/cerca/$")
	public static Set<Label> FilterCerca(LogLine logline) {
		Set<Label> result = new HashSet<Label>();
		result.add(new Label("search", 0l));
		return result;
	}
	
	@BearFilter(regex = "/annunci/cerca/\\?")
	public static Set<Label> FilterCercaPagina(LogLine logline) {
		Set<Label> result = new HashSet<Label>();
		result.add(new Label("search", 0l));
		return result;
	}
	
	@BearFilter(regex = "/annunci/affitto/\\?page=([0-9])+")
	public static Set<Label> FilterAffittoPagina(LogLine logline) {
		Set<Label> result = new HashSet<Label>();
		
		Pattern pattern = Pattern.compile("/annunci/affitto/\\?page=([0-9])+");
		Matcher matcher = pattern.matcher(logline.getUrl());
		matcher.find();
				
		String number = matcher.group(1);
		result.add(new Label("renting_page", 0l));
		result.add(new Label("page_"+number, 0l));

		return result;
	}
	
	@BearFilter(regex = "/annunci/vendita/\\?page=([0-9])+")
	public static Set<Label> FilterVenditaPagina(LogLine logline) {
		Set<Label> result = new HashSet<Label>();
		Pattern pattern = Pattern.compile("/annunci/vendita/\\?page=([0-9]+)");
		Matcher matcher = pattern.matcher(logline.getUrl());
		matcher.find();
		
		String number = matcher.group(1);
		result.add(new Label("sales_page", 0l));
		result.add(new Label("page_"+number, 0l));

		return result;
	}
	
	@BearFilter(regex = "/annunci/vendita/\\?fb_xd_fragment")
	public static Set<Label> FilterVenditaPaginaFacebook(LogLine logline) {
		Set<Label> result = new HashSet<Label>();
		result.add(new Label("sales_page", 0l));
		result.add(new Label("facebook"));
		return result;
	}
	
	@BearFilter(regex = "/annunci/affitto(/)?$")
	public static Set<Label> FilterAffitto(LogLine logline) {
		Set<Label> result = new HashSet<Label>();
		result.add(new Label("renting_page", 0l));
		result.add(new Label("page_1", 0l));
		return result;
	}
	
	@BearFilter(regex = "/annunci/vendita(/)?$")
	public static Set<Label> FilterVendita(LogLine logline) {
		Set<Label> result = new HashSet<Label>();
		result.add(new Label("sales_page", 0l));
		result.add(new Label("page_1", 0l));
		return result;
	}
	
	@BearFilter(regex = "/annunci/affitto/(\\w+)")
	public static Set<Label> FilterAnnunvioAffitto(LogLine logline) {
		Set<Label> result = new HashSet<Label>();
		result.add(new Label("renting_anncs", 0l));
		return result;
	}
	
	@BearFilter(regex = "/annunci/vendita/(\\w+)")
	public static Set<Label> FilterAnnunvioVendita(LogLine logline) {
		Set<Label> result = new HashSet<Label>();
		result.add(new Label("sales_anncs", 0l));
		return result;
	}
	
	@BearFilter(regex = "/admin/")
	public static Set<Label> FilterAdmin(LogLine logline) {
		Set<Label> result = new HashSet<Label>();
		if(logline.getRawLine().contains("POST")){
			if(logline.getRawLine().contains(" 302 "))
				result.add(new Label("login_success", 0l));
			else
				result.add(new Label("login_fail", 0l));
		}else
			result.add(new Label("admin", 0l));
		return result;
	}
	
	@BearFilter(regex = "^/$")
	public static Set<Label> FilterHome(LogLine logline) {
		Set<Label> result = new HashSet<Label>();
		result.add(new Label("homepage", 0l));
		return result;
	}
	
	@BearFilter(regex = "^http://www.findyourhouse.it/$")
	public static Set<Label> FilterHome2(LogLine logline) {
		Set<Label> result = new HashSet<Label>();
		result.add(new Label("homepage", 0l));
		return result;
	}
	
	@BearFilter(regex = "/agenzia/")
	public static Set<Label> FilterAgency(LogLine logline) {
		Set<Label> result = new HashSet<Label>();
		result.add(new Label("agency", 0l));
		return result;
	}

	@BearFilter(regex = "/news/list/")
	public static Set<Label> FilterNewsPage(LogLine logline) {
		Set<Label> result = new HashSet<Label>();
		result.add(new Label("news_page", 0l));
		return result;
	}
	
	@BearFilter(regex = "/news/(\\d+)/")
	public static Set<Label> FilterNews(LogLine logline) {
		Set<Label> result = new HashSet<Label>();
		result.add(new Label("news_article", 0l));
		return result;
	}
	
	@BearFilter(regex = "/contattaci/info/$")
	public static Set<Label> FilterContact(LogLine logline) {
		Set<Label> result = new HashSet<Label>();
		if(logline.getRawLine().contains(" POST "))
			result.add(new Label("contact_requested", 0l));
		else
			result.add(new Label("contacts", 0l));
		return result;
	}
	
	@BearFilter(regex = "/contattaci/info/success/")
	public static Set<Label> FilterContactSubmitted(LogLine logline) {
		Set<Label> result = new HashSet<Label>();
		result.add(new Label("contacts_requested", 0l));
		return result;
	}
	
	
	
	@BearFilter(regex = "/contattaci/privacy/")
	public static Set<Label> FilterTou(LogLine logline) {
		Set<Label> result = new HashSet<Label>();
		result.add(new Label("tou", 0l));
		return result;
	}
	
	@BearFilter(regex = "/contattaci/valutazione/$")
	public static Set<Label> FilterEvaluationPage(LogLine logline) {
		Set<Label> result = new HashSet<Label>();
		if(logline.getRawLine().contains(" POST "))
			result.add(new Label("contact_requested", 0l));
		else
			result.add(new Label("contacts", 0l));
		return result;
	}
	
	@BearFilter(regex = "/contattaci/ajax/info/$")
	public static Set<Label> FilterEvaluation(LogLine logline) {
		Set<Label> result = new HashSet<Label>();
		result.add(new Label("contact_requested", 0l));
		return result;
	}
}
