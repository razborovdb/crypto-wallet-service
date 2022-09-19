var customerId = "";
var walletName = "";
var params = "";

const walletPageForm = document.querySelector("#walletForm");
const cryptosTable = document.querySelector("#cryptosTableForm");

// Load Window
window.onload = async function(evt) {
    evt.preventDefault();
    document.getElementById("labelMessage").innerHTML = "Loading ...";
    params = new URLSearchParams(window.location.search);
    customerId = params.get("customerId");
    walletName = params.get("walletName");

    document.getElementById("labelCustomer").innerHTML = customerId;
    document.getElementById("labelWallet").innerHTML = walletName;

    var request = "https://c56ip1t5m3.execute-api.us-west-2.amazonaws.com/beta/" + customerId + "/" + walletName;
    const data = { };
    const headers = {
        'Content-Type': 'application/json',
        'x-api-key': 'J1GBGYozR04Oeu1opXuZuOFiEJA3jwPVAE6enhc0'
    };

    axios.get(request,{headers})
        .then(function(response) {
            document.getElementById("labelMessage").innerHTML = "Done";
            populateWalletCryptos(response.data.wallet);
        })
        .catch(function(error) {
            document.getElementById("labelMessage").innerHTML = "Error";
        })
        .finally(function() {

    });
    alert("Get data for wallet: "+walletName + " for customer: "+customerId);
}
// Press Update Button
function pressUpdateButton() {
    doUpdateWallet();
}
// Update Wallet
function doUpdateWallet() {
    document.getElementById("labelMessage").innerHTML = "Updating ...";
    var walletDescription = document.getElementById("labelWalletDescriptionValue").value;
    var request = "https://c56ip1t5m3.execute-api.us-west-2.amazonaws.com/beta/" + customerId + "/" + walletName;
    const data = { "walletDescription": walletDescription };
    const headers = {
        'Content-Type': 'application/json',
        'x-api-key': 'J1GBGYozR04Oeu1opXuZuOFiEJA3jwPVAE6enhc0'
    };
    axios.put(request, data, {headers})
        .then(function(response) {
            document.getElementById("labelMessage").innerHTML = "Done";
        })
        .catch(function(error) {
            document.getElementById("labelMessage").innerHTML = "Error";
        })
        .finally(function() {
            window.location.reload();
    });
    alert("Update wallet: "+walletName + " for customer: "+customerId);
}
// Press Add Button
function pressAddButton() {
    window.open("addcryptopage.html?customerId="+ customerId+"&walletName="+ walletName, "_self");
}
// Press Home Page Button
function pressHomePageButton() {
    sessionStorage.setItem("customerId", customerId);
    window.open("homepage.html", "_self");
}
// Populate Wallet Cryptos
function populateWalletCryptos(walletData) {
    document.getElementById("labelWalletDescriptionValue").value = walletData.walletDescription;
    document.getElementById("cryptosCountValueLabel").innerHTML = walletData.cryptosCount;
    document.getElementById("cryptosCostValueLabel").innerHTML = walletData.cryptosCost;
    var cryptosData = walletData.cryptocurrenciesList;

    let th1 = document.createElement("th");
    let th2 = document.createElement("th");
    let th3 = document.createElement("th");
    let th4 = document.createElement("th");
    let tr = document.createElement("tr");
    let text1 = document.createTextNode("Crypto Name");
    let text2 = document.createTextNode("Crypto Description");
    let text3 = document.createTextNode("Crypto Amount");
    let text4 = document.createTextNode("Crypto Cost");
    th1.appendChild(text1);
    th2.appendChild(text2);
    th3.appendChild(text3);
    th4.appendChild(text4);
    tr.appendChild(th1);
    tr.appendChild(th2);
    tr.appendChild(th3);
    tr.appendChild(th4);

    cryptosTable.appendChild(tr);

    for (let c of cryptosData) {
        let td1 = document.createElement("td");
        let td2 = document.createElement("td");
        let td3 = document.createElement("td");
        let td4 = document.createElement("td");
        let a = document.createElement("a");
        a.setAttribute('href', `./updatecryptoinwalletpage.html?customerId=${customerId}&walletName=${walletName}&cryptoName=${c.cryptoName}`);
        let txt1 = document.createTextNode(c.cryptoName);
        let txt2 = document.createTextNode(c.cryptoDescription);
        let txt3 = document.createTextNode(c.cryptoAmount);
        let txt4 = document.createTextNode(c.cryptoCost);
        a.appendChild(txt1);
        td1.appendChild(a);
        td2.appendChild(txt2);
        td3.appendChild(txt3);
        td4.appendChild(txt4);
        let trr = document.createElement("tr");
        trr.appendChild(td1);
        trr.appendChild(td2);
        trr.appendChild(td3);
        trr.appendChild(td4);
        cryptosTable.appendChild(trr);
    }
}