let ID;
let Email;
let password;
let firstName;
let lastName;
let City;
let address;

let url = "http://localhost:8080/users/signup";

// Helper to show banner messages
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

  // Auto-hide after 5 seconds
  setTimeout(() => banner.remove(), 5000);
}

document.getElementById("submit").onclick = async () => {
  ID = document.getElementById("id-input").value;
  Email = document.getElementById("email-input").value;
  password = document.getElementById("password-input").value;
  firstName = document.getElementById("first-name-input").value;
  lastName = document.getElementById("last-name-input").value;
  City = document.getElementById("city-input").value;
  address = document.getElementById("address-input").value;

  const options = {
    method: "POST",
    headers: { "Content-type": "application/json" },
    body: JSON.stringify({
      balance: 0.0,
      Password: password,
      ID: ID,
      FirstName: firstName,
      LastName: lastName,
      City: City,
      address: address,
      email: Email,
      DOB: "",
    }),
  };

  try {
    const response = await fetch(url, options);
    const data = await response.json();
    console.log(data);

    if (data.success) {
      showBanner("Signup successful! You can now log in.", "success");
    } else {
      showBanner(data.message || "Signup failed. Please check your inputs.", "error");
    }
  } catch (err) {
    console.error(err);
    showBanner("Network error. Please try again.", "error");
  }
};
