package co.uk.prudential.assignment.worker;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Categories.ExcludeCategory;

public class CrawlerTest {

	Crawler crawler;

	@Before
	public void init() {
		crawler = new Crawler();
	}

	@Test
	public void crawlHtmlPageShouldReturnListOfLinksAndImages() throws IOException {
		String htmlFileName = "Input.html";
		String defaultPrefixURL = "http://www.prudential.co.uk/";
		crawler.crawlHtmlPage(htmlFileName, 0, defaultPrefixURL);
	}

	@Test
	public void mimicWebPageCrawlBadHtmlPageAndLogErrors() throws IOException {
		String htmlFileName = "BadInput.html";
		String defaultPrefixURL = "https://www.prudential.co.uk/";
		crawler.crawlHtmlPage(htmlFileName, 0, defaultPrefixURL);
	}

	@Test
	public void crawlWebURLShouldReturnListOfLinksAndImages() throws IOException {
		String webUrl = "https://www.google.com";
		// String webUrl = "https://www.prudential.co.uk/";
		crawler.crawlWebPage(webUrl, 0);
	}

}
