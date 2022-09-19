const loginPageForm = document.querySelector("#loginForm");



loginPageForm.onsubmit = async function(evt) {
    evt.preventDefault();
    const cCustomerId = document.querySelector("#customerId").value;

    sessionStorage.setItem("customerId", cCustomerId);

    window.open("homepage.html", "_self");
}

window.onload = async function(evt) {
    evt.preventDefault();
    document.getElementById("labelMessage").innerHTML = "Loading ...";
    document.getElementById("labelMessage").innerHTML = "Done";

}
