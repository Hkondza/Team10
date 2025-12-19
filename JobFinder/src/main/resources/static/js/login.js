// Dependency Inversion: Injected AuthService depends on abstractions (ApiClient, StorageAdapter).
// Single Responsibility: Controller handles DOM/events; AuthService handles auth/API.
// Liskov: LocalStorageAdapter can be swapped with MemoryStorageAdapter without breaking logic.
document.getElementById("loginForm").addEventListener("submit", async (e) => {
    e.preventDefault();

    const email = document.getElementById("email").value.trim();
    const password = document.getElementById("password").value.trim();

    const { ApiClient, AuthService, LocalStorageAdapter, EmailPasswordCredentials } = window.JobFinder || {};

    // Guard if core isn't loaded; fall back to direct fetch.
    if (!ApiClient || !AuthService || !LocalStorageAdapter) {
        try {
            const response = await fetch("/api/auth/login", {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify({ email, password })
            });
            if (!response.ok) {
                alert("Pogrešan email ili lozinka");
                return;
            }
            const user = await response.json();
            localStorage.setItem("loggedUser", JSON.stringify(user));
            window.location.href = "main.html";
        } catch (err) {
            console.error(err);
            alert("Server nije dostupan");
        }
        return;
    }

    const api = new ApiClient();
    const storage = new LocalStorageAdapter(window.localStorage);
    const auth = new AuthService(api, storage);

    try {
        const creds = new EmailPasswordCredentials(email, password);
        await auth.login(creds);
        window.location.href = "main.html";
    } catch (err) {
        console.error(err);
        alert("Pogrešan email ili lozinka ili server nedostupan");
    }
});
