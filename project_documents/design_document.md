# Development_Fans Cryptocurrency Wallet Service Design



## 1. Problem Statement

Today, many people have cryptocurrencies as an asset. At the same time,
the number of types of cryptocurrencies that they can own may be in the tens.
They can mine, buy and exchange cryptocurrencies and also store cryptocurrencies
on different cryptocurrency exchanges. Users want to have information about the assets
that they store in cryptocurrencies in one place.
Users also want to distribute their assets to different wallets in order
to accumulate the necessary assets for different projects.

This design document describes the Cryptocurrency Wallet Service, a new service 
that will provide the custom wallet functionality to meet customers' needs. 
It is designed to interact with the Cryptocurrency Wallet Service client,
which allows customers to get information about their wallets to their
phone or computer.


## 2. Top Questions to Resolve in Review

1. Will we have a common cryptocurrency exchange rate table?
2. Will we have a cryptocurrency table, which will store the list of available cryptocurrencies
in The Cryptocurrency Wallet Service?
3. How will we update cryptocurrencies' exchange rate?

## 3. Use Cases

U1. As a customer, I want to create a new, empty wallet with a given name and
a description.

U2. As a customer, I want to retrieve all my wallets list.

U3. As a customer, I want to retrieve my wallet with a given name.

U4. As a customer, I want to update my wallet description.

U5. As a customer, I want to add cryptocurrency to my wallet.

U6. As a customer, I want to update a cryptocurrency amount in my wallet.

U7. As a customer, I want to update the cryptocurrency exchange rate in all my wallets.

U8. As a customer, I want to retrieve all available cryptocurrencies that I can use
in my wallet.

## 4. Project Scope

### 4.1. In Scope

* Creating, retrieving, and updating a wallet
* Retrieving all customer wallets in the list
* Retrieving all available cryptocurrencies that we can use in our wallet

### 4.2. Out of Scope

* Creating a customer table that contains information about customers (customerId, Name, etc.). 
* Security
* Updating cryptocurrencies exchange rate from external API (at this stage, 
the available cryptocurrencies list and the cryptocurrencies exchange rate will be updated 
by the system administrator). 

# 5. Proposed Architecture Overview

This initial iteration will provide the minimum lovable product (MLP) including:
* Creating, retrieving, and updating a wallet
* Retrieving all customer wallets in the list
* Retrieving all available cryptocurrencies that we can use in our wallet

We will use API Gateway and Lambda to create nine endpoints (`GetAllWallets`, `GetWallet`,
`CreateWallet`, `UpdateWallet`, `AddCryptocurrencyToWallet`, `UpdateCryptocurrencyInWallet`, 
`UpdateExchangeRateInWallets`, and `GetAvailableCryptocurrencies`) that will handle the creation, update, 
and retrieval of wallets to satisfy our requirements.

We will store available cryptocurrencies that we can use in our wallet in a table in DynamoDB. Wallets
themselves will also be stored in DynamoDB. For simpler cryptocurrency list retrieval, we
will store the list of cryptocurrencies in a given wallet directly in the wallet
table.

Cryptocurrency Wallet Service will also provide a web interface for users to manage their wallets. 
A home page providing a list view of all of customer wallets will let them create new wallets and link off 
to pages, per-wallet to update metadata, add cryptocurrencies and change their amount in the wallet.

# 6. API

## 6.1. Public Models

```
// CryptocurrencyModel

String cryptoName;
String cryptoDescription;
Double cryptoAmount;
Double cryptoCost;
```

```
// WalletModel

String customerId;
String walletName;
String walletDescription;
Double cryptosCount;
Double cryptosCost;
List   cryptocurrenciesList;
```

## 6.2. GetAllWallets Endpoint

* Accepts `GET` requests to `/:customerId`
* Accepts a customerID and returns the corresponding List of wallet.
  * If the given customerID is not found, will return an empty List of wallet.
  * If the given customerID doesn't have any wallet, will return an empty List of wallet.

## 6.3. GetWallet Endpoint

* Accepts `GET` requests to `/:customerId/:walletName`
* Accepts customerID and walletName and returns the corresponding wallet.
    * If the wallet with the given customerId and walletName is not found, 
  will throw a `WalletNotFoundException`

## 6.4. CreateWallet Endpoint

