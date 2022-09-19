var customerId = "";
var walletName = "";
var cryptoName = "cryptoName";
var cryptoDescription = "cryptoDescription";
var cryptoAmount = "0.0";
var params = "";
const cryptosTable = document.querySelector("#cryptosTableForm");
// Load window
window.onload = async function(evt) {
    evt.preventDefault();
    document.getElementById("labelMessage").innerHTML = "Loading ...";
    params = new URLSearchParams(window.location.search);
    customerId = params.get("customerId");
    walletName = params.get("walletName");
    //
    var request = "https://c56ip1t5m3.execute-api.us-west-2.amazonaws.com/beta/availablecryptos?greaterThan=0.0";
    const data = { };
    const headers = {
        'Content-Type': 'application/json',
        'x-api-key': 'J1GBGYozR04Oeu1opXuZuOFiEJA3jwPVAE6enhc0'
    };

    axios.get(request,{headers})
        .then(function(response) {
            document.getElementById("labelMessage").innerHTML = "Done";
            populateCrypto();
            populateAvailableCryptos(response.data.cryptocurrencyList);
        })
        .catch(function(error) {
            document.getElementById("labelMessage").innerHTML = "Error";
        })
        .finally(function() {

    });
    alert("Load available cryptos");
}
// Press Add Button
function pressAddButton() {
    doAddCrypto();
}
// Add Crypto
async function doAddCrypto() {
    document.getElementById("labelMessage").innerHTML = "Adding ...";
    var cryptoName = document.getElementById("labelCrypto").value;
    var cryptoDescription = document.getElementById("labelCryptoDescriptionValue").value;

    var cryptoAmount = document.getElementById("labelAmountValue").value;

    var request = "https://c56ip1t5m3.execute-api.us-west-2.amazonaws.com/beta/" + customerId + "/" + walletName+ "/cryptos/" +cryptoName;

    const data = {
        "cryptoDescription": cryptoDescription,
        "cryptoAmount": cryptoAmount
    };
    const headers = {
        'Content-Type': 'application/json',
        'x-api-key': 'J1GBGYozR04Oeu1opXuZuOFiEJA3jwPVAE6enhc0'
    };

    axios.post(request, data, {headers})
        .then(function(response) {
            document.getElementById("labelMessage").innerHTML = "Done";
        })
        .catch(function(error) {
            document.getElementById("labelMessage").innerHTML = "Error";
        })
        .finally(function() {
            window.open("walletpage.html?customerId="+ customerId+"&walletName="+ walletName, "_self");
    });
    alert("Add crypto: " + cryptoName + " to wallet: " + walletName + "for customer: " + customerId);
}
// Press Wallet Page Button
function pressWalletPageButton() {
    window.open("walletpage.html?customerId="+ customerId+"&walletName="+ walletName, "_self");
}
// Populate Common Data
function populateCrypto() {
    document.getElementById("labelCustomer").innerHTML = customerId;
    document.getElementById("labelWallet").innerHTML = walletName;
    //document.getElementById("labelCrypto").value = cryptoName;
    //document.getElementById("labelCryptoDescriptionValue").value = cryptoDescription;
    //document.getElementById("labelAmountValue").value = cryptoAmount;
}
// Populate Cryptos Data
function populateAvailableCryptos(cryptosData) {
    let th1 = document.createElement("th");
    let th2 = document.createElement("th");
    let th3 = document.createElement("th");
    let tr = document.createElement("tr");
    let text1 = document.createTextNode("Crypto Name");
    let text2 = document.createTextNode("Crypto Description");
    let text3 = document.createTextNode("Exchange Rate");
    th1.appendChild(text1);
    th2.appendChild(text2);
    th3.appendChild(text3);
    tr.appendChild(th1);
    tr.appendChild(th2);
    tr.appendChild(th3);

    cryptosTable.appendChild(tr);

    for (let c of cryptosData) {
        let td1 = document.createElement("td");
        let td2 = document.createElement("td");
        let td3 = document.createElement("td");
        let txt1 = document.createTextNode(c.cryptoName);
        let txt2 = document.createTextNode(c.cryptoDescription);
        let txt3 = document.createTextNode(c.cryptoCost);

        td1.appendChild(txt1);
        td2.appendChild(txt2);
        td3.appendChild(txt3);
        let trr = document.createElement("tr");
        trr.appendChild(td1);
        trr.appendChild(td2);
        trr.appendChild(td3);
        cryptosTable.appendChild(trr);
    }
}