async function getReservas(nombreHotel){
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
            //document.getElementById("reserva").innerHTML = "Reserva #" + reserva.id;
            //var myTableDiv = document.getElementById("tabla");
            var table = document.createElement('table');
            var tableBody = document.createElement('tbody');
            table.appendChild(tableBody);

            for(var i = 0; i < 5; i++){
              var tr = document.createElement('tr');
              tableBody.appendChild(tr);
              for(var j = 0; j < 2; j++){
                var td = document.createElement('td');
                if(j == 0){
                   td.style.width = '410px';
                   td.style.textAlign = "left";
                   if(i == 0){
                      td.appendChild(document.createTextNode("NIF empleado en la reserva:"));
                   }else if(i == 1){
                      td.appendChild(document.createTextNode("Número de huéspedes:"));
                   }else if(i == 2){
                      td.appendChild(document.createTextNode("Número de habitaciones:"));
                   }else if(i == 3){
                      td.appendChild(document.createTextNode("Fecha de entrada:"));
                   }else{
                      td.appendChild(document.createTextNode("Fecha de salida:"));
                   }
                }else{
                   td.style.width = '300px';
                   td.style.textAlign = "right";
                   if(i == 0){
                      td.appendChild(document.createTextNode(reserva.nif));
                   }else if(i == 1){
                      td.appendChild(document.createTextNode(reserva.huespedes));
                   }else if(i == 2){
                      td.appendChild(document.createTextNode(reserva.habitaciones));
                   }else if(i == 3){
                      td.appendChild(document.createTextNode(reserva.fechaEntrada));
                   }else{
                      td.appendChild(document.createTextNode(reserva.fechaSalida));
                   }
                }
                tr.appendChild(td);
              }
            }
            //myTableDiv.appendChild(table);
            div.appendChild(header);
            div.appendChild(table);
            document.getElementById("tabla").appendChild(div);
       }
    }
}

getReservas('Melia-Madrid-Princesa');