package utils.enums;

public enum HeaderMenu {
    LOGO("//img[@alt='logo']"),
    SEARCH("//a[@href='/search']"),
    LET_THE_CAR_WORK("//a[@href='/let-car-work']"),
    TERMS_OF_USE("//a[@href='/terms-of-use']"),
    SIGN_UP("//a[@href='/registration?url=%2Fsearch']"),
    LOGIN("//a[@href='/login?url=%2Fsearch']"),
    LOGOUT("//a[@href='/logout?url=%2Fsearch']"),
    DELETE_ACCOUNT("//div[@class='header']//a[text()='Delete account']");

    private final String locator;

    HeaderMenu(String locator) {
        this.locator = locator;
    }

    public String getLocator() {
        return locator;
    }
}
