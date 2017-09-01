[![gv-logo](img/logo.png)](http://www.greenvulcanotechnologies.com)

[<img src="img/index.png" width="32">](index.md)

----
### Delete Payment
Delete an authorized transaction.

- **Payload**:
<pre>

        {
           <b> "shopLogin":< shopLogin >,</b>
            "shopTransactionId":< shopTransactionId >,
            "bankTransactionId":< bankTransactionId >,</b>
            "cancelReason":< cancelReason >
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
