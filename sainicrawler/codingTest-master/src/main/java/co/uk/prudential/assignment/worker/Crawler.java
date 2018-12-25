package co.uk.prudential.assignment.worker;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashSet;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import co.uk.prudential.assignment.util.Utility;

/**
 * This Class Can use Multi Threaded Tasks for each of say 10 URLS
 * Output can be logged to one File - 1 For each different Title 
 */
public class Crawler {

	// Constant which specifies how far can we nest from original URL
	// Limits recursion to save Time and CPU
	// To cover website more thoroughly , Increase it to set it to a  higher number
	private static final int TRAVERSAL_DEPTH = 3;

	private Set<String> linksSet;

	private String hostDomain;

	public Crawler() {
		this.linksSet = new HashSet<String>();
	}

	
	/**
	 * This is main worker method which takes a webURL as argument and parses it to return all Links and Image Links
	 * We make a Unique Set of non-duplicate URLS, Validate it , and Check its host same as original WebUrl Domain.
	 * On encountering Invalid URL we ignore and move ahead to next
	 * 
	 * @param url
	 * @param depth
	 */
	public void crawlWebPage(String url, int depth) {
		
		if(hostDomain == null){
			try {
				hostDomain = Utility.getDomainName(url);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
			System.out.println("hostDomain " + hostDomain);
		}
		
		
		if ((!linksSet.contains(url) && (depth < TRAVERSAL_DEPTH))) {
			
			Set<String> UniqueLinksInWebPage = new HashSet<String>();
			
			try {
				linksSet.add(url);
				
				if( Utility.isURLValid(url) && hostDomain.equalsIgnoreCase(Utility.getDomainName(url)) ){
					System.out.println("URL Parsed: '" + url + "'  matches original domain and is Valid" );
				
				
					Document document = Jsoup.connect(url).get();
					Elements pageLinks = document.select("a[href]");
					Elements imgs = document.select("img[src]");

					System.out.println("Page Title:  " + document.title());
				    System.out.println("Total Links Found: " + pageLinks.size());
				    System.out.println("Total Images Found: " + imgs.size());

				    System.out.println("---------------------------------------------------------------------------------------------");
				
				    depth++;
				
				    for (Element link : pageLinks) {
					   System.out.println(link.text() + " :: " + link.absUrl("href").toLowerCase());
					   UniqueLinksInWebPage.add(link.absUrl("href").toLowerCase());
				    }
				
				    System.out.println("---------------------------------------------------------------------------------------------");

				    for (Element img : imgs) {
				    	System.out.println(img.attr("abs:src"));
				    }

				    System.out.println("---------------------------------------------------------------------------------------------");

				    System.out.println("Number of Unique Websites Found on this Page: " + UniqueLinksInWebPage.size());
				
				    System.out.println("------------X------------X------------X------------X------------X------------X------------X\n");
				
				    for(String linkStr: UniqueLinksInWebPage){
				    	crawlWebPage(linkStr, depth);
				    }
				}else{
					System.out.println("URL Parsed: '" + url + "'  is Invalid or is Outside Original Domain" );
					System.out.println("------------X------------X------------X------------X------------X------------X------------X\n");
				}
			} catch (IOException exp) {
				System.out.println("Exception Occured: " + exp);
			}
		}
	}
	
	/**
	 * This method is for Say Testing offline Copy of HTML Document as entryPoint - Test Helper
	 * 
	 * @param fileName
	 * @param depth
	 * @param defaultPrefixURL
	 */
	public void crawlHtmlPage(String fileName, int depth, String defaultPrefixURL) {

		if ((!linksSet.contains(fileName) && (depth < TRAVERSAL_DEPTH))) {
			
			Set<String> UniqueLinksInHTMLPage = new HashSet<String>();
			
			try {
				linksSet.add(fileName);
				System.out.println("URL Parsed: '" + fileName + "'");
				ClassLoader classLoader = getClass().getClassLoader();
				File inputHtmlFile = new File(classLoader.getResource(fileName).getFile());
				Document htmlFileDocument = Jsoup.parse(inputHtmlFile, "UTF-8",  defaultPrefixURL);
				Elements htmlFilelinks = htmlFileDocument.select("a[href]");	
				Elements imgs = htmlFileDocument.select("img[src]");

				System.out.println("Page Title:  " + htmlFileDocument.title());
				System.out.println("Total Links Found: " + htmlFilelinks.size());
				System.out.println("Total Images Found: " + imgs.size());

				System.out.println("---------------------------------------------------------------------------------------------");
				
				depth++;
				
				for (Element link : htmlFilelinks) {
					System.out.println(link.text() + " :: " + link.absUrl("href").toLowerCase());
					UniqueLinksInHTMLPage.add(link.absUrl("href").toLowerCase());
				}
				
				System.out.println(
						"---------------------------------------------------------------------------------------------");

				for (Element img : imgs) {
					System.out.println(img.attr("abs:src"));
				}

				System.out.println("---------------------------------------------------------------------------------------------");

				System.out.println("Number of Unique Websites Found on this Page: " + UniqueLinksInHTMLPage.size());
				
				System.out.println("------------X------------X------------X------------X------------X------------X------------X\n");
				
				for(String linkStr: UniqueLinksInHTMLPage){
					crawlWebPage(linkStr, depth);
				}
			} catch (IOException exp) {
				System.out.println("Exception Occured: " + exp);
			}
		}

	}

	
	
}
