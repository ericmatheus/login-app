document.addEventListener("DOMContentLoaded", function () {
    const loginForm = document.getElementById("loginForm");
    const errorMessage = document.getElementById("errorMessage");
    const registerButton = document.getElementById("registerButton");

    // Evento para login
    loginForm.addEventListener("submit", async function (event) {
        event.preventDefault();
        
        const username = document.getElementById("username").value;
        const password = document.getElementById("password").value;
        
        const response = await fetch("http://localhost:8080/auth/login", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({ username, password })
        });

        if (response.ok) {
            window.location.href = "dashboard.html"; // Redireciona para outra página
        } else {
            errorMessage.textContent = "Usuário ou senha inválidos.";
            errorMessage.style.color = "red";
        }
    });

    // Evento para cadastrar usuário
    registerButton.addEventListener("click", async function () {
        const username = prompt("Digite o nome de usuário:");
        if (!username) return;

        const password = prompt("Digite a senha:");
        if (!password) return;

        const response = await fetch("http://localhost:8080/auth/register", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({ username, password })
        });

        if (response.ok) {
            alert("Usuário cadastrado com sucesso!");
        } else {
            alert("Erro: " + await response.text());
        }
    });
});