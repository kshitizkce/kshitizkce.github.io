function validateLogin(event) {
    event.preventDefault();

    const email = document.getElementById('login-email').value;
    const password = document.getElementById('login-password').value;
    const messageElement = document.getElementById('login-message');

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
                return response.json(); // Read the response text (e.g., "Login successful")
            } else {
                return response.json().then((data) => {
                    // Assuming the response contains an error message in the "error" field
                    throw new Error(data.error);
                });
            }
        })
        .then((data) => {
            const { email, token } = data;

            // Save token and email in local storage
        localStorage.setItem("jwtToken", token);
        localStorage.setItem("loggedInUser", email);

            // Successful login
            messageElement.textContent = "Welcome User :" + email; // Display the success message
            messageElement.style.color = "green";
    
            // Redirect after 2 seconds
            setTimeout(() => {
                window.location.href = "home.html";
            }, 2000);
        })
        .catch((error) => {
            // Handle errors (e.g., invalid credentials)
            messageElement.textContent = error.message; // Display error message
            messageElement.style.color = "red";
            console.error("Error:", error);
        });
    }