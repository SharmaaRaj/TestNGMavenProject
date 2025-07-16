package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws InterruptedException {
        PageDetailsWiki pg = new PageDetailsWiki();
        pg.launchUrl("https://www.wikipedia.org/");
        pg.enterText("Selenium WebDriver");
        pg.languageSelection("Deutsch");
        pg.searchIcon();
        pg.validateHeader("Suchergebnisse");
    }

}