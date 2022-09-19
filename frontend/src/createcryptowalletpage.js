var customerId = "";
var walletName = "";
var params = "";
// Load Window
window.onload = async function(evt) {
    document.getElementById("labelMessage").innerHTML = "Loading ...";
    params = new URLSearchParams(window.location.search); //,
    customerId = params.get("customerId");

    document.getElementById("labelCustomer").innerHTML = customerId;
    document.getElementById("labelMessage").innerHTML = "Done";
}
// Press Create Button
function pressCreateButton() {
    doCreateWallet();
}
// Create
async function doCreateWallet() {
    document.getElementById("labelMessage").innerHTML = "Creating ...";
    var walName = document.getElementById("labelWallet").value;
    var walletDescription = document.getElementById("labelWalletDescriptionValue").value;

    var request = "https://c56ip1t5m3.execute-api.us-west-2.amazonaws.com/beta/" + customerId + "/" + walName;

    const data = { "walletDescription": walletDescription };
    const headers = {
        'Content-Type': 'application/json',
        'x-api-key': 'J1GBGYozR04Oeu1opXuZuOFiEJA3jwPVAE6enhc0'
    };
    axios.post(request, data, {headers})
        .then(function(response) {
            document.getElementById("labelMessage").innerHTML = "Done";
            sessionStorage.setItem("customerId", customerId);
            window.open("homepage.html", "_self");
        })
        .catch(function(error) {
            document.getElementById("labelMessage").innerHTML = "Error";
            sessionStorage.setItem("customerId", customerId);
            window.open("homepage.html", "_self");
        })
        .finally(function() {

    });
    alert("Create wallet: "+walName + " for customer: "+customerId);
}
// Press Home Button
function pressHomePageButton() {
    sessionStorage.setItem("customerId", customerId);
    window.open("homepage.html", "_self");
}


