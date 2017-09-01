[![gv-logo](img/logo.png)](http://www.greenvulcanotechnologies.com)

[<img src="img/index.png" width="32">](index.md)

----
### Delete Payment
Delete an authorized transaction.

- **Payload**:
<pre>

        {
           <b> "shopLogin":< shopLogin --- required >,</b>
            "shopTransactionId":<shopTransactionId --- required>,
            "bankTransactionId":<bankTransactionId --- required>,
            "cancelReason":<cancelReason>
        }

</pre>
- **Response**:

```
    {
        "ErrorDescription": "<ErrorDescription>",
        "TransactionType": "<TransactionType>",
        "TransactionResult": "<TransactionResult>",
        "ShopTransactionID": "<ShopTransactionID>",
        "ErrorCode": <ErrorCode>,
        "BankTransactionID": <BankTransactionID>
    }
```
