document.getElementById("signupForm").addEventListener("submit", async (e) => {
    e.preventDefault();

    const fullName = document.getElementById("fullName").value.trim();
    const email = document.querySelector("input[type='email']").value.trim();
    const password = document.getElementById("password").value.trim();
    const role = document.querySelector("input[name='role']:checked")?.value;

    const { ApiClient, AuthService, Validator, EmailPasswordCredentials, RegistrationProfile } = window.JobFinder || {};

    if (!Validator || !ApiClient || !AuthService) {
        if (!role) {
            alert("Odaberite ulogu");
            return;
        }
        try {
            const response = await fetch("/api/auth/register", {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify({ fullName, email, password, role })
            });
            if (response.ok) {
                alert("Registracija uspješna! Prijavite se.");
                window.location.href = "login.html";
            } else {
                const msg = await response.text();
                alert("Greška: " + msg);
            }
        } catch (err) {
            alert("Server nedostupan");
        }
        return;
    }

    const creds = new EmailPasswordCredentials(email, password);
    const profile = new RegistrationProfile(fullName, role);
    const data = { ...creds, ...profile };

    const validator = new Validator();
    validator.addRule(d => !d.role ? "Odaberite ulogu" : null);
    validator.addRule(d => d.password && d.password.length < 6 ? "Lozinka mora imati najmanje 6 znakova" : null);
    const errors = validator.validate(data);
    if (errors.length) {
        alert(errors[0]);
        return;
    }

    try {
        const api = new ApiClient();
        const auth = new AuthService(api,  null);
        const response = await auth.register(data);
        if (response.ok) {
            alert("Registracija uspješna! Prijavite se.");
            window.location.href = "login.html";
        } else {
            const msg = await response.text();
            alert("Greška: " + msg);
        }
    } catch (err) {
        alert("Server nedostupan");
    }
});
