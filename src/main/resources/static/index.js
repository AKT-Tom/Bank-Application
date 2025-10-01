
let email;
let password;



let loginUrl = "http://localhost:8080/users/login";

document.getElementById("signin").onclick = function(){
    email = document.getElementById("email-input").value;
    password = document.getElementById("password-input").value;

    fetch(loginUrl, {
    method: 'POST',
    credentials: 'include',
    headers: {'Content-type':'application/json'} ,
    body: JSON.stringify({
        "email": email,
        "password": password
    })

}).then(response => response.json())
.then(data => {
    if (data.success === true){
    window.open("App.html", "_blank");
    }
})
.catch(error => console.error('ERROR:', error));
}
