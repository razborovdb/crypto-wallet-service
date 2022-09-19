# Development_Fans Project Rubric

## Deliverables

| Deliverable                                                                  |Due Date                  | Date Completed |URL                               |
|------------------------------------------------------------------------------|---                       |----------------|---                               |
| Team name                                                                    |Sprint 1 Module 1         | 09/06/2022     |name: Development_Fans            |
| [Design Document - problem statement](design_document.md)                    |Sprint 1 Module 2         | 09/08/2022     | https://github.com/BloomTechBackend/bd-team-project-development_fans/blob/main/project_documents/design_document.md                                 |
| [Team Charter](team_charter.md)                                              |Sprint 1 Module 3         | 09/08/2022     |  https://github.com/BloomTechBackend/bd-team-project-development_fans/blob/main/project_documents/team_charter.md                                |
| [Design Document](design_document.md)                                        |Sprint 1 REQUIRED TO GO ON| 09/09/2022     | https://github.com/BloomTechBackend/bd-team-project-development_fans/blob/main/project_documents/design_document.md                                 |
| Project Completion (Feature Complete)                                        |Sprint 3                  | 09/18/2022     | https://github.com/BloomTechBackend/bd-team-project-development_fans                                 |
| [Team Reflection](reflection.md)                                             |Sprint 3                  | 09/18/2022     | https://github.com/BloomTechBackend/bd-team-project-development_fans/blob/main/project_documents/Development_Fans/reflection.md                                 |
| [Accomplishment Tracking Dmitry Razborov](accomplishment_tracking_dmitry.md) |Sprint 3                  | 09/18/2022     | https://github.com/BloomTechBackend/bd-team-project-development_fans/blob/main/project_documents/accomplishment_tracking_dmitry.md                                 |
| Self Reflection                                                              |Sprint 3                  |                |n/a (will be submitted via Canvas - "Wrap-up" section) |

## Technical Learning Objectives

### API Design

**Design an API to meet the needs of your application.** 

Endpoint definition location: 
* https://cryptocurrency-wallet-api-doc.s3.us-west-2.amazonaws.com/index.html 

What we learned:
1. Creating an API document using the Swagger editor
2. Using the document *.yaml for creating API gateway endpoints

**Develop a service endpoint definition that uses complex inputs and outputs. (example)**

GetWallet Endpoint `/:customerId/wallets/:walletName`

Input object(s):
- attribute 1: customerId
- attribute 2: walletName

Output object(s):    

- attribute 1: customerId
- attribute 2: walletName
- attribute 3: walletDescription
- attribute 4: cryptosCount
- attribute 5: cryptosCost
- attribute 6: cryptocurrenciesList
  - cryptoName
  - cryptoDescription
  - cryptoAmount
  - cryptoCost

**Selected one of endpoints that accepts input values from a client. List the
error cases you identified and how the service responds in each case. Provide at
most 3 error cases.**

| **Endpoint:**        | CreateWallet Endpoint `/:customerId/wallets/:walletName` |
|----------------------|----------------------------------------------------------|
| **Error case**       | **Service response**                                     |
| Invalid customerId   | throw InvalidAttributeValueException                     |
| Invalid walletName   | throw InvalidAttributeValueException                     |
| Wallet isn't created | throw WalletNotCreatedException                          |

**Develop a service endpoint definition that uses query parameters to determine
how results are sorted or filtered.** List at least one endpoint that allows
sorting or filtering of results. Which attribute(s) can be sorted/filtered on?

GetAvailableCryptocurrencies Endpoint:         
- Filtered attribute: cryptosCost  

**Determine whether a given error condition should result in a client or server
exception.**

|                       |**Exception** | **One case in which it is thrown** |
|---	                |---	       |------------------------------------|
|**Client exception:**  |	InvalidAttributeValueException           | Invalid customerId	                |
|**Service exception:** |	WalletNotCreatedException           | Wallet isn't created	                                  |

### DynamoDB Table Design

**DynamoDB tables in the project:** 

1. `cryptocurrencies` 
2. `wallets`

**Design a DynamoDB table key schema that allows items to be uniquely
identified.** 

1. `cryptocurrencies`
   - cryptoName // partition key

     Allow define unique primary key.
2. `wallets`
   - customerId // partition key 
   - walletName // sort key
     
     Allow define unique primary key.
3. 
**Design the attributes of a DynamoDB table given a set of use cases.** 

**Table name:** `cryptocurrencies`  
 
**Attributes:**

|Attribute name | (One) relevant use case                  | attribute purpose                |
|---            |------------------------------------------|----------------------------------|
| cryptoName              | Use to define cryptocurrency name        | Store cryptocurrency name        |
| cryptoDescription              | Use to define cryptocurrency description | Store cryptocurrency description |
| cryptoAmount              | Use to define cryptocurrency  amount     | Store cryptocurrency amount      |
| cryptoCost              | Use to define cryptocurrency cost        | Store cryptocurrency cost        ||

### DynamoDB Indexes

**Design a GSI key schema and attribute projection that optimizes queries not
supported by a provided DynamoDB table.** 

**Table name:**`cryptocurrencies`

**Table keys:**
- cryptoName // partition key

**GSI keys:**
- cryptoCost // GSI partition key
- cryptoName // GSI sort key

**Use case for GSI:**
Retrieve cryptocurrencies that greater than defined cryptoCost value.

**Table name:**`cryptocurrencies`

**Use case for GSI:** GetAvailableCryptocurrencies Endpoint

## Soft(er) Outcomes

**Learn a new technology.** 

| Team Member     | Something new the team member learned                                                                             |   
|-----------------|-------------------------------------------------------------------------------------------------------------------|
| Dmitry Razborov | 1. Using Trello Workspace for team working<br/>2. Using the Swagger Editor to create API documents<br/> 3. Using HTML/CSS/JS |

**Search terms:**   

- access to DynamoDB from Web application
- 
**Helpful resource:**      

https://docs.aws.amazon.com/amazondynamodb/latest/developerguide/AccessingDynamoDB.html

**Find material online to learn new technical topics.**

**Topic:**

- JavaScript

**Resource:**

https://developer.mozilla.org/en-US/docs/Web/JavaScript


