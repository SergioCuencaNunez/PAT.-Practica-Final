async function iniciarSesion(){
    try {
        var correo = await document.getElementById("correo").value;
        var contrasena = await document.getElementById("contrasena").value;

        var data = {"correo": correo,
                    "contrasena":contrasena
                    };
        const address = "api/v1/usuarios/inicio-sesion";
        fetch(address, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body:JSON.stringify(data)
            })
            .then(response => response.json())
            .then(data => {
                console.log(data);
                if(data.result == "OK") {
                    alert("Inicio de Sesión Correcto.");
                    localStorage.setItem("correo", correo);
                    console.log(data.accessToken)
                    localStorage.setItem("access_token", data.accessToken);
                    console.log(data.accessToken);
                    testSecureEndpoint();
                }else{
                    alert(data.result);
                }
            });

    } catch (err) {
        console.error(err.message);
    }
    return false;
}

async function testSecureEndpoint() {
    console.log("Conectando con un endpoint seguro");
    var headers = {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
                'Authorization': "Bearer " + localStorage.getItem("access_token")
            };
    fetch("/secure", {
            method: 'GET',
            headers: headers
    })
    .then(data => {
        if(data.status == 401) {
            localStorage.setItem("access_token_type", "cliente");
            document.location.href="perfil.html";
        }else{
            localStorage.setItem("access_token_type", "admin");
            document.location.href="perfil-gerentes.html";
        }
    });
}

async function sesionIniciada(){
    console.log("Redireccionando a página de usuario");
    if(localStorage.getItem("access_token") != null && localStorage.getItem("access_token_type") == "cliente"){
        document.location.href="perfil.html";
    }else if(localStorage.getItem("access_token") != null && localStorage.getItem("access_token_type") == "admin"){
        document.location.href="perfil-gerentes.html";
    }
}
sesionIniciada();

$('#inicioSesionForm').submit(function (e) {
    e.preventDefault();
});