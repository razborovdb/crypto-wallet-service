var customerId = "";
const customerId2 = "1234";

const homePageForm = document.querySelector("#homeForm");
const walletList = document.querySelector("#wallets");
const walletTable = document.querySelector("#walletTable");

// Load Windows
window.onload = async function(evt) {
    evt.preventDefault();

    document.getElementById("labelMessage").innerHTML = "Loading ...";
    customerId = sessionStorage.getItem("customerId");
    document.getElementById("labelCustomer").innerHTML = customerId;
    //
    var request = "https://c56ip1t5m3.execute-api.us-west-2.amazonaws.com/beta/" + customerId;
    const data = { };
    const headers = {
        'Content-Type': 'application/json',
        'x-api-key': 'J1GBGYozR04Oeu1opXuZuOFiEJA3jwPVAE6enhc0'
    };
    alert("Loading wallets for customer: "+customerId);
    axios.get(request,{headers})
        .then(function(response) {
            //alert(JSON.stringify(response.data));
            document.getElementById("labelMessage").innerHTML = "Done";
            populateWallets(response.data.walletList);
        })
        .catch(function(error) {
            //alert(JSON.stringify(error));
            document.getElementById("labelMessage").innerHTML = "Error";
        }).finally(function() {

        });
}
// Create button
function pressCreateButton() {
    sessionStorage.setItem("customerId", customerId);
    window.open("createcryptowalletpage.html?customerId="+ customerId, "_self");
}
// Login Page Button
function pressLoginPageButton() {
    window.open("index.html", "_self");
}
// Update Wallet
async function updateWallet(walName) {
    document.getElementById("labelMessage").innerHTML = "Updating ...";

    var request = "https://c56ip1t5m3.execute-api.us-west-2.amazonaws.com/beta/" +
            customerId + "/" + walName + "/cryptos/";

    const data = { "walletDescription": "test Ok" };
    const headers = {
        'Content-Type': 'application/json',
        'x-api-key': 'J1GBGYozR04Oeu1opXuZuOFiEJA3jwPVAE6enhc0'
    };
    axios.put(request, data, {headers})
        .then(function(response) {
            //alert(JSON.stringify(response.data));
            document.getElementById("labelMessage").innerHTML = "Done";
        })
        .catch(function(error) {
            //alert(JSON.stringify(error));
            document.getElementById("labelMessage").innerHTML = "Error";
        })
        .finally(function() {
            window.location.reload();
    });
    alert("Update cryptos exchange rate in wallet: "+walName + " for customer: "+customerId);
}

function populateWallets(walletData) {
    let th1 = document.createElement("th");
    let th2 = document.createElement("th");
    let th3 = document.createElement("th");
    let tr = document.createElement("tr");
    let text1 = document.createTextNode("Wallet Name");
    let text2 = document.createTextNode("Cost");
    let text3 = document.createTextNode("Update");
    let thead = walletTable.createTHead();
    th1.appendChild(text1);
    th2.appendChild(text2);
    th3.appendChild(text3);
    tr.appendChild(th1);
    tr.appendChild(th2);
    tr.appendChild(th3);

    walletTable.appendChild(tr);
    var i = 1;
    for (let w of walletData) {
        let td1 = document.createElement("td");
        let td2 = document.createElement("td");
        let td3 = document.createElement("td");
        let txt1 = document.createTextNode(w.walletName);
        let txt2 = document.createTextNode(w.cryptosCost);
        let btn = document.createElement("button");
        btn.innerHTML = "Update";
        btn.name = w.walletName;
        btn.value = i;
        i = i + 1;
        btn.onclick = function (evt) {
            updateWallet(evt.target.name);

        };

        let a = document.createElement("a");
        a.setAttribute('href', `./walletPage.html?customerId=${w.customerId}&walletName=${w.walletName}`);
        a.appendChild(txt1);
        td1.appendChild(a);
        td2.appendChild(txt2);
        td3.appendChild(btn);
        let trr = document.createElement("tr");
        trr.appendChild(td1);
        trr.appendChild(td2);
        trr.appendChild(td3);
        walletTable.appendChild(trr);
    }
}





