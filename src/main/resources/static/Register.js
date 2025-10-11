let ID;
let Email;
let password;
let firstName;
let lastName;
let City;
let address;

let url = "http://localhost:8080/users/signup";




document.getElementById("submit").onclick = async () => {

    let response = null;
    
    
    
    ID = document.getElementById("id-input").value;
    Email = document.getElementById("email-input").value;
    password = document.getElementById("password-input").value;
    firstName = document.getElementById("first-name-input").value;
    lastName = document.getElementById("last-name-input").value;
    City = document.getElementById("city-input").value;
    address = document.getElementById("address-input").value;

    
const options = {
    method: 'POST',
        headers: {'Content-type':'application/json'} ,
        body: JSON.stringify({
                "balance": 0.0,
                "Password": password,
                "ID": ID,
                "FirstName": firstName,
                "LastName": lastName,
                "City": City,
                "address": address,
                "email": Email,
                "DOB": ""
        })}



    
    response = await fetch(url , options);
    console.log(await response.json());

    

}