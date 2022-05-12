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
                    document.location.href="inicio-sesion-clientes.html";
                }else{
                    alert(data.result);
                }
            });

    } catch (err) {
        console.error(err.message);
    }
    return false;
}

$('#inicioSesionForm').submit(function (e) {
    e.preventDefault();
});