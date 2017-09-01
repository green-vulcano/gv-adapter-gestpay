[![gv-logo](img/logo.png)](http://www.greenvulcanotechnologies.com)

[<img src="img/index.png" width="32">](index.md)

----
### Make Payment
Perform the transaction.

-**Payload**

```
    {

        "shopLogin":<shopLogin --- required>,
        "uicCode":<uicCode --- required>,
        "amount":<amount --- required>,
        "shopTransID":<shopTransID --- required>,
        "bankTransID":<bankTransID>,
        "fullFillment":<fullFillment>
    }
```

-**Response**

```
{
  "ErrorDescription": "<ErrorDescription>",
  "AuthorizationCode": <AuthorizationCode>,
  "TransactionType": "<TransactionType>",
  "TransactionResult": "<TransactionResult>",
  "TransactionKey": <TransactionKey>,
  "AVSResultCode": "<AVSResultCode>",
  "Amount": <Amount>,
  "TDLevel": "<TDLevel>",
  "AVSResultDescription": "<AVSResultDescription>",
  "Buyer": {
    "BuyerEmail": "<BuyerEmail>",
    "BuyerName": "<BuyerName>"
  },
  "VbV": {
    "VbVFlag": "<VbVFlag>",
    "VbVBuyer": "<VbVBuyer>",
    "VbVRisp": "<VbVRisp>"
  },
  "AlertDescription": "<AlertDescription>",
  "ShopTransactionID": "<ShopTransactionID>",
  "RiskResponseCode": "<RiskResponseCode>",
  "RiskResponseDescription": "<RiskResponseDescription>",
  "AlertCode": "<AlertCode>",
  "Currency": <Currency>,
  "Country": "<Country>",
  "CustomInfo": "<CustomInfo>",
  "ErrorCode": <ErrorCode>,
  "BankTransactionID": BankTransactionID
}
```
