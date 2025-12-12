document.getElementById("loginForm").addEventListener("submit", async function (e) {
    e.preventDefault();

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
            alert("Uspješno prijavljeni.");
        } else {
            alert("Neispravni podaci.");
        }
    } catch (error) {
        console.error("Greška:", error);
        alert("Server nije dostupan.");
    }
});
