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
        "Content-Type": "application/json",   // Additional headers if needed
        },
        body: JSON.stringify({ email, password }),
    })
        .then((response) => {
            if (response.ok) {
                 message = response.text();
                return message; // Read the response text (e.g., "Signup successful")
            } else {
                return response.text().then((text) => {
                    throw new Error(text); // Throw an error with the response text (message)
                });
            }
        })
        .then((message) => {
            // Successful signup
            messageElement.textContent = message; // Display the success message
            messageElement.style.color = "green";
    
            // Redirect after 2 seconds
            setTimeout(() => {
                window.location.href = "index.html";
            }, 2000);
        })
        .catch((error) => {
            // Handle errors (e.g., invalid credentials)
            messageElement.textContent = error.message; // Display error message
            messageElement.style.color = "red";
            console.error("Error:", error);
            setTimeout(() => {
                document.getElementById("signup-email").value = "";
             document.getElementById("signup-password").value = "";
             document.getElementById("signup-confirm-password").value = "";
             messageElement.textContent = "";
            }, 2000);
        });
        
    }
