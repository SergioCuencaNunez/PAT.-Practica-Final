async function reservarSupremme() {
    try {
        var nif = await document.getElementById("nif-duques1").value;
        var checkIn = await document.getElementById("date-in-duques1").value;
        var checkOut = await document.getElementById("date-out-duques1").value;
        var huespedes = await document.getElementById("guest-duques1").value;
        var habitaciones = await document.getElementById("room-duques1").value;
        var checkInDia = checkIn.substr(0,2);
        var checkOutDia = checkOut.substr(0,2);
        var checkInMes = checkIn.substr(3,(checkIn.indexOf(",")) - 3);
        var checkOutMes = checkOut.substr(3,(checkOut.indexOf(",")) - 3);
        var checkInAno = checkIn.substr((checkIn.indexOf(",") + 2));
        var checkOutAno = checkOut.substr((checkOut.indexOf(",") + 2));
        var checkInNumeroMes = ["enero", "febrero", "marzo", "abril", "mayo", "junio", "julio", "agosto", "septiembre", "octubre", "noviembre", "diciembre"].indexOf(checkInMes.toLowerCase()) + 1;
        var checkOutNumeroMes = ["enero", "febrero", "marzo", "abril", "mayo", "junio", "julio", "agosto", "septiembre", "octubre", "noviembre", "diciembre"].indexOf(checkOutMes.toLowerCase()) + 1;

        if (checkInNumeroMes < 10 || checkOutNumeroMes < 10) {
          checkInNumeroMes = "0" + checkInNumeroMes;
          checkOutNumeroMes = "0" + checkOutNumeroMes;
        }
        var checkInDef = checkInAno + "-" + checkInNumeroMes + "-" + checkInDia;
        var checkOutDef = checkOutAno + "-" + checkOutNumeroMes + "-" + checkOutDia;

        const data1 = {nif: nif, checkIn: checkInDef, checkOut: checkOutDef, huespedes: huespedes, habitaciones: habitaciones};
        const address1 = "api/v1/reservas/registro";
        fetch(address1, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data1)
        })
        .then(response => response.json())
        .then(data1 => {
            console.log(data1);
            if(data1.result == "OK") {
                console.log("Datos correctos");
                var checkInDefi = new Date(checkInDef);
                var checkOutDefi = new Date(checkOutDef);
                const data2 = {"id": null, "nif":nif, "hotel": 'Gran-Melia-Palacio-de-los-Duques', "destino": 'Madrid', "tipo": 'Supremme', "huespedes": huespedes, "habitaciones":habitaciones, "fechaEntrada": checkInDefi, "fechaSalida": checkOutDefi};
                const address2 = "api/v1/reservas/insert";
                fetch(address2, {
                    method: 'POST',
                    headers: {
                         'Content-Type': 'application/json'
                    },
                    body: JSON.stringify(data2)
                })
                .then(response => response.json())
                .then(data2 => {
                    if(data2.result == "OK") {
                        alert("Reserva realizada correctamente. Para modificarla, cancelarla o ver los detalles, inicie sesión en su cuenta de MeliáRewards.");
                    }else{
                        alert(data2.result);
                    }
                });
            }else{
                alert("No se ha podido realizar la reserva. Por favor, revise que todos los campos introducidos son correctos.\nDebe introducir un NIF vigente, fechas de entrada y de salida coherentes, número de huéspedes y número de habitaciones.");
            }
       });
    } catch (err) {
        console.error(err.message);
    }
    return false;
}

async function reservarSuiteDeluxe() {
    try {
        var nif = await document.getElementById("nif-duques2").value;
        var checkIn = await document.getElementById("date-in-duques2").value;
        var checkOut = await document.getElementById("date-out-duques2").value;
        var huespedes = await document.getElementById("guest-duques2").value;
        var habitaciones = await document.getElementById("room-duques2").value;
        var checkInDia = checkIn.substr(0,2);
        var checkOutDia = checkOut.substr(0,2);
        var checkInMes = checkIn.substr(3,(checkIn.indexOf(",")) - 3);
        var checkOutMes = checkOut.substr(3,(checkOut.indexOf(",")) - 3);
        var checkInAno = checkIn.substr((checkIn.indexOf(",") + 2));
        var checkOutAno = checkOut.substr((checkOut.indexOf(",") + 2));
        var checkInNumeroMes = ["enero", "febrero", "marzo", "abril", "mayo", "junio", "julio", "agosto", "septiembre", "octubre", "noviembre", "diciembre"].indexOf(checkInMes.toLowerCase()) + 1;
        var checkOutNumeroMes = ["enero", "febrero", "marzo", "abril", "mayo", "junio", "julio", "agosto", "septiembre", "octubre", "noviembre", "diciembre"].indexOf(checkOutMes.toLowerCase()) + 1;

        if (checkInNumeroMes < 10 || checkOutNumeroMes < 10) {
          checkInNumeroMes = "0" + checkInNumeroMes;
          checkOutNumeroMes = "0" + checkOutNumeroMes;
        }
        var checkInDef = checkInAno + "-" + checkInNumeroMes + "-" + checkInDia;
        var checkOutDef = checkOutAno + "-" + checkOutNumeroMes + "-" + checkOutDia;

        const data1 = {nif: nif, checkIn: checkInDef, checkOut: checkOutDef, huespedes: huespedes, habitaciones: habitaciones};
        const address1 = "api/v1/reservas/registro";
        fetch(address1, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data1)
        })
        .then(response => response.json())
        .then(data1 => {
            console.log(data1);
            if(data1.result == "OK") {
                console.log("Datos correctos");
                var checkInDefi = new Date(checkInDef);
                var checkOutDefi = new Date(checkOutDef);
                const data2 = {"id": null, "nif":nif, "hotel": 'Gran-Melia-Palacio-de-los-Duques', "destino": 'Madrid', "tipo": 'Suite-Deluxe', "huespedes": huespedes, "habitaciones":habitaciones, "fechaEntrada": checkInDefi, "fechaSalida": checkOutDefi};
                const address2 = "api/v1/reservas/insert";
                fetch(address2, {
                    method: 'POST',
                    headers: {
                         'Content-Type': 'application/json'
                    },
                    body: JSON.stringify(data2)
                })
                .then(response => response.json())
                .then(data2 => {
                    if(data2.result == "OK") {
                        alert("Reserva realizada correctamente. Para modificarla, cancelarla o ver los detalles, inicie sesión en su cuenta de MeliáRewards.");
                    }else{
                        alert(data2.result);
                    }
                });
            }else{
                alert("No se ha podido realizar la reserva. Por favor, revise que todos los campos introducidos son correctos.\nDebe introducir un NIF vigente, fechas de entrada y de salida coherentes, número de huéspedes y número de habitaciones.");
            }
       });
    } catch (err) {
        console.error(err.message);
    }
    return false;
}

