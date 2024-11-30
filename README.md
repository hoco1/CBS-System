1. **CSR.java**
   - Represents a Customer Service Representative.
   - Attributes: `csrId`, `csrName`, `password`, `phoneNumber`, `available`.
   - Relationships: Many-to-Many with `CSRAuthority`, One-to-Many with `Offer`.

2. **CSRAuthority.java**
   - Represents the authority roles of CSRs.
   - Attributes: `authorityId`, `role`.
   - Relationships: Many-to-Many with `CSR`.

3. **Offer.java**
   - Represents an offer created by a CSR.
   - Attributes: `offerId`, `offerName`, `dataLimitMB`, `validityDays`, `offerType`.
   - Relationships: Many-to-One with `CSR`, One-to-Many with `SubscriberOffer`.

4. **SubscribedOfferMappingId.java**
   - Composite key for mapping `Subscriber` and `Offer`.
   - Attributes: `subscriberId`, `offerId`.

5. **Subscriber.java**
   - Represents a subscriber.
   - Attributes: `subscriberId`, `customerName`, `msisdn`, `subscriberType`, `creditLimit`, `prepayment`.
   - Relationships: One-to-Many with `SubscriberOffer`.

6. **SubscriberOffer.java**
   - Represents an offer subscribed by a subscriber.
   - Attributes: `dataUsedMB`, `dataUnusedMB`, `createTime`, `expiryTime`.
   - Relationships: Many-to-One with `Subscriber` and `Offer`.

7. **SubscriberType.java**
   - Enum representing subscriber types: `PREPAID`, `POSTPAID`.

8. **SubscriberTypeConverter.java**
   - Converts `SubscriberType` to a database column and vice versa.

  ![image](https://github.com/user-attachments/assets/a94698a3-d56c-4702-8a20-09ec9125cafd)
