package it.greenvulcano.gestpay.wscryptdecrypt;

/**
 * 
 * @version 4.0 08/august/2016
 * @author GreenVulcano Developer Team
 */
public class EncryptResponse {

	  private String transactionType;
	  private String transactionResult;
	  private String cryptDecryptString;
	  private String errorCode;
	  private String errorDescription;

	  public String getTransactionType() {
	    return transactionType;
	  }

	  public void setTransactionType(String transactionType) {
	    this.transactionType = transactionType;
	  }

	  public String getTransactionResult() {
	    return transactionResult;
	  }

	  public void setTransactionResult(String transactionResult) {
	    this.transactionResult = transactionResult;
	  }

	  public String getCryptDecryptString() {
	    return cryptDecryptString;
	  }

	  public void setCryptDecryptString(String cryptDecryptString) {
	    this.cryptDecryptString = cryptDecryptString;
	  }

	  public String getErrorCode() {
	    return errorCode;
	  }

	  public void setErrorCode(String errorCode) {
	    this.errorCode = errorCode;
	  }

	  public String getErrorDescription() {
	    return errorDescription;
	  }

	  public void setErrorDescription(String errorDescription) {
	    this.errorDescription = errorDescription;
	  }


	  @Override
	  public String toString() {
	    return "EncryptResponse{" +
	        "transactionType='" + transactionType + '\'' +
	        ", transactionResult='" + transactionResult + '\'' +
	        ", cryptDecryptString='" + cryptDecryptString + '\'' +
	        ", errorCode='" + errorCode + '\'' +
	        ", errorDescription='" + errorDescription + '\'' +
	        '}';
	  }
	}
