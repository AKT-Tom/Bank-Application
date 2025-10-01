let recordsUrl = "http://localhost:8080/transaction/TransactionHistory";


document.getElementById("test").onclick = function(){
    fetch(recordsUrl, {
    method: 'GET',
    credentials: 'include',
    headers: {'Content-Type': 'application/json'}
}).then(response => response.json())
.then(data => console.log(data))
.catch(err => console.error(err));}