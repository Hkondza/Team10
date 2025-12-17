document.getElementById("loginForm").addEventListener("submit", async (e) => {
    e.preventDefault();

    const email = document.getElementById("email").value.trim();
    const password = document.getElementById("password").value.trim();

    const data = { email, password };

    try {
        const response = await fetch("/api/auth/login", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(data)
        });

        if (response.ok) {
            // opcionalno spremiti user info
            // localStorage.setItem("userEmail", email);

            window.location.href = "main.html"; // ✅ redirect
        } else {
            alert("Pogrešan email ili lozinka");
        }
    } catch (err) {
        alert("Server nije dostupan");
    }
});
