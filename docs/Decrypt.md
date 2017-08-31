[![gv-logo](img/logo.png)](http://www.greenvulcanotechnologies.com)

[<img src="img/index.png" width="32">](index.md)

----
### Decrypt
Communicates the payment transaction result to the merchant through an encrypted string .
- **Payload**:

        
   ```	
    {
        "shopLogin":<shopLogin>,
        "cryptedString":<cryptedString>
    }
   ```
- **Response**:

```
    {
        "TransactionType": "<TransactionType>",
        "TransactionKey": "<TransactionKey>",
        "AVSResultCode": "<AVSResultCode>",
        "TDLevel": "<TDLevel>",
        "Buyer": {
                "BuyerEmail": "<BuyerEmail>",
                "BuyerName": "<BuyerName>"
        },
        "VbVRisp": "<VbVRisp>",
        "ShopTransactionID": "<ShopTransactionID>",
        "RiskResponseDescription": "<RiskResponseDescription>",
        "Currency":<Currency>,
        "CustomInfo": <CustomInfo>,
        "MaskedPAN": "MaskedPAN",
        "TOKEN": "<TOKEN>",
        "BankTransactionID": <BankTransactionID>,
        "ErrorDescription": "<ErrorDescription>",
        "AuthorizationCode": <AuthorizationCode>,
        "TransactionResult": "<TransactionResult>",
        "VbVFlag": "VbVFlag",
        "Amount": <Amount>,
        "VbVBuyer": VbVBuyer,
        "AVSResultDescription": "<AVSResultDescription>",
        "AlertDescription": "<AlertDescription>",
        "RiskResponseCode": "<RiskResponseCode>",
        "AlertCode": "<AlertCode>",
        "Country": "<Country>",
        "ErrorCode": <ErrorCode>,
        "TokenExpiryMonth": "<TokenExpiryMonth>",
        "TokenExpiryYear": <TokenExpiryYear>
    }
```
