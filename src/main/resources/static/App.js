const recordsUrl = "http://localhost:8080/transaction/TransactionHistory";

const depositUrl = "http://localhost:8080/transaction/deposit";

const withdrawUrl = "http://localhost:8080/transaction/withdraw";

const transferUrl = "http://localhost:8080/transaction/transfer";




const raw = null; 
const requestOptions = {
  method: "GET",
  body: raw,
  redirect: "follow"
}

let records = null;

window.onload = async () => {
    let response = await fetch(recordsUrl, requestOptions);
    let data = await response.json();
    records = await data;


    let balance = records.balance;
    const overiewButton = document.getElementById("overview");
    const transactionsButton = document.getElementById("Transactions-button");

    const deposit = document.getElementById("deposit");
    const withdraw = document.getElementById("withdraw");
    const transfer = document.getElementById("transfer");

    const depositContainer = document.querySelector(".deposit-container");
    const withdrawContainer = document.querySelector(".withdraw-container");
    const transferContaner = document.querySelector(".transfer-container");
    const cancel = document.querySelectorAll(".cancel-button");


    document.getElementById("amount").textContent = `R ${balance}`;

    document.getElementById("Transactions-button").onclick = () => {

        overiewButton.style.cssText = `
    border-bottom: none;
       `

        transactionsButton.style.cssText =
            `
    border-bottom: black solid 3px;
    
    `
    }


    document.getElementById("overview").onclick = () => {
        overiewButton.style.cssText = `
     border-bottom: black solid 3px;
     `

        transactionsButton.style.cssText = `
     border-bottom: none;
     `
    }

    const account = document.querySelector(".balance");


    account.addEventListener("click", () => {


    })

    deposit.onclick = () => {
        withdrawContainer.style.display = "none";
        transferContaner.style.display = "none";
        depositContainer.style.display = "block";
    }

    withdraw.onclick = () => {
        depositContainer.style.display = "none";
        transferContaner.style.display = "none";
        withdrawContainer.style.display = "block";
    }

    transfer.onclick = () => {

        depositContainer.style.display = "none";
        withdrawContainer.style.display = "none";
        transferContaner.style.display = "block";

    }


    cancel.forEach((button) => {
        button.onclick = () => {
            depositContainer.style.display = "none";
            withdrawContainer.style.display = "none";
            transferContaner.style.display = "none";
        }
    })

    document.getElementById("deposit-button").onclick = async () => {
        let amount = document.getElementById("deposit-amount").value;
        const depositOptions = {
        method: 'POST',
        headers: {'Content-type': 'application/json'},
        body: JSON.stringify({
            "amount": amount,
            "type": "D"
            })
            }
            try{
                let response = await fetch(depositUrl, depositOptions);
                let data = await response.json();
                }
                catch(error){
                        console.error(error);
        }
            }

    document.getElementById("withdraw-button").onclick = async () => {
        let amount = document.getElementById("withdraw-amount").value;

        const withdrawOptions = {
            method: 'POST',
            headers: {'Content-type': 'application/json'},
            body: JSON.stringify({
                "amount": amount,
                "type": "W"
            })

        }
        try {
            let response = await fetch(withdrawUrl, withdrawOptions);
            let data = await response.json();
        } catch (error) {
            console.error(error);
        }


    }

    document.getElementById("transfer-button").onclick = async () => {
        let amount = document.getElementById("transfer-amount");
        let user = document.getElementById("user-input");

        const transferOptions = {
            method: 'POST',
            headers: {'Content-type': 'application/json'},
            credentials: 'include',
            body: JSON.stringify({
                "type": "T",
                "amount": amount,
                "email": user

            })}

            try{
                let response = await fetch(transferUrl, transferOptions);
                let data = await response.json();

                console.log(data);

            }
            catch(error){
            console.log(error);
            }
        }






}