function validateLogin(event) {
    event.preventDefault();

    const email = document.getElementById('login-email').value;
    const password = document.getElementById('login-password').value;
    const messageElement = document.getElementById('login-message').value;

    const apiUrl = "https://c1d1-2604-3d09-b982-a200-4025-7d3-973f-60ed.ngrok-free.app/api/login";

    fetch(apiUrl, {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify({ email, password }),
    })
        .then((response) => {
            if (response.ok) {
                messageElement.textContent = "Login successful! Redirecting...";
                messageElement.style.color = "green";
                setTimeout(() => {
                    window.location.href = "home.html";
                }, 2000);
            } else {
                messageElement.textContent = "Invalid email or password.";
                messageElement.style.color = "red";
            }
        })
        .catch((error) => {
            messageElement.textContent = "Error connecting to the server.";
            messageElement.style.color = "red";
            console.error("Error:", error);
        });
}
