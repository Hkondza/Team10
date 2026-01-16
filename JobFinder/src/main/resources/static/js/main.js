document.addEventListener("DOMContentLoaded", () => {

    // 🔐 LOGIN CHECK
    const userJson = localStorage.getItem("loggedUser");
    if (!userJson) {
        window.location.href = "login.html";
        return;
    }

    const user = JSON.parse(userJson);

    // PROFILE INFO
    document.getElementById("profileName").innerText = user.fullName;
    document.getElementById("profileNameHeader").innerText = user.fullName;
    document.getElementById("profileEmail").innerText = user.email;
    document.getElementById("profileEmail").href = "mailto:" + user.email;
    document.getElementById("profileRole").innerText = user.role;

    // ROLE BASED BUTTONS
    const roleButtons = document.getElementById("roleButtons");
    const role = user.role.toLowerCase();

    if (role === "student") {
        const btn = document.createElement("button");
        btn.className = "btn-show-jobs";
        btn.innerText = "Show all jobs";
        btn.onclick = () => window.location.href = 'student-jobs.html';
        roleButtons.appendChild(btn);
    }

    if (role === "employer") {
        const btnCreate = document.createElement("button");
        btnCreate.className = "btn-show-jobs";
        btnCreate.innerText = "Create job";
        btnCreate.onclick = () => window.location.href = 'create-job.html';

        const btnMyJobs = document.createElement("button");
        btnMyJobs.className = "btn-show-jobs";
        btnMyJobs.innerText = "My jobs";
        btnMyJobs.onclick = () => window.location.href = 'employer-jobs.html';

        roleButtons.appendChild(btnCreate);
        roleButtons.appendChild(btnMyJobs);
    }

    // LOGOUT EVENT
    const logoutBtn = document.querySelector(".btn-logout");
    if (logoutBtn) {
        logoutBtn.addEventListener("click", () => {
            localStorage.removeItem("loggedUser");
            window.location.href = "login.html";
        });
    }
});