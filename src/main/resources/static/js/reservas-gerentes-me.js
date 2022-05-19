async function getReservas(nombreHotel){
    try{
        const address = "api/v1/reservas/hotel/" + nombreHotel;
        let request = await fetch(address, {
          method: 'GET'
        });
        if(request.ok){
           var reservas = await request.json();
           for(var reserva of reservas){
                var div = document.createElement('div');
                var header = document.createElement("header");
                h4 = document.createElement("h4");
                h4.textContent = "Reserva #" + reserva.id;
                header.appendChild(h4);
                var table = document.createElement('table');
                var tableBody = document.createElement('tbody');
                table.appendChild(tableBody);

                for(var i = 0; i < 7; i++){
                  var tr = document.createElement('tr');
                  tableBody.appendChild(tr);
                  for(var j = 0; j < 3; j++){
                    if((i === 2 || i === 3 || i === 4 || i === 5 || i === 6) && j === 2){
                        break;
                    }else if((j === 1 || j === 2) && i === 0){
                        break;
                    }else{
                        var td = document.createElement('td');
                        if(j == 0){
                           td.style.width = '320px';
                           td.style.textAlign = "left";
                           if(i == 0){
                              td.appendChild(h4);
                           }else if(i == 1){
                              var bold = document.createElement('strong');
                              var nif = document.createTextNode("NIF");
                              bold.appendChild(nif);
                              td.appendChild(bold);
                              td.appendChild(document.createTextNode(" empleado en la reserva:"));
                           }else if(i == 2){
                              var bold = document.createElement('strong');
                              var tipo = document.createTextNode("Tipo");
                              bold.appendChild(tipo);
                              td.appendChild(bold);
                              td.appendChild(document.createTextNode(" de habitación:"));
                           }else if(i == 3){
                              var bold = document.createElement('strong');
                              var huespedes = document.createTextNode("huéspedes");
                              td.appendChild(document.createTextNode("Número de "));
                              bold.appendChild(huespedes);
                              td.appendChild(bold);
                              td.appendChild(document.createTextNode(":"));
                           }else if(i == 4){
                              var bold = document.createElement('strong');
                              var habitaciones = document.createTextNode("habitaciones");
                              td.appendChild(document.createTextNode("Número de "));
                              bold.appendChild(habitaciones);
                              td.appendChild(bold);
                              td.appendChild(document.createTextNode(":"));
                           }else if(i == 5){
                              var bold = document.createElement('strong');
                              var entrada = document.createTextNode("entrada");
                              td.appendChild(document.createTextNode("Fecha de "));
                              bold.appendChild(entrada);
                              td.appendChild(bold);
                              td.appendChild(document.createTextNode(":"));
                           }else{
                              var bold = document.createElement('strong');
                              var entrada = document.createTextNode("salida");
                              td.appendChild(document.createTextNode("Fecha de "));
                              bold.appendChild(entrada);
                              td.appendChild(bold);
                              td.appendChild(document.createTextNode(":"));
                           }
                        }else if(j == 1){
                           td.style.width = '320px';
                           td.style.textAlign = "right";
                           if(i == 1){
                              var bold = document.createElement('strong');
                              var nif = document.createTextNode(reserva.nif);
                              bold.appendChild(nif);
                              td.appendChild(bold);
                           }else if(i == 2){
                               var habitaciones = {
                                 'Mode': "Habitación Mode",
                                 'Suite-Personality': "Suite Personality",
                                 'Chic-Penthouse-Suite': "Chic Penthouse Suite"
                               };
                              var bold = document.createElement('strong');
                              var habitacion = document.createTextNode(habitaciones[reserva.tipo]);
                              bold.appendChild(habitacion);
                              td.appendChild(bold);
                           }else if(i == 3){
                              var bold = document.createElement('strong');
                              var huespedes = document.createTextNode(reserva.huespedes);
                              bold.appendChild(huespedes);
                              td.appendChild(bold);
                           }else if(i == 4){
                              var bold = document.createElement('strong');
                              var habitaciones = document.createTextNode(reserva.habitaciones);
                              bold.appendChild(habitaciones);
                              td.appendChild(bold);
                           }else if(i == 5){
                              var fechaEntradaDia = reserva.fechaEntrada.substr(8,9);
                              var fechaEntradaMesAnt = reserva.fechaEntrada.substr(5,2);
                              var fechaEntradaAno = reserva.fechaEntrada.substr(0,4);
                              var meses = ["Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"];
                              var fechaEntradaMes = meses[parseInt(fechaEntradaMesAnt) - 1]
                              var bold = document.createElement('strong');
                              var fechaEntrada = document.createTextNode(fechaEntradaDia + " " + fechaEntradaMes + ", " + fechaEntradaAno);
                              bold.appendChild(fechaEntrada);
                              td.appendChild(bold);
                           }else{
                              var fechaSalidaDia = reserva.fechaSalida.substr(8,9);
                              var fechaSalidaMesAnt = reserva.fechaSalida.substr(5,2);
                              var fechaSalidaAno = reserva.fechaSalida.substr(0,4);
                              var meses = ["Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"];
                              var fechaSalidaMes = meses[parseInt(fechaSalidaMesAnt) - 1]
                              var bold = document.createElement('strong');
                              var fechaSalida = document.createTextNode(fechaSalidaDia + " " + fechaSalidaMes + ", " + fechaSalidaAno);
                              bold.appendChild(fechaSalida);
                              td.appendChild(bold);
                           }
                        }else{
                            td.style.width = '395px';
                            if(reserva.tipo == 'Mode'){
                                var img = document.createElement('img');
                                img.src = "img/room/habs/me1.png";
                                td.appendChild(img);
                            }else if(reserva.tipo == 'Suite-Personality'){
                                var img = document.createElement('img');
                                img.src = "img/room/habs/me2.png";
                                td.appendChild(img);
                            }else{
                                var img = document.createElement('img');
                                img.src = "img/room/habs/cdg3.png";
                                td.appendChild(img);
                            }
                        }
                        tr.appendChild(td);
                        if(i === 1 && j === 2){
                           td.setAttribute('rowSpan', '7');
                        }
                        if(i === 0 && j === 0){
                           td.setAttribute('colSpan', '3');
                        }
                    }
                  }
                }
                table.style.marginTop = "20px";
                table.style.marginBottom = "20px";
                div.appendChild(table);
                div.style.border = "1px solid #ebebeb";
                div.style.marginBottom = "30px";
                document.getElementById("tabla").appendChild(div);
           }
           var div2 = document.createElement('div');
           var cancelar = document.createElement('button');
           cancelar.setAttribute('onclick',"cancelarReserva('ME-London');");
           cancelar.innerHTML = "Cancelar reserva";
           cancelar.className = "secondary-btn";
           div2.appendChild(cancelar);
           div2.style.textAlign = "center";
           document.getElementById("tabla").appendChild(div2);

        }
    }catch (err){
       console.error(err.message);
    }
    return false;
}