* Accepts `POST` requests to `/:customerId/:walletName`
* Accepts data to create a new wallet with a provided customerId, a provided walletName,  
and walletDescription. Returns the new wallet.
* For security concerns, we will validate the provided walletName and customerId do not
  contain any invalid characters: `" ' \`
    * If the walletName or customerId contain any of the invalid characters, will throw an
      `InvalidAttributeValueException`.

## 6.5. UpdateWallet Endpoint

* Accepts `PUT` requests to `/:customerId/:walletName`
* Accepts data to update a wallet description. Returns the updated wallet.
  * If the Wallet with the given customerId and walletName is not found, will throw a `WalletNotFoundException`

## 6.6. AddCryptocurrencyToWallet Endpoint

* Accepts `POST` requests to `/:customerId/:walletName/cryptos/:cryptoName`
* Accepts a customerId, walletName, a cryptoName, and a cryptoAmount to be added. 
The cryptoName is specified by the cryptoName from the available cryptocurrency table. 
Returns the wallet with the added cryptocurrency.
    * If the given customerId is not found, will throw a `CustomerNotFoundException`
    * If the walletName is not found, will throw a `WalletNotFoundException`
    * If the given cryptoName doesn't exist, will throw a `CryptocurrencyNotFoundException`

## 6.7. UpdateCryptocurrencyInWallet Endpoint

* Accepts `PUT` requests to `/:customerId/:walletName/cryptos/:cryptoName`
* Accepts a customerId, walletName, a cryptoName and a cryptoAmount to be a new cryptoAmount.
  Returns the wallet with the updated cryptocurrency.
  The cryptoName is specified by the cryptoName from the available cryptocurrency table
  * If the given customerId is not found, will throw a `CustomerNotFoundException`
  * If the walletName is not found, will throw a `WalletNotFoundException`
  * If the given cryptoName doesn't exist, will throw a `CryptocurrencyNotFoundException`

## 6.8. UpdateExchangeRateInWallet Endpoint

* Accepts `PUT` requests to `/:customerId/:walletName/cryptos`
* Accepts a customerID and walletName, updates cryptocurrencies cost in the wallet
according to exchange rate getting from cryptocurrency table and returns the corresponding Wallet.
  * If the given customerID is not found, will return empty List of WalletModel.
  * If the given walletName is not found, will return empty List of WalletModel.

## 6.9. GetAvailableCryptocurrencies Endpoint

* Accepts `GET` requests to `/availablecryptos`
* Accepts a greaterThan and returns the list of available cryptocurrencies (which has cost greater than
greaterThan) with cryptoName, cryptoDescription, cryptoAmount (always 1 in this case), and CryptoCost.



# 7. Tables

### 7.1. `cryptocurrencies`

```
cryptoName // partition key, GSI sort key, string
cryptoDescription // string
cryptoAmount // number
cryptoCost // GSI partition key, number
```

### 7.2. `wallets`

```
customerId // partition key, string
walletName // sort key, string
walletDescription // string
cryptosCount // number
cryptosCost // number
cryptocurrenciesList // list
```

# 8. Pages


## 8.1. Scheme

![After entering customerId and clicking Login button on the Login Page, opens Home Page. 
Clicking Login Icon on the Home Page opens Login Page. Clicking "Update Exchange Rate" button on the Home Page 
updates cryptocurrencies exchange rate. 
After clicking on wallet name on the Home Page, opens the Wallet Page.
Clicking the Create button on the Home Page opens the Create Cryptocurrency Wallet Page. 
Clicking the Cancel button on the Create Cryptocurrency Wallet Page opens Home Page. Clicking
the Create button on the Create Cryptocurrency Wallet Page opens the Wallet Page.
Clicking Home Icon on the Wallet Page opens Home Page.
Clicking the Add button on the Wallet Page opens the Add Cryptocurrency page.  
Clicking Add button on the Add Cryptocurrency page opens the the Wallet Page.
Clicking Cancel button on the Add Cryptocurrency page opens the the Wallet Page.
Clicking the cryptoName on the Wallet Page opens the Update Cryptocurrency In Wallet page. 
Clicking the Update button on the Update Cryptocurrency In Wallet page opens the Wallet Page.
Clicking the Cancel button on the Update Cryptocurrency In Wallet page opens the Wallet Page.
](images/pages/OverallWorkflow.png)


## 8.2. Login Page
![The Login Page has a header that reads "My Cryptocurrency wallets" wirh a field customerId.
There is also a Login button.](images/pages/LoginPage.png)

## 8.3. Home Page

![The homepage has a header that reads "My Cryptocurrency wallets" It also displays the
user's alias. Each page has this header. Each wallet the user has is shown as
a box with the wallet name and any tags it has. There are also a Create
button and Login Icon.](images/pages/Homepage.png)

## 8.4. Create Cryptocurrency Wallet Page

![The Create Cryptocurrency Wallet Page says "Create Cryptocurrency wallet" with a field for name and
description. There is a "Create" button and 
Home Icon.](images/pages/CreateCryptocurrencyWalletPage.png)

## 8.5. Wallet Page

![The Wallet Page has the name of the wallet, description and total amount in USD. Each cryptocurrency 
in the wallet is listed. There is a Add button to add a new cryptocurrency to the wallet 
and Home Icon.](images/pages/WalletPage.png)

## 8.6. Update Cryptocurrency Amount In Wallet

![The Update Cryptocurrency Amount In Wallet page displays the walletName, 
the cryptoName and the cryptoAmount. There is an "Update" button to submit the form
and Home Icon.](images/pages/UpdateCryptocurrencyInWallet.png)

## 8.7. Add Cryptocurrency Page

![The Add Cryptocurrency page has a form titled "Add cryptocurrency." It displays the walletName
followed by fields for the cryptoName and cryptoAmount. There is a list of available
cryptos and button "Update Exchange Rate" for update current cryptocurrency exchange rate.
Finally, there is an "Add" button to submit the form 
and Home Icon.](images/pages/AddCryptocurrencyPage.png)