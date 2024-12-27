function validateLogin(event) {
    event.preventDefault(); // Prevent form submission

    const email = document.getElementById('login-email').value;
    const password = document.getElementById('login-password').value;
    const messageElement = document.getElementById('login-message');

    // API call to validate login credentials
    const apiUrl = "http://localhost:8080/api/login"; // Update with your actual API URL
    const loginData = { email, password };

    fetch(apiUrl, {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(loginData),
    })
    .then(response => {
        if (response.ok) {
            messageElement.texContent = "Login successful! Redirecting to login...";
            messageElement.style.color = "green";

            // Redirect to login page
            setTimeout(() => {
                window.location.href = "home.html"; // Update with your actual login page path
            }, 2000); // Delay to allow user to see the message
        } else {
            return response.json().then(data => {
                messageElement.texContent = data.message || "Login failed.";
                messageElement.style.color = "red";
            });
        }
    })
    .catch(error => {
        messageElement.texContent = "Error connecting to the server.";
        messageElement.style.color = "red";
        console.error("Error:", error);
    });
}
