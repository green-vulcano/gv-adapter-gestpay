[![gv-logo](img/logo.png)](http://www.greenvulcanotechnologies.com)

[<img src="img/index.png" width="32">](index.md)

----
### Settle
Merchant can use this method to settle the transaction. Parameters in **bold** are mandatory

-**Payload**:

<pre>
    {
   <b> "shopLogin":< shopLogin >,
    "uicCode":< uicCode >,
    "amount":< amount >,
    "shopTransID":< shopTransID >,
    "bankTransID":< bankTransID ></b>,
    "fullFillment":< fullFillment >
    }
</pre>

-**Response**:

```
{
   "TransactionType":"<TransactionType>",
   "TransactionResult":"<TransactionResult>",
   "ShopTransactionID":"<ShopTransactionID>",
   "BankTransactionID":<BankTransactionID>,
   "ErrorCode":<ErrorCode>,
   "ErrorDescription":<ErrorDescription>
}
```
