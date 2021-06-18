package at.ac.fhcampuswien.newsanalyzer.ui;


import at.ac.fhcampuswien.newsanalyzer.ctrl.Controller;
import at.ac.fhcampuswien.newsapi.beans.Article;
import at.ac.fhcampuswien.newsapi.enums.Category;
import at.ac.fhcampuswien.newsapi.enums.Country;
import at.ac.fhcampuswien.newsapi.enums.Endpoint;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;



public class UserInterface {
	public List<Article> article;

	private Controller ctrl = new Controller();

	public void getDataFromCtrl1(){
		System.out.println("ABC");

		article = ctrl.process("business", Category.business, Country.at, Endpoint.TOP_HEADLINES);
	}

	public void getDataFromCtrl2(){
		// TODO implement me
		article = ctrl.process("ether", Category.technology, Country.de, Endpoint.TOP_HEADLINES);

	}

	public void getDataFromCtrl3(){
		// TODO implement me
		article = ctrl.process("health", Category.health, Country.ar, Endpoint.TOP_HEADLINES);
	}
	

		// TODO implement me
		public void getDataForCustomInput() {
			try{
				Scanner scan= new Scanner(System.in);
				System.out.println("Q: ");
				String o=scan.nextLine();
				System.out.println("Category: ");
				Category ca = Category.valueOf(scan.next());
				System.out.println("Country: ");
				Country co = Country.valueOf(scan.next());
				System.out.println("Endpoint: ");
				Endpoint en = Endpoint.valueOf(scan.next());

				article = ctrl.process(o,ca,co,en);


			}catch (Exception e){
				System.out.println("Wrong keyword");
			}
		}

	public void getQuantityOfArticles(){
		ctrl.analyze1(article);
	}

	public void getProviderWithMostArticles(){
		ctrl.analyze2(article);
	}

	public void getAuthorWithShortestName(){
		ctrl.analyze3(article);
	}

	public void getLongestTitle(){
		ctrl.analyze4(article);
	}

	public void start() {
		Menu<Runnable> menu = new Menu<>("User Interface");
		menu.setTitle("Wählen Sie aus:");
		menu.insert("a", "Choice ABC", this::getDataFromCtrl1);
		menu.insert("b", "Choice DEF", this::getDataFromCtrl2);
		menu.insert("c", "Choice 3", this::getDataFromCtrl3);
		menu.insert("d", "Choice User Input:",this::getDataForCustomInput);

		menu.insert("e", " Quantity of articles:",this::getQuantityOfArticles);
		menu.insert("f", " Provider with the most aritcles:",this::getProviderWithMostArticles);
		menu.insert("g", " Author with the shortest name:",this::getAuthorWithShortestName);
		menu.insert("h", " Sort by longest title:",this::getLongestTitle);

		menu.insert("q", "Quit", null);
		Runnable choice;
		while ((choice = menu.exec()) != null) {
			 choice.run();
		}
		System.out.println("Program finished");
	}


    protected String readLine() {
		String value = "\0";
		BufferedReader inReader = new BufferedReader(new InputStreamReader(System.in));
		try {
			value = inReader.readLine();
        } catch (IOException ignored) {
		}
		return value.trim();
	}

	protected Double readDouble(int lowerlimit, int upperlimit) 	{
		Double number = null;
        while (number == null) {
			String str = this.readLine();
			try {
				number = Double.parseDouble(str);
            } catch (NumberFormatException e) {
                number = null;
				System.out.println("Please enter a valid number:");
				continue;
			}
            if (number < lowerlimit) {
				System.out.println("Please enter a higher number:");
                number = null;
            } else if (number > upperlimit) {
				System.out.println("Please enter a lower number:");
                number = null;
			}
		}
		return number;
	}
}
