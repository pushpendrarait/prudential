package co.uk.prudential.assignment.client;

import co.uk.prudential.assignment.util.Utility;
import co.uk.prudential.assignment.worker.Crawler;

public class Solution {

	public static void main(String args[]) {

		String url = Utility.validateParseInput(args);
		Crawler spider = new Crawler();
		spider.crawlWebPage(url,0);
	}

}