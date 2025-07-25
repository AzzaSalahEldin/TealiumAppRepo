package tests;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

    @Epic("Tealium App")
    @Feature("Add Product to Shopping Cart")
    @Listeners({io.qameta.allure.testng.AllureTestNg.class})
    public class AddToCartTest extends BaseTest{

    @Test(description = "Verify Accessories category is hoverable")
    @Story("Hover interaction")
    @Severity(SeverityLevel.NORMAL)
    @Description("Checks that the Accessories menu is visible and interactive when hovered")
    public void verifyAccessoriesCategoryIsHoverable() {
        Assert.assertTrue(dashboardPage.isAccessoriesMenuVisible(), "Accessories menu should be visible on hover.");
    }

    @Test(description = "Verify sorting by price works")
    @Story("Sorting")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Ensures the shoes listing is sorted in ascending price order when filter is applied")
     public void verifyShoesSortedAscendingByPrice() {
        dashboardPage.goToShoesCategory();
        accessories.sortByPriceLowToHigh();
        List<Double> prices = accessories.getAllDisplayedPrices();
        List<Double> sortedPrices = new ArrayList<>(prices);
        Assert.assertEquals(prices, sortedPrices, "Shoes should be sorted by price (low to high).");
    }

    @Test(description = "Verify product details page opens")
    @Story("Product Navigation")
    @Severity(SeverityLevel.NORMAL)
    @Description("Ensure clicking a product navigates to the correct details page")
    public void verifyProductDetailsPageNavigation() {
        dashboardPage.goToShoesCategory();
        accessories.openProductDetails("Dorian Perforated Oxford");
        Assert.assertTrue(accessories.isProductDetailsDisplayed("Dorian Perforated Oxford").contains("DORIAN PERFORATED OXFORD"), "Product details page should open for 'Dorian Perforated Oxford'.");
    }

    @Test(description = "Verify color and size selection")
    @Story("Product Selection")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Ensure user can select color and size before adding to cart")
    public void verifyColorAndSizeSelection() {
        dashboardPage.goToShoesCategory();
        details.selectColor("Black");
        details.selectSize("8");
        Assert.assertTrue(details.isColorSelected().equals("Black"), "Color should be selected.");
        Assert.assertTrue(details.isSizeSelected().equals("8"), "Size should be selected.");
    }

    @Test(description = "Verify add to cart confirmation")
    @Story("Add to Cart")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Ensure confirmation message is shown after adding to cart")
    public void verifyAddToCartConfirmation() {
        loginPage.login("validuser@mail.com", "correctpass");
        dashboardPage.goToShoesCategory();
        details.addToWishList("Ann Ankle Boot");
        Assert.assertTrue(details.getCurrentUrl("wishlist/index/index/wishlist_id").contains("wishlist/index/index/wishlist_id"), "User should be redirected to wishlist page.");
        Assert.assertTrue(details.assertOnSuccessMessage("Ann Ankle Boot"));
        details.addToCart("Ann Ankle Boot");
        cart.addToCart("8");
        Assert.assertTrue(details.getCurrentUrl("/checkout/cart/").contains("/checkout/cart/"), "User should be redirected to checkout page.");
        Assert.assertTrue(cart.isConfirmationDisplayed(), "Confirmation message should appear after adding to cart.");
    }
}
