const recordsUrl = "http://localhost:8080/transaction/TransactionHistory";
const depositUrl = "http://localhost:8080/transaction/deposit";
const withdrawUrl = "http://localhost:8080/transaction/withdraw";
const transferUrl = "http://localhost:8080/transaction/transfer";

// Helper: Banner system
function showBanner(message, type = "error") {
  let existingBanner = document.querySelector(".banner");
  if (existingBanner) existingBanner.remove();

  const banner = document.createElement("div");
  banner.className = `banner ${type}`;
  banner.textContent = message;

  const closeBtn = document.createElement("span");
  closeBtn.innerHTML = "&times;";
  closeBtn.className = "banner-close";
  closeBtn.onclick = () => banner.remove();

  banner.appendChild(closeBtn);
  document.body.appendChild(banner);

  setTimeout(() => banner.remove(), 5000);
}

const requestOptions = { method: "GET", redirect: "follow" };
let records = null;

window.onload = async () => {
  let response = await fetch(recordsUrl, requestOptions);
  let data = await response.json();
  records = data;

  let balance = records.balance;
  const overviewButton = document.getElementById("overview");
  const transactionsButton = document.getElementById("Transactions-button");

  const deposit = document.getElementById("deposit");
  const withdraw = document.getElementById("withdraw");
  const transfer = document.getElementById("transfer");

  const depositContainer = document.querySelector(".deposit-container");
  const withdrawContainer = document.querySelector(".withdraw-container");
  const transferContainer = document.querySelector(".transfer-container");
  const cancel = document.querySelectorAll(".cancel-button");
   overviewSection = document.getElementById("overviewSection");

  document.getElementById("amount").textContent = `R ${balance.toFixed(2)}`;

  // Transactions button
  transactionsButton.onclick = () => {
    overviewButton.style.borderBottom = "none";
    transactionsButton.style.borderBottom = "black solid 3px";
   
    overviewSection.style.display = "none";

    // Display transactions
    displayTransactions(records.records);

  };

  // Overview button
  overviewButton.onclick = () => {
    overviewSection.style.display = "block";
    overviewButton.style.borderBottom = "black solid 3px";
    transactionsButton.style.borderBottom = "none";
    const transactionsSection = document.querySelector(".transactions-section");
    if (transactionsSection) transactionsSection.remove();
  };

  deposit.onclick = () => {
    withdrawContainer.style.display = "none";
    transferContainer.style.display = "none";
    depositContainer.style.display = "block";
  };

  withdraw.onclick = () => {
    depositContainer.style.display = "none";
    transferContainer.style.display = "none";
    withdrawContainer.style.display = "block";
  };

  transfer.onclick = () => {
    depositContainer.style.display = "none";
    withdrawContainer.style.display = "none";
    transferContainer.style.display = "block";
  };

  cancel.forEach((button) => {
    button.onclick = () => {
      depositContainer.style.display = "none";
      withdrawContainer.style.display = "none";
      transferContainer.style.display = "none";
    };
  });

  //Transfer

  document.getElementById("transfer-button").onclick = async () => {
    let amount = parseFloat(document.getElementById("transfer-amount").value);
    if (!amount || amount <=0 ){
        showBanner("Please enter a valid amount.", "error");
        return;
    }
    else if (amount > balance){
        showBanner("Insufficient funds", "error");
        return;

    }
    let email = document.getElementById("user-input").value;

    const transferOptions = {
        method: "POST",
        headers: {"Content-type" :"application/json"},
        body: JSON.stringify({
            amount,
            type: "T",
            email: `${email}`
        })
    };

    try {
        let response = await fetch(transferUrl, transferOptions);
        let data = await response.json();
        if (data.success){
            balance -= amount;
            document.getElementById("amount").textContent = `R ${balance.toFixed(2)}`;
            showBanner("Transfer succesfull", "success");
        }
        else{
            showBanner(data.message);
        }
    }
  catch (error){
    console.log(error);
    showBanner("Network error during transfer", "error");
  }
}

  // Deposit
  document.getElementById("deposit-button").onclick = async () => {
    let amount = parseFloat(document.getElementById("deposit-amount").value);
    if (!amount || amount <= 0) {
      showBanner("Please enter a valid amount.", "error");
      return;
    }

    const depositOptions = {
      method: "POST",
      headers: { "Content-type": "application/json" },
      body: JSON.stringify({ amount, type: "D" }),
    };

    try {
      let response = await fetch(depositUrl, depositOptions);
      let data = await response.json();

      if (data.success) {
        balance += amount;
        document.getElementById("amount").textContent = `R ${balance.toFixed(2)}`;
        showBanner("Deposit successful!", "success");
      } else {
        showBanner(data.message || "Deposit failed.", "error");
      }
    } catch (error) {
      showBanner("Network error during deposit.", "error");
    }
  };

  // Withdraw
  document.getElementById("withdraw-button").onclick = async () => {
    let amount = parseFloat(document.getElementById("withdraw-amount").value);
    if (!amount || amount <= 0) {
      showBanner("Please enter a valid amount.", "error");
      return;
    }

    if (amount > balance) {
      showBanner("Insufficient funds — you can’t withdraw more than your balance.", "error");
      return;
    }

    const withdrawOptions = {
      method: "POST",
      headers: { "Content-type": "application/json" },
      body: JSON.stringify({ amount, type: "W" }),
    };

    try {
      let response = await fetch(withdrawUrl, withdrawOptions);
      let data = await response.json();

      if (data.success) {
        balance -= amount;
        document.getElementById("amount").textContent = `R ${balance.toFixed(2)}`;
        showBanner("Withdrawal successful!", "success");
      } else {
        showBanner(data.message || "Withdrawal failed.", "error");
      }
    } catch (error) {
      console.error(error);
      showBanner("Network error during withdrawal.", "error");
    }
  };
};

// Helper function to render transactions
function displayTransactions(records) {
  // Remove old section if exists
  const oldSection = document.querySelector(".transactions-section");
  if (oldSection) oldSection.remove();

  const section = document.createElement("div");
  section.className = "transactions-section";
  section.innerHTML = `
    <h3 style="text-align:center;">Transaction History</h3>
    <table class="transactions-table">
      <thead>
        <tr>
          <th>Date</th>
          <th>Type</th>
          <th>Amount (R)</th>
          <th>Recipient</th>
        </tr>
      </thead>
      <tbody></tbody>
    </table>
  `;

  const tbody = section.querySelector("tbody");

  records.flat().forEach((rec) => {
    const row = document.createElement("tr");
    row.innerHTML = `
      <td>${rec.date}</td>
      <td>${rec.type}</td>
      <td>${rec.amount.toFixed(2)}</td>
      <td>${rec.transfer_To ? rec.transfer_To : "-"}</td>
    `;
    tbody.appendChild(row);
  });

  document.body.appendChild(section);
}
