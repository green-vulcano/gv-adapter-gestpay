package it.greenvulcano.gestpay.wscryptdecrypt;

/**
 * 
 * @version 4.0 08/august/2016
 * @author GreenVulcano Developer Team
 */
public class DecryptRequest {

	  private String shopLogin;
	  private String cryptedString;

	  public String getCryptedString() {
	    return cryptedString;
	  }

	  public void setCryptedString(String cryptedString) {
	    this.cryptedString = cryptedString;
	  }

	  public String getShopLogin() {

	    return shopLogin;
	  }

	  public void setShopLogin(String shopLogin) {
	    this.shopLogin = shopLogin;
	  }
	}