async function reservarRoyalSuite() {
    try {
        var nif = await document.getElementById("nif-duques3").value;
        var checkIn = await document.getElementById("date-in-duques3").value;
        var checkOut = await document.getElementById("date-out-duques3").value;
        var huespedes = await document.getElementById("guest-duques3").value;
        var habitaciones = await document.getElementById("room-duques3").value;
        var checkInDia = checkIn.substr(0,2);
        var checkOutDia = checkOut.substr(0,2);
        var checkInMes = checkIn.substr(3,(checkIn.indexOf(",")) - 3);
        var checkOutMes = checkOut.substr(3,(checkOut.indexOf(",")) - 3);
        var checkInAno = checkIn.substr((checkIn.indexOf(",") + 2));
        var checkOutAno = checkOut.substr((checkOut.indexOf(",") + 2));
        var checkInNumeroMes = ["enero", "febrero", "marzo", "abril", "mayo", "junio", "julio", "agosto", "septiembre", "octubre", "noviembre", "diciembre"].indexOf(checkInMes.toLowerCase()) + 1;
        var checkOutNumeroMes = ["enero", "febrero", "marzo", "abril", "mayo", "junio", "julio", "agosto", "septiembre", "octubre", "noviembre", "diciembre"].indexOf(checkOutMes.toLowerCase()) + 1;

        if (checkInNumeroMes < 10 || checkOutNumeroMes < 10) {
          checkInNumeroMes = "0" + checkInNumeroMes;
          checkOutNumeroMes = "0" + checkOutNumeroMes;
        }
        var checkInDef = checkInAno + "-" + checkInNumeroMes + "-" + checkInDia;
        var checkOutDef = checkOutAno + "-" + checkOutNumeroMes + "-" + checkOutDia;

        const data1 = {nif: nif, checkIn: checkInDef, checkOut: checkOutDef, huespedes: huespedes, habitaciones: habitaciones};
        const address1 = "api/v1/reservas/registro";
        fetch(address1, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data1)
        })
        .then(response => response.json())
        .then(data1 => {
            console.log(data1);
            if(data1.result == "OK") {
                console.log("Datos correctos");
                var checkInDefi = new Date(checkInDef);
                var checkOutDefi = new Date(checkOutDef);
                const data2 = {"id": null, "nif":nif, "hotel": 'Gran-Melia-Palacio-de-los-Duques', "destino": 'Madrid', "tipo": 'Royal-Suite', "huespedes": huespedes, "habitaciones":habitaciones, "fechaEntrada": checkInDefi, "fechaSalida": checkOutDefi};
                const address2 = "api/v1/reservas/insert";
                fetch(address2, {
                    method: 'POST',
                    headers: {
                         'Content-Type': 'application/json'
                    },
                    body: JSON.stringify(data2)
                })
                .then(response => response.json())
                .then(data2 => {
                    if(data2.result == "OK") {
                        alert("Reserva realizada correctamente. Para modificarla, cancelarla o ver los detalles, inicie sesión en su cuenta de MeliáRewards.");
                    }else{
                        alert(data2.result);
                    }
                });
            }else{
                alert("No se ha podido realizar la reserva. Por favor, revise que todos los campos introducidos son correctos.\nDebe introducir un NIF vigente, fechas de entrada y de salida coherentes, número de huéspedes y número de habitaciones.");
            }
       });
    } catch (err) {
        console.error(err.message);
    }
    return false;
}

$('#reservaFormSupremme').submit(function (e) {
    e.preventDefault();
});

$('#reservaFormSuiteDeluxe').submit(function (e) {
    e.preventDefault();
});

$('#reservaFormRoyalSuite').submit(function (e) {
    e.preventDefault();
});