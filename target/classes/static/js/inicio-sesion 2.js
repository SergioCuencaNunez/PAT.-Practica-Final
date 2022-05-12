async function iniciarSesion(){
    console.log("Retrieving the access token");
    console.log(localStorage.getItem("access_token"));

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
                    alert("Inicio de Sesión Correcto");
                    localStorage.setItem("access_token", data.accessToken);
                    console.log(data.accessToken);
                    console.log("Authenticated");
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
                document.location.href="inicio-sesion-clientes.html";
            }else{
                document.location.href="inicio-sesion-gerentes1.html";
            }

        });
}

async function sesionIniciada(){
    console.log("Redireccionando a página usuario");
    if(localStorage.getItem("access_token") != null){
        document.location.href="inicio-sesion-clientes.html";
        /*document.getElementById("cuenta1").innerHTML = "Mi Cuenta";
        document.getElementById("cuenta2").innerHTML = "Mi Cuenta";*/
    }
}
sesionIniciada();

$('#inicioSesionForm').submit(function (e) {
    e.preventDefault();
});

