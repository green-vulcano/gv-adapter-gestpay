[![gv-logo](img/logo.png)](http://www.greenvulcanotechnologies.com)

[<img src="img/index.png" width="32">](index.md)

----
### Make Payment
Perform the transaction.

-**Payload**
Use this payload if you choose to pay with the credit card. Parameters in **bold** are mandatory.
<pre>{
   <b>"shopLogin":"< shopLogin >",
   "uicCode":"< uicCode >",
   "amount":"< amount >",
   "shopTransactionId":"< shopTransactionId >"</b>
   "cardNumber":"< cardNumber >",
   "expiryMonth":"< expiryMonth >",
   "expiryYear":"< expiryYear >"
}
</pre>

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
