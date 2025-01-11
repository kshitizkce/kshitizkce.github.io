function validateSignup(event) {
    event.preventDefault();

    const email = document.getElementById("signup-email").value;
    const password = document.getElementById("signup-password").value;
    const confirmPassword = document.getElementById("signup-confirm-password").value;
    const messageElement = document.getElementById("signup-message");

    // Validate passwords match
    if (password !== confirmPassword) {
        messageElement.textContent = "Passwords do not match.";
        messageElement.style.color = "red";
        return;
    }

    // API call
    const apiUrl = " https://c1d1-2604-3d09-b982-a200-4025-7d3-973f-60ed.ngrok-free.app/api/signup"; 
    const signupData = { email, password };

    fetch(apiUrl, {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(signupData),
    })
    .then(response => {
        if (response.ok) {
            messageElement.textContent = "Signup successful! Redirecting to login...";
            messageElement.style.color = "green";

            // Redirect to login page
            setTimeout(() => {
                window.location.href = "index.html"; 
            }, 2000); // Delay to allow user to see the message
        } else {
            return response.json().then(data => {
                messageElement.textContent = data.message || "Signup failed.";
                messageElement.style.color = "red";
            });
        }
    })
    .catch(error => {
        messageElement.textContent = "Error connecting to the server.";
        messageElement.style.color = "red";
        console.error("Error:", error);
    });
}
