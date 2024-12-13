    function validateSignup(event) {
            event.preventDefault(); // Prevent form submission

            const email = document.getElementById('signup-email').value;
            const password = document.getElementById('signup-password').value;
            const confirmPassword = document.getElementById('signup-confirm-password').value;
            const messageDiv = document.getElementById('signup-message');

            if (password !== confirmPassword) {
                messageDiv.textContent = "Passwords do not match!";
                messageDiv.style.color = "red";
            } else {
                // Save email and password to localStorage
                localStorage.setItem("userEmail", email);
                localStorage.setItem("userPassword", password);

                messageDiv.textContent = "Signup successful! Redirecting to login page...";
                messageDiv.style.color = "green";

                // Redirect to login page after 1 seconds
                setTimeout(() => {
                    window.location.href = "index.html";
                }, 1000);
            }
        }
