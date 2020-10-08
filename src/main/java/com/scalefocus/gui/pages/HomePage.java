package com.scalefocus.gui.pages;

import java.util.List;

import com.scalefocus.gui.components.WeValuePrivacyAd;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import com.scalefocus.sqat_java.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.scalefocus.sqat_java.core.gui.AbstractPage;
import com.scalefocus.gui.components.FooterMenu;
import org.apache.log4j.Logger;

public class HomePage extends AbstractPage {
    private Logger LOGGER = Logger.getLogger(HomePage.class);

    @FindBy(id = "footmenu")
    private FooterMenu footerMenu;

    @FindBy(xpath = "//div[contains(@class, 'brandmenu-v2')]//a")
    private List<ExtendedWebElement> brandLinks;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public FooterMenu getFooterMenu() {
        return footerMenu;
    }

    public BrandModelsPage selectBrand(String brand) {
        LOGGER.info("selecting '" + brand + "' brand...");
        for (ExtendedWebElement brandLink : brandLinks) {
            String currentBrand = brandLink.getText();
            LOGGER.info("currentBrand: " + currentBrand);
            if (brand.equalsIgnoreCase(currentBrand)) {
                brandLink.click();
                return new BrandModelsPage(driver);
            }
        }
        throw new RuntimeException("Unable to open brand: " + brand);
    }
    public WeValuePrivacyAd getWeValuePrivacyAd() {
        return new WeValuePrivacyAd(driver);
    }
}
