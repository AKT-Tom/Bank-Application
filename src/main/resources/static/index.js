
let email;
let password;



let loginUrl = "http://localhost:8080/users/login";

document.getElementById("signin").onclick = async () => {
    email = document.getElementById("email-input").value;
    password = document.getElementById("password-input").value;

    const options = {
    method: 'POST',
    credentials: 'include',
    headers: {'Content-type':'application/json'} ,
    body: JSON.stringify({
        "email": email,
        "password": password
    })}


   const response = await fetch(loginUrl, options);
   const data = await response.json();

    
    if (data.success === true){
    window.open("App.html", "_blank");
    }

     
}