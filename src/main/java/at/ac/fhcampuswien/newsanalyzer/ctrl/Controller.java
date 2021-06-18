package at.ac.fhcampuswien.newsanalyzer.ctrl;

import at.ac.fhcampuswien.newsanalyzer.ui.NewsApiException;
import at.ac.fhcampuswien.newsapi.NewsApi;
import at.ac.fhcampuswien.newsapi.NewsApiBuilder;
import at.ac.fhcampuswien.newsapi.beans.Article;
import at.ac.fhcampuswien.newsapi.beans.NewsResponse;
import at.ac.fhcampuswien.newsapi.enums.Category;
import at.ac.fhcampuswien.newsapi.enums.Country;
import at.ac.fhcampuswien.newsapi.enums.Endpoint;

import java.util.Comparator;
import java.util.List;

//In cooperation with Jakob Welna

public class Controller {

	public static final String APIKEY = "25fe590b3b774181ae0683d247c3b844";  //TODO add your api key

	public List<Article> process(String s, Category category, Country country, Endpoint endpoint) {
		//TODO implement Error handling

		//TODO load the news based on the parameters

		//TODO implement methods for analysis

		System.out.println("Start process");

		try {
			NewsApi newsApi = new NewsApiBuilder()
					.setApiKey(APIKEY)
					.setQ(s) // set query
					.setEndPoint(endpoint)
					.setSourceCountry(country)
					.setSourceCategory(category)
					.createNewsApi();

			NewsResponse newsResponse = newsApi.getNews();
			if (newsResponse != null) {
				List<Article> articles = newsResponse.getArticles();
				articles.stream().forEach(article -> System.out.println(article.toString()));
				return articles;
			}

		}catch(NewsApiException e){
			System.out.println(e.getMessage());
		}


		System.out.println("End process");
		return null;


	}

	public void analyze1(List<Article> article){
		if(article == null)
			System.out.println("No articles");
		long count = article.size();
		System.out.println(count);
	}

	public void analyze2(List<Article> a){
	}

	public void analyze3(List<Article> a){
		Article shortestName = a.stream().filter(article -> article.getAuthor() != null).min(Comparator.comparing(article -> article.getAuthor().length())).orElse(new Article());
		System.out.println(shortestName.getAuthor());
	}

	public void analyze4(List<Article> a){
		a.stream()
				.sorted((artice1, article2) -> {
					String title1 = artice1.getTitle();
					String title2 = article2.getTitle();
					if (title1.length() == title2.length()) {
						return title1.compareToIgnoreCase(title2);
					} else {
						return Integer.compare(title2.length(), title1.length());
					}
				})
				.forEach(article -> System.out.println("Title: " + article.getTitle()));
	}



	public Object getData() {
		
		return null;
	}
}
