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

        if (!response.ok) {
            alert("Pogrešan email ili lozinka");
            return;
        }

        // ✅ BACKEND VRAĆA USER PODATKE
        const user = await response.json();

        // ✅ SPREMI U LOCAL STORAGE
        localStorage.setItem("loggedUser", JSON.stringify(user));

        // ✅ REDIRECT NA MAIN
        window.location.href = "main.html";

    } catch (err) {
        console.error(err);
        alert("Server nije dostupan");
    }
});
