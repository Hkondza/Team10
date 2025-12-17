document.getElementById("signupForm").addEventListener("submit", async (e) => {
    e.preventDefault();

    const fullName = document.getElementById("fullName").value.trim();
    const email = document.querySelector("input[type='email']").value.trim();
    const password = document.getElementById("password").value.trim();
    const role = document.querySelector("input[name='role']:checked")?.value;

    if (!role) {
        alert("Odaberite ulogu");
        return;
    }

    const data = {
        fullName,
        email,
        password,
        role
    };

    try {
        const response = await fetch("/api/auth/register", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(data)
        });

        if (response.ok) {
            alert("Registracija uspješna! Prijavite se.");
            window.location.href = "login.html"; // ✅ redirect
        } else {
            const msg = await response.text();
            alert("Greška: " + msg);
        }
    } catch (err) {
        alert("Server nedostupan");
    }
});
