function sendMessage(event) {
    event.preventDefault();

    const name = document.getElementById('name').value;
    const email = document.getElementById('email').value;
    const message = document.getElementById('message').value;
    const jwtToken = localStorage.getItem("jwtToken");
    const messageElement = document.getElementById("contactus-message");
    

    const apiUrl = "https://c1d1-2604-3d09-b982-a200-4025-7d3-973f-60ed.ngrok-free.app/contactus";

    fetch(apiUrl, {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            "Authorization": `Bearer ${jwtToken}`,
        },
        body: JSON.stringify({ name,email,message }),
    })
        .then((response) => {
            if (response.ok) {
                return response.text(); // Read the response text (e.g., "Login successful")
            } else {
                return response.text().then((text) => {
                    throw new Error(text); // Throw an error with the response text (message)
                });
            }
        })
        .then((responseMessage) => {
            if (responseMessage.trim().startsWith("Success:")) {
                // Success case
                messageElement.textContent = "Thanks for the feedback: " + name+ ", " + responseMessage; 
                messageElement.style.color = "green";
            } else{
                // Error case
                messageElement.textContent = "Sorry: " + name + ", "+ responseMessage;
                messageElement.style.color = "red";
            }
            setTimeout(() => {
                document.getElementById('name').value = "";
                 document.getElementById('email').value = "";
                document.getElementById('message').value = "";
                messageElement.textContent = "";
            }, 2000);
        })
        .catch((error) => {
            // Handle errors (e.g., invalid credentials)
            messageElement.textContent = error // Display error message
            messageElement.style.color = "red";
            console.error("Error:", error);
            setTimeout(() => {
                document.getElementById('name').value = "";
                 document.getElementById('email').value = "";
                document.getElementById('message').value = "";
            }, 2000);
            setTimeout(() => {
                messageElement.textContent = "";
            }, 20000);
        });
    }