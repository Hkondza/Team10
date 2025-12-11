function togglePassword() {
    const pass = document.getElementById("password");
    pass.type = pass.type === "password" ? "text" : "password";
}
document.getElementById("registerForm").addEventListener("submit", async function (e) {
    e.preventDefault();

    const data = {
        fullName: document.getElementById("fullname").value.trim(),
        email: document.getElementById("email").value.trim(),
        password: document.getElementById("password").value.trim(),
        role: document.getElementById("role").value
    };

    if (!data.role) {
        alert("Molimo odaberite ulogu!");
        return;
    }

    try {
        const response = await fetch("http://localhost:8080/api/auth/register", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(data)
        });

        if (response.ok) {
            const result = await response.json();
            console.log("Registracija uspješna:", result);
            alert("Registracija uspješna!");
        } else {
            alert("Greška pri registraciji. Provjerite podatke.");
        }
    } catch (error) {
        console.error("Greška:", error);
        alert("Server nije dostupan.");
    }
});