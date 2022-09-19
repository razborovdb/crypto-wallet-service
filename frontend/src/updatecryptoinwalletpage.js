var customerId = "";
var walletName = "";
var cryptoName = "";
var params = "";
// Load Window
window.onload = async function(evt) {
    document.getElementById("labelMessage").innerHTML = "Loading ...";
    evt.preventDefault();

    params = new URLSearchParams(window.location.search);
    customerId = params.get("customerId");
    walletName = params.get("walletName");
    cryptoName = params.get("cryptoName");

    var request = "https://c56ip1t5m3.execute-api.us-west-2.amazonaws.com/beta/" + customerId + "/" + walletName;
    const data = { };
    const headers = {
        'Content-Type': 'application/json',
        'x-api-key': 'J1GBGYozR04Oeu1opXuZuOFiEJA3jwPVAE6enhc0'
    };
    axios.get(request,{headers})
        .then(function(response) {
            document.getElementById("labelMessage").innerHTML = "Done";
            populateCrypto(response.data.wallet.cryptocurrenciesList);
        })
        .catch(function(error) {
            document.getElementById("labelMessage").innerHTML = "Error";
        })
        .finally(function() {

    });
    alert("Get data for crypto:" + cryptoName + "in wallet: "+walletName + " for customer: "+customerId);
}
// Press Update Button
function pressUpdateButton() {
    doUpdateCrypto();
}
// Update Crypto
async function doUpdateCrypto() {
    document.getElementById("labelMessage").innerHTML = "Updating ...";
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
    axios.put(request, data, {headers})
        .then(function(response) {
            document.getElementById("labelMessage").innerHTML = "Done";
        })
        .catch(function(error) {
            document.getElementById("labelMessage").innerHTML = "Error";
        })
        .finally(function() {
            var url = "walletpage.html?customerId="+ customerId+"&walletName="+ walletName;
            window.open(url, "_self");
    });
    alert("Update crypto: " + cryptoName + " in wallet: "+walletName + " for customer: "+customerId);
}
// Press Wallet Page Button
function pressWalletPageButton() {
    window.open("walletpage.html?customerId="+ customerId+"&walletName="+ walletName, "_self");
}
// Populate Crypto
function populateCrypto(cryptosData) {
    document.getElementById("labelCustomer").innerHTML = customerId;
    document.getElementById("labelWallet").innerHTML = walletName;
    document.getElementById("labelCrypto").innerHTML = cryptoName;
    for (let c of cryptosData) {
        let s = cryptoName + " ; " + c.cryptoName;
        if (cryptoName == c.cryptoName) {
            document.getElementById("labelCryptoDescriptionValue").value = c.cryptoDescription;
            document.getElementById("labelAmountValue").value = c.cryptoAmount;
        }
    }
}