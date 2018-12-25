package co.uk.prudential.assignment.util;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.validator.routines.UrlValidator;

public class Utility {
	
	/**
	 * This method is used for validation of input URL argument
	 * 
	 * @param args
	 * @return valid URL
	 */
	public static String validateParseInput(String[] args){
		
		if( args == null || args.length == 0 ) {
			throw new IllegalArgumentException("1st Argument cannot be empty");
		}
		
		String url = args[0];
		
		if( !isURLValid(url) ){
			throw new IllegalArgumentException("1st Argument must be a valid URL prefixed by http(s)");
		}
		
		return url;
	}
	
	
	
	/**
	 * This returns domainName of url without 'www' prefix
	 * 
	 * @param urlStr
	 * @return host
	 * @throws MalformedURLException
	 */
	public static String getDomainName(String urlStr) throws MalformedURLException {
		URL url = new URL(urlStr);
		String domain = url.getHost();
		return domain.startsWith("www.") ? domain.substring(4) : domain;
	}

	
	
	/**
	 * This validates Syntax of URL string and checks if protocol is http(s)
	 * 
	 * @param url
	 * @return boolean validityStatus
	 */
	public static boolean isURLValid(String url) {
		// Jsoup can parse only HTTP and HTTPS urls
		String[] schemes = { "http", "https" };
		boolean status;

		UrlValidator urlValidator = new UrlValidator(schemes);
		if (urlValidator.isValid(url)) {
			status = true;
		} else {
			status = false;
		}
		return status;
	}

}