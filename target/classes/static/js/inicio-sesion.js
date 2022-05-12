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
                    localStorage.setItem("acces_token", data.accessToken);
                    console.log(data.accessToken);
                    console.log("Authenticated");
<<<<<<< HEAD
                    document.location.href="inicio-sesion-clientes.html";
=======
                    testSecureEndpoint();
>>>>>>> 66c03206cd986c9289f8fd205852ab1c0c21b01a
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
    console.log("Connecting with a secure endpoint");
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
    if(localStorage.getItem("acces_token") != null){
        document.location.href="inicio-sesion-clientes.html";
    }
}
sesionIniciada();

$('#inicioSesionForm').submit(function (e) {
    e.preventDefault();
});

