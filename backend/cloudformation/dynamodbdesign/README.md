     
## Table design
1. wallets Table
- customerId // partition key, string
- walletName // sort key, string
- walletDescription // string
- cryptosCount // number
- cryptosCost // number
- cryptocurrenciesList // list

2. cryptocurrency Table
- cryptoName // partition key, string
- cryptoDescription // string
- cryptoAmount // number
- cryptoCost //GSI partition key, number

## Create Tables

1. Create the tables by running these aws CLI commands:
   ```none
   aws cloudformation create-stack --region us-west-2 --stack-name dynamodbindexes-cryptocurrencytable01 --template-body file://cloudformation/dynamodbdesign/cryptos_table.yaml --capabilities CAPABILITY_IAM
   aws cloudformation create-stack --region us-west-2 --stack-name dynamodbindexes-walletstable01 --template-body file://cloudformation/dynamodbdesign/wallets_table.yaml --capabilities CAPABILITY_IAM
   ```
