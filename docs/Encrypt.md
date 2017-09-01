[![gv-logo](img/logo.png)](http://www.greenvulcanotechnologies.com)

[<img src="img/index.png" width="32">](index.md)

----
### Encrypt

Is the main entrypoint for Gestpay. You must call Encrypt either if you want to use the standard payment page, or the custom iframe solution.
	
- **Payload**:

<pre>
        {
            <b>"shopLogin":<shopLogin>,
            "uicCode":<uicCode>,
            "amount":<amount>,
            "shopTransactionId":<shopTransactionId></b>,
            "cardNumber":<cardNumber>,
            "expiryYear":<expiryMonth>,
            "expiryYear":<expiryYear>,
            "buyerName":<buyerName>,
            "buyerEmail":<buyerEmail>,
	    "languageId":<languageId>,
            "cvv":<cvv>,
            "customInfo":<customInfo>,
            "requestToken":<requestToken>,
            "ppSellerProtection":<ppSellerProtection>,
            "shippingDetails":<shippingDetails>,
            "paymentTypes":<paymentTypes>,
            "paymentTypeDetail":<paymentTypeDetail>,
            "redFraudPrevention":<redFraudPrevention>,
            "redCustomerInfo":<redCustomerInfo>,
            "redShippingInfo":<redShippingInfo>,
            "redBillingInfo":<redBillingInfo>,
            "redCustomerData":<redCustomerData>,
            "redCustomInfo":<redCustomInfo>,
            "redItems":<redItems>,
            "conselMerchantPro":<conselMerchantPro>,
            "conselCustomerInfo":<conselCustomerInfo>,
            "payPalBillingAgreementDescription":<payPalBillingAgreementDescription>,
            "orderDetails":<orderDetails>   
        }
</pre>


- **Response**
    ```
    {
        "ErrorDescription":"<ErrorDescription>",
        "TransactionType":"<TransactionType>",
        "TransactionResult":"<TransactionResult>",
        "CryptDecryptString":<CryptDecryptString>,
        "ErrorCode":<ErrorCode>
        
    }
```
