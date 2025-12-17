document.getElementById("loginForm").addEventListener("submit", async function (e) {
    e.preventDefault();

    // Get the button element to disable it during submission (best practice)
    const submitButton = e.target.querySelector('button[type="submit"]');
    if (submitButton) {
        submitButton.disabled = true;
    }

    const data = {
        email: document.getElementById("email").value.trim(),
        password: document.getElementById("password").value.trim()
    };

    try {
        const response = await fetch("http://localhost:8080/api/auth/login", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(data)
        });

        if (response.ok) {
            const result = await response.json();
            console.log("Login uspješan:", result);
            alert("Uspješno prijavljeni! Preusmjeravanje..."); // Changed alert

            // --- ADDED: 200ms Delay and Redirection ---
            const delayInMilliseconds = 200;
            const targetURL = "main.html";

            setTimeout(function() {
                // This line executes after 200 milliseconds
                window.location.href = targetURL;
            }, delayInMilliseconds);
            // --- END ADDED LOGIC ---

        } else {
            // Failure: Re-enable the button
            if (submitButton) {
                submitButton.disabled = false;
            }
            alert("Neispravni podaci.");
        }
    } catch (error) {
        // Network Error: Re-enable the button
        if (submitButton) {
            submitButton.disabled = false;
        }
        console.error("Greška:", error);
        alert("Server nije dostupan.");
    }
});