
let email;
let password;

let loginUrl = "http://localhost:8080/users/login";


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

document.getElementById("signin").onclick = async () => {
  email = document.getElementById("email-input").value;
  password = document.getElementById("password-input").value;

  const options = {
    method: "POST",
    credentials: "include",
    headers: { "Content-type": "application/json" },
    body: JSON.stringify({
      email: email,
      password: password,
    }),
  };

  try {
    const response = await fetch(loginUrl, options);
    const data = await response.json();
    console.log(data);

    if (data.success === true) {
      showBanner("Login successful! Redirecting...", "success");
      setTimeout(() => window.open("App.html", "_blank"), 1000);
    } else {
      // Handle incorrect credentials or user not found
      const message =
        data.message ||
        "Invalid email or password. If you don’t have an account, please sign up.";
      showBanner(message, "error");
    }
  } catch (err) {
    console.error(err);
    showBanner("Network error. Please try again later.", "error");
  }
};
