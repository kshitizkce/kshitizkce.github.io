function validateLogin(event) {
    event.preventDefault(); // Prevent form submission

    const email = document.getElementById('login-email').value;
    const password = document.getElementById('login-password').value;
    const messageDiv = document.getElementById('login-message');

    // Retrieve stored credentials from localStorage
    const storedEmail = localStorage.getItem("userEmail");
    const storedPassword = localStorage.getItem("userPassword");

    if (email === storedEmail && password === storedPassword) {
        messageDiv.textContent = "Login successful! Redirecting...";
        messageDiv.style.color = "green";

        localStorage.setItem("loggedInUser", email);

        // Redirect to home page after 1 second
        setTimeout(() => {
            window.location.href = "home.html";
        }, 1000);
    } else {
        messageDiv.textContent = "Invalid email or password. Please try again.";
        messageDiv.style.color = "red";
    }
}