async function cancelarReserva(nombreHotel){
    try{
        var reservaId = prompt("Introduzca el ID de la reserva que desea cancelar.", "");
        if(reservaId){
            const address = "api/v1/reservas/id/" + reservaId;
            let request = await fetch(address, {
                method: 'GET'
            });
            if(request.ok){
                var reserva = await request.json();
                const address1 = "api/v1/reservas/delete/hotel/" + nombreHotel;
                const data1 = {id: reserva.id, nif: reserva.nif, hotel: reserva.hotel, destino: reserva.destino, tipo: reserva.tipo, huespedes: reserva.huespedes, habitaciones: reserva.habitaciones, fechaEntrada: reserva.fechaEntrada, fechaSalida: reserva.fechaSalida};
                if(confirm("¿Está seguro que desea cancelar la reserva #" + reservaId + "? Esta acción no se podrá deshacer.")){
                    fetch(address1, {
                        method: 'DELETE',
                        headers: {
                            'Content-Type': 'application/json'
                        },
                        body: JSON.stringify(data1)
                    })
                    .then(response => response.json())
                    .then(data1 => {
                        if(data1.result == "OK") {
                            alert("La reserva #" + reservaId + " se ha cancelado correctamente.");
                            location.reload();
                        }else if(data1.result == "No se puede eliminar la reserva seleccionada puesto que no pertenece a este hotel."){
                            alert(data1.result);
                        }else{
                            alert("La reserva #" + reservaId + " no ha podido ser cancelada debido a que no existe.");
                        }
                    });
                }
            }else{
                alert("La reserva #" + reservaId + " no ha podido ser cancelada debido a que no existe.");
            }
        }
    }catch (err){
       console.error(err.message);
    }
    return false;
}

getReservas('ME-London');