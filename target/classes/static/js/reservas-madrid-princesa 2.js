async function reservar1(){
    try {
        var nif = await document.getElementById("nif-princesa1").value;
        var checkIn = await document.getElementById("date-in-princesa1").value;
        var checkOut = await document.getElementById("date-out-princesa1").value;
        var huespedesAnti = await document.getElementById("guest-princesa1");
        var huespedesAnt = huespedesAnti.options[huespedesAnti.selectedIndex].text;
        var habitacionesAnt = await document.getElementById("room-princesa1");
        var habitaciones = habitacionesAnt.options[habitacionesAnt.selectedIndex].text;
        var checkInDia = checkIn.substr(0,2);
        var checkOutDia = checkOut.substr(0,2);
        var checkInMes = checkIn.substr(3,(checkIn.indexOf(",")) - 3);
        var checkOutMes = checkOut.substr(3,(checkOut.indexOf(",")) - 3);
        var checkInAno = checkIn.substr((checkIn.indexOf(",") + 2));
        var checkOutAno = checkOut.substr((checkOut.indexOf(",") + 2));
        var checkInNumeroMes = ["enero", "febrero", "marzo", "abril", "mayo", "junio", "julio", "agosto", "septiembre", "octubre", "noviembre", "diciembre"].indexOf(checkInMes.toLowerCase()) + 1;
        var checkOutNumeroMes = ["enero", "febrero", "marzo", "abril", "mayo", "junio", "julio", "agosto", "septiembre", "octubre", "noviembre", "diciembre"].indexOf(checkOutMes.toLowerCase()) + 1;
        var huespedes = huespedesAnt.substr(0,1);

        if (checkInNumeroMes < 10 || checkOutNumeroMes < 10) {
          checkInNumeroMes = "0" + checkInNumeroMes;
          checkOutNumeroMes = "0" + checkOutNumeroMes;
        }
        var checkInDef = checkInAno + "-" + checkInNumeroMes + "-" + checkInDia;
        var checkOutDef = checkOutAno + "-" + checkOutNumeroMes + "-" + checkOutDia;
        var checkInDefi = new Date(checkInDef);
        var checkOutDefi = new Date(checkOutDef);

        var data = {"id": null, "nif":nif, "hotel": 'Melia-Madrid-Princesa', "destino": 'Madrid', "huespedes": huespedes, "habitaciones":habitaciones, "fechaEntrada": checkInDefi, "fechaSalida": checkOutDefi};
        const address = "api/v1/reservas/insert";
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
                    alert("Reserva realizada correctamente");
                    console.log(data.accessToken);
                }else{
                    alert(data.result);
                }
            });

    } catch (err) {
        console.error(err.message);
    }
    return false;
}

$('#reservaForm').submit(function (e) {
    e.preventDefault();
});