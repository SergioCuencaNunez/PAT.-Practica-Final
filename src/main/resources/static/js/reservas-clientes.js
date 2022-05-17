const fechaValida = (fecha) => {
  return fecha instanceof Date && !isNaN(fecha);
}

async function getReservas(){
    try{
       const address0 = "api/v1/usuarios/correo/" + getCorreo();
       let request0 = await fetch(address0, {
           method: 'GET'
       });
       if(request0.ok){
            var usuario = await request0.json();
            const address1 = "api/v1/reservas/nif/" + usuario.nif;
            let request1 = await fetch(address1, {
              method: 'GET'
            });
            if(request1.ok){
               var reservas = await request1.json();
               if(reservas.length === 0){
                    var div = document.createElement('div');
                    var header = document.createElement("header");
                    h4 = document.createElement("h4");
                    h4.textContent = "No hay reservas realizadas.";
                    div.appendChild(h4);
                    div.style.textAlign = "center";
                    div.style.marginBottom = "30px";
                    document.getElementById("tabla").appendChild(div);
               }else{
                    for(var reserva of reservas){
                        var div = document.createElement('div');
                        var header = document.createElement("header");
                        h4 = document.createElement("h4");
                        h4.textContent = "Reserva #" + reserva.id;
                        header.appendChild(h4);
                        var table = document.createElement('table');
                        var tableBody = document.createElement('tbody');
                        table.appendChild(tableBody);

                        for(var i = 0; i < 8; i++){
                          var tr = document.createElement('tr');
                          tableBody.appendChild(tr);
                          for(var j = 0; j < 3; j++){
                            if((i === 2 || i === 3 || i === 4 || i === 5 || i === 6 || i === 7) && j === 2){
                                break;
                            }else if((j === 1 || j === 2) && i === 0){
                                break;
                            }else{
                                var td = document.createElement('td');
                                if(j == 0){
                                   td.style.width = '251px';
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
                                      var nif = document.createTextNode("Hotel");
                                      bold.appendChild(nif);
                                      td.appendChild(bold);
                                      td.appendChild(document.createTextNode(" de la reserva:"));
                                   }else if(i == 3){
                                      var bold = document.createElement('strong');
                                      var tipo = document.createTextNode("Tipo");
                                      bold.appendChild(tipo);
                                      td.appendChild(bold);
                                      td.appendChild(document.createTextNode(" de habitación:"));
                                   }else if(i == 4){
                                      var bold = document.createElement('strong');
                                      var huespedes = document.createTextNode("huéspedes");
                                      td.appendChild(document.createTextNode("Número de "));
                                      bold.appendChild(huespedes);
                                      td.appendChild(bold);
                                      td.appendChild(document.createTextNode(":"));
                                   }else if(i == 5){
                                      var bold = document.createElement('strong');
                                      var habitaciones = document.createTextNode("habitaciones");
                                      td.appendChild(document.createTextNode("Número de "));
                                      bold.appendChild(habitaciones);
                                      td.appendChild(bold);
                                      td.appendChild(document.createTextNode(":"));
                                   }else if(i == 6){
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
                                   td.style.width = '310px';
                                   td.style.textAlign = "right";
                                   if(i == 1){
                                      var bold = document.createElement('strong');
                                      var nif = document.createTextNode(reserva.nif);
                                      bold.appendChild(nif);
                                      td.appendChild(bold);
                                   }else if(i == 2){
                                       var hoteles = {
                                             'Melia-Madrid-Princesa': "Meliá Madrid Princesa",
                                             'Gran-Melia-Palacio-de-los-Duques': "Gran Meliá Palacio de los Duques",
                                             'Melia-White-House': "Meliá White House",
                                             'ME-London': "ME London",
                                             'Innside-Paris-Charles-de-Gaulle': "Innside Paris Charles de Gaulle",
                                             'Melia-Paris-La-Defense': "Meliá Paris La Défense",
                                             'TRYP-New-York-Times-Square': "TRYP New York Times Square",
                                             'Innside-New-York-Nomad': "Innside New York Nomad"
                                       };
                                      var bold = document.createElement('strong');
                                      var habitacion = document.createTextNode(hoteles[reserva.hotel]);
                                      bold.appendChild(habitacion);
                                      td.appendChild(bold);
                                   }else if(i == 3){
                                       var habitaciones = {
                                         'Premium': "Habitación Premium",
                                         'Suite': "Suite The Level",
                                         'Grand-Suite-Presidencial': "Grand Suite Presidencial The Level",
                                         'Supremme': " Habitación Supremme",
                                         'Suite-Deluxe': "Suite Deluxe RedLevel",
                                         'Royal-Suite': "Royal Suite RedLevel",
                                         'Premium-King': "Habitación Premium King",
                                         'Junior-Suite': "Junior Suite The Level",
                                         'Marylebone-Suite': "The Marylebone Suite",
                                         'Mode': "Habitación Mode",
                                         'Suite-Personality': "Suite Personality",
                                         'Chic-Penthouse-Suite': "Chic Penthouse Suite",
                                         'Premium-Extra': "Habitación Premium Extra",
                                         'Loft': "The Loft",
                                         'Townhouse-Suite': "The Townhouse Suite",
                                         'Premium-Twin': "Habitación Premium Twin",
                                         'Suite-Premium': "Suite Premium The Level",
                                         'Grand-Suite-Eiffel': "Grand Suite Torre Eiffel The Level",
                                         'Queen': "Habitación Queen",
                                         'King': "Habitación King",
                                         'Junior-Suite-Metro': "Junior Metro Suite",
                                         'King-City': "Habitación King City",
                                         'Studio': "The Studio with Terrace",
                                         'Townhouse-Junior-Suite': "The Townhouse Junior Suite"
                                       };
                                      var bold = document.createElement('strong');
                                      var habitacion = document.createTextNode(habitaciones[reserva.tipo]);
                                      bold.appendChild(habitacion);
                                      td.appendChild(bold);
                                   }else if(i == 4){
                                      var bold = document.createElement('strong');
                                      var huespedes = document.createTextNode(reserva.huespedes);
                                      bold.appendChild(huespedes);
                                      td.appendChild(bold);
                                   }else if(i == 5){
                                      var bold = document.createElement('strong');
                                      var habitaciones = document.createTextNode(reserva.habitaciones);
                                      bold.appendChild(habitaciones);
                                      td.appendChild(bold);
                                   }else if(i == 6){
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
                                    td.style.width = '474px';
                                    if(reserva.tipo == 'Premium'){
                                        var img = document.createElement('img');
                                        img.src = "img/room/habs-reservas/princesa1.png";
                                        td.appendChild(img);
                                    }else if(reserva.tipo == 'Suite'){
                                        var img = document.createElement('img');
                                        img.src = "img/room/habs-reservas/princesa2.png";
                                        td.appendChild(img);
                                    }else if(reserva.tipo == 'Grand-Suite-Presidencial'){
                                        var img = document.createElement('img');
                                        img.src = "img/room/habs-reservas/princesa3.png";
                                        td.appendChild(img);
                                    }else if(reserva.tipo == 'Supremme'){
                                        var img = document.createElement('img');
                                        img.src = "img/room/habs-reservas/duques1.png";
                                        td.appendChild(img);
                                    }else if(reserva.tipo == 'Suite-Deluxe'){
                                        var img = document.createElement('img');
                                        img.src = "img/room/habs-reservas/duques2.png";
                                        td.appendChild(img);
                                    }else if(reserva.tipo == 'Royal-Suite'){
                                        var img = document.createElement('img');
                                        img.src = "img/room/habs-reservas/duques3.png";
                                        td.appendChild(img);
                                    }else if(reserva.tipo == 'Premium-King'){
                                        var img = document.createElement('img');
                                        img.src = "img/room/habs-reservas/wh1.png";
                                        td.appendChild(img);
                                    }else if(reserva.tipo == 'Junior-Suite'){
                                        var img = document.createElement('img');
                                        img.src = "img/room/habs-reservas/wh2.png";
                                        td.appendChild(img);
                                    }else if(reserva.tipo == 'Marylebone-Suite'){
                                        var img = document.createElement('img');
                                        img.src = "img/room/habs-reservas/wh3.png";
                                        td.appendChild(img);
                                    }else if(reserva.tipo == 'Mode'){
                                        var img = document.createElement('img');
                                        img.src = "img/room/habs-reservas/me1.png";
                                        td.appendChild(img);
                                    }else if(reserva.tipo == 'Suite-Personality'){
                                        var img = document.createElement('img');
                                        img.src = "img/room/habs-reservas/me2.png";
                                        td.appendChild(img);
                                    }else if(reserva.tipo == 'Chic-Penthouse-Suite'){
                                        var img = document.createElement('img');
                                        img.src = "img/room/habs-reservas/me3.png";
                                        td.appendChild(img);
                                    }else if(reserva.tipo == 'Premium-Extra'){
                                        var img = document.createElement('img');
                                        img.src = "img/room/habs-reservas/cdg1.png";
                                        td.appendChild(img);
                                    }else if(reserva.tipo == 'Loft'){
                                        var img = document.createElement('img');
                                        img.src = "img/room/habs-reservas/cdg2.png";
                                        td.appendChild(img);
                                    }else if(reserva.tipo == 'Townhouse-Suite'){
                                        var img = document.createElement('img');
                                        img.src = "img/room/habs-reservas/cdg3.png";
                                        td.appendChild(img);
                                    }else if(reserva.tipo == 'Premium-Twin'){
                                        var img = document.createElement('img');
                                        img.src = "img/room/habs-reservas/defense1.png";
                                        td.appendChild(img);
                                    }else if(reserva.tipo == 'Suite-Premium'){
                                        var img = document.createElement('img');
                                        img.src = "img/room/habs-reservas/defense2.png";
                                        td.appendChild(img);
                                    }else if(reserva.tipo == 'Grand-Suite-Eiffel'){
                                        var img = document.createElement('img');
                                        img.src = "img/room/habs-reservas/defense3.png";
                                        td.appendChild(img);
                                    }else if(reserva.tipo == 'Queen'){
                                        var img = document.createElement('img');
                                        img.src = "img/room/habs-reservas/ts1.png";
                                        td.appendChild(img);
                                    }else if(reserva.tipo == 'King'){
                                        var img = document.createElement('img');
                                        img.src = "img/room/habs-reservas/ts2.png";
                                        td.appendChild(img);
                                    }else if(reserva.tipo == 'Junior-Suite-Metro'){
                                        var img = document.createElement('img');
                                        img.src = "img/room/habs-reservas/ts3.png";
                                        td.appendChild(img);
                                    }else if(reserva.tipo == 'King-City'){
                                        var img = document.createElement('img');
                                        img.src = "img/room/habs-reservas/nomad1.png";
                                        td.appendChild(img);
                                    }else if(reserva.tipo == 'Studio'){
                                        var img = document.createElement('img');
                                        img.src = "img/room/habs-reservas/nomad2.png";
                                        td.appendChild(img);
                                    }else{
                                        var img = document.createElement('img');
                                        img.src = "img/room/habs-reservas/nomad3.png";
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
                        var modificar = document.createElement('button');
                        var cancelar = document.createElement('button');
                        modificar.setAttribute('onclick',"modificarReserva();");
                        modificar.innerHTML = "Modificar reserva";
                        cancelar.setAttribute('onclick',"cancelarReserva();");
                        cancelar.innerHTML = "Cancelar reserva";
                        modificar.className = "secondary-btn";
                        cancelar.className = "secondary-btn";
                        div2.appendChild(modificar);
                        div2.appendChild(cancelar);
                        div2.style.textAlign = "center";
                        document.getElementById("tabla").appendChild(div2);
               }
            }
       }
    }catch (err){
       console.error(err.message);
    }
    return false;
}

async function cancelarReserva(){
    try{
        var reservaId = prompt("Introduzca el ID de la reserva que desea cancelar.", "");
        if(reservaId){
            if(confirm("¿Está seguro que desea cancelar la reserva con ID: " + reservaId + "? Esta acción no se podrá deshacer.")){
                const address = "api/v1/reservas/delete/" + reservaId;
                let request = await fetch(address, {
                    method: 'DELETE'
                });
                if(request.ok){
                    alert("La reserva #" + reservaId + " se ha cancelado correctamente.");
                    location.reload();
                }else{
                    alert("La reserva #" + reservaId + " no ha podido ser cancelada debido a que no existe.");
                }
            }
        }
    }catch (err){
       console.error(err.message);
    }
    return false;
}

async function modificarReserva(){
    try{
        var reservaId = prompt("Introduzca el ID de la reserva que desea modificar.", "");
        if(reservaId){
            const address0 = "api/v1/reservas/id/" + reservaId;
            let request0 = await fetch(address0, {
               method: 'GET'
            });
            if(request0.ok){
                 var reserva = await request0.json();
                 var fechaEntradaDia = reserva.fechaEntrada.substr(8,9);
                 var fechaEntradaMesAnt = reserva.fechaEntrada.substr(5,2);
                 var fechaEntradaAno = reserva.fechaEntrada.substr(0,4);
                 var meses = ["Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"];
                 var fechaEntradaMes = meses[parseInt(fechaEntradaMesAnt) - 1]
                 var fechaEntrada = fechaEntradaDia + " " + fechaEntradaMes + ", " + fechaEntradaAno;
                 var fechaSalidaDia = reserva.fechaSalida.substr(8,9);
                 var fechaSalidaMesAnt = reserva.fechaSalida.substr(5,2);
                 var fechaSalidaAno = reserva.fechaSalida.substr(0,4);
                 var fechaSalidaMes = meses[parseInt(fechaSalidaMesAnt) - 1]
                 var fechaSalida = fechaSalidaDia + " " + fechaSalidaMes + ", " + fechaSalidaAno;
                 var destinos = {
                     'Madrid': "Madrid",
                     'Londres': "Londres",
                     'Paris': "París",
                     'Nueva-York': "Nueva York"
                 };
                 var hoteles = {
                     'Melia-Madrid-Princesa': "Meliá Madrid Princesa",
                     'Gran-Melia-Palacio-de-los-Duques': "Gran Meliá Palacio de los Duques",
                     'Melia-White-House': "Meliá White House",
                     'ME-London': "ME London",
                     'Innside-Paris-Charles-de-Gaulle': "Innside Paris Charles de Gaulle",
                     'Melia-Paris-La-Defense': "Meliá Paris La Défense",
                     'TRYP-New-York-Times-Square': "TRYP New York Times Square",
                     'Innside-New-York-Nomad': "Innside New York Nomad"
                 };
                 var habitaciones = {
                      'Premium': "Habitación Premium",
                      'Suite': "Suite The Level",
                      'Grand-Suite-Presidencial': "Grand Suite Presidencial The Level",
                      'Supremme': " Habitación Supremme",
                      'Suite-Deluxe': "Suite Deluxe RedLevel",
                      'Royal-Suite': "Royal Suite RedLevel",
                      'Premium-King': "Habitación Premium King",
                      'Junior-Suite': "Junior Suite The Level",
                      'Marylebone-Suite': "The Marylebone Suite",
                      'Mode': "Habitación Mode",
                      'Suite-Personality': "Suite Personality",
                      'Chic-Penthouse-Suite': "Chic Penthouse Suite",
                      'Premium-Extra': "Habitación Premium Extra",
                      'Loft': "The Loft",
                      'Townhouse-Suite': "The Townhouse Suite",
                      'Premium-Twin': "Habitación Premium Twin",
                      'Suite-Premium': "Suite Premium The Level",
                      'Grand-Suite-Eiffel': "Grand Suite Torre Eiffel The Level",
                      'Queen': "Habitación Queen",
                      'King': "Habitación King",
                      'Junior-Suite-Metro': "Junior Metro Suite",
                      'King-City': "Habitación King City",
                      'Studio': "The Studio with Terrace",
                      'Townhouse-Junior-Suite': "The Townhouse Junior Suite"
                 };
                 var parametroAnti = prompt("Reserva #" + reserva.id + "\nPuede modificar los siguientes parámetros: " + "\n\u2022 Tipo de habitación reservada: " + habitaciones[reserva.tipo] + "\n\u2022 Número de huéspedes: " + reserva.huespedes + "\n\u2022 Número de habitaciones: " + reserva.habitaciones + "\n\u2022 Fecha de entrada: " + fechaEntrada + "\n\u2022 Fecha de salida: " + fechaSalida + "\nIntroduzca el parámetro que desea modificar. Si desea cambiar el NIF o el hotel, cancele la reserva.","");
                 if(parametroAnti){
                     var parametroAnt = parametroAnti.toLowerCase();
                     var parametro = parametroAnt.normalize('NFD').replace(/[\u0300-\u036f]/g, "")
                     if(parametro == "tipo de habitacion reservada" || parametro == "tipo de habitacion" || parametro == "tipo habitacion" || parametro == "habitacion" || parametro == "habitacion reservada"){
                           const address1 = "api/v1/habitaciones/hotel/" + reserva.hotel;
                           let request1 = await fetch(address1, {
                               method: 'GET'
                           });
                           if(request1.ok){
                               var habitaciones1 = await request1.json();
                               var tipoHabitacion = prompt("Tipos de habitaciones disponibles: " + "\n 1. - " + habitaciones[habitaciones1[0].tipo] + "\n 2. - " + habitaciones[habitaciones1[1].tipo] + "\n 3. - " + habitaciones[habitaciones1[2].tipo] + "\nSeleccione el número del tipo de habitación a la cuál desea cambiar.","");
                               if(tipoHabitacion){
                                    if(tipoHabitacion == "1" || tipoHabitacion == "2" || tipoHabitacion == "3"){
                                        const address2 = "api/v1/habitaciones/capacidad/" + habitaciones1[(parseInt(tipoHabitacion))-1].tipo;
                                        let request2 = await fetch(address2, {
                                              method: 'GET'
                                        });
                                        if(request2.ok){
                                            var capacidad = await request2.json();
                                            if(reserva.huespedes <= capacidad){
                                                const address3 = "api/v1/reservas/update/tipo/" + reserva.id + "/" + habitaciones1[(parseInt(tipoHabitacion))-1].tipo;
                                                let request3 = await fetch(address3, {
                                                      method: 'PUT'
                                                });
                                                if(request3.ok){
                                                    alert('El tipo de habitación de la reserva #' + reserva.id + " se ha modificado correctamente.");
                                                    location.reload();
                                                }else{
                                                    alert('No se ha podido modificar el tipo de habitación de la reserva #' + reserva.id + '.');
                                                }
                                            }else{
                                                alert('No se han podido modificar el tipo de habitación de la reserva #' + reserva.id + ' debido a que el número de huéspedes es superior a la capacidad de la habitación.');
                                            }
                                        }
                                    }else{
                                        alert("Ha introducido un número no válido. La reserva #" + reserva.id + " no ha podido ser modificada.");
                                    }
                               }
                           }
                     }else if(parametro == "numero de huespedes" || parametro == "numero huespedes" || parametro == "huespedes"){
                           var numeroHuespedes = prompt("Introduzca el nuevo número de huéspedes de acuerdo al tipo de habitación elegida.");
                           if(numeroHuespedes){
                               const address1 = "api/v1/habitaciones/capacidad/" + reserva.tipo;
                               let request1 = await fetch(address1, {
                                   method: 'GET'
                               });
                               if(request1.ok){
                                   var capacidad = await request1.json();
                                   if((parseInt(numeroHuespedes)) <= capacidad){
                                        const address2 = "api/v1/reservas/update/huespedes/" + reserva.id + "/" + numeroHuespedes;
                                        let request2 = await fetch(address2, {
                                           method: 'PUT'
                                        });
                                        if(request2.ok){
                                            alert('El número de huéspedes de la reserva #' + reserva.id + " se ha modificado correctamente.");
                                            location.reload();
                                        }else{
                                            alert('No se ha podido modificar el número de huéspedes de la reserva #' + reserva.id + '.');
                                        }
                                   }else{
                                        alert('No se han podido modificar el número de huéspedes de la reserva #' + reserva.id + ' debido a que es superior a la capacidad de la habitación elegida.');
                                   }
                              }
                           }
                     }else if(parametro == "numero de habitaciones" || parametro == "numero habitaciones" || parametro == "habitaciones"){
                           var numeroHabitacionesAnti = prompt("Introduzca el nuevo número de habitaciones.");
                           if(numeroHabitacionesAnti){
                                var numeroHabitaciones = parseInt(numeroHabitacionesAnti);
                                if(numeroHabitaciones == 0){
                                    alert('Si desea modificar las habitaciones de la reserva #' + reserva.id + " a 0, cancele la reserva.");
                                }else if(numeroHabitaciones < 10){
                                        const address1 = "api/v1/reservas/update/habitaciones/" + reserva.id + "/" + numeroHabitaciones;
                                        let request1 = await fetch(address1, {
                                           method: 'PUT'
                                        });
                                        if(request1.ok){
                                            alert('El número de habitaciones de la reserva #' + reserva.id + " se ha modificado correctamente.");
                                            location.reload();
                                        }else{
                                            alert('No se ha podido modificar el número de huéspedes de la reserva #' + reserva.id + '.');
                                        }
                                }else{
                                    alert('Si desea hacer una reserva de grupo (más de 9 habitaciones) o de sala de reunión, llame al 912 76 47 47 o envíe un correo electrónico a grupos@melia.com.');
                                }
                           }
                     }else if(parametro == "fecha de entrada" || parametro == "fecha entrada" || parametro == "entrada"){
                           var fechaEntradaAnti = prompt('Introduzca en el formato "AAAA/MM/DD" la nueva fecha de entrada.');
                           var fechaEntrada = new Date(fechaEntradaAnti);
                           var fechaSalida= new Date(reserva.fechaSalida);
                           if(fechaValida(fechaEntrada)){
                                if(fechaEntrada < fechaSalida){
                                        var fechaEntradaAno = fechaEntradaAnti.substr(0,4);
                                        var fechaEntradaMes = fechaEntradaAnti.substr(5,2);
                                        var fechaEntradaDia = fechaEntradaAnti.substr(8);
                                        var fechaEntradaDef = fechaEntradaAno + "-" + fechaEntradaMes + "-" + fechaEntradaDia;
                                        const address1 = "api/v1/reservas/update/fechaEntrada/" + reserva.id + "/" + fechaEntradaDef;
                                        let request1 = await fetch(address1, {
                                           method: 'PUT'
                                        });
                                        if(request1.ok){
                                            alert('La fecha de entrada de la reserva #' + reserva.id + " se ha modificado correctamente.");
                                            location.reload();
                                        }else{
                                            alert('No se ha podido modificar la fecha de entrada de la reserva #' + reserva.id + '.');
                                        }
                                }else{
                                   alert('No se ha podido modificar la fecha de entrada de la reserva #' + reserva.id + ' debido a que es superior a la fecha de salida.');
                                }
                           }else{
                                alert('No se ha podido modificar la fecha de entrada de la reserva #' + reserva.id + ' debido a que ha introducido una fecha no válida.');
                           }
                     }else if(parametro == "fecha de salida" || parametro == "fecha salida" || parametro == "salida"){
                           var fechaSalidaAnti = prompt('Introduzca en el formato "AAAA/MM/DD" la nueva fecha de salida.');
                           var fechaSalida = new Date(fechaSalidaAnti);
                           var fechaEntrada= new Date(reserva.fechaEntrada);
                           if(fechaValida(fechaSalida)){
                                if(fechaEntrada < fechaSalida){
                                        var fechaSalidaAno = fechaSalidaAnti.substr(0,4);
                                        var fechaSalidaMes = fechaSalidaAnti.substr(5,2);
                                        var fechaSalidaDia = fechaSalidaAnti.substr(8);
                                        var fechaEntradaDef = fechaSalidaAno + "-" + fechaSalidaMes + "-" + fechaSalidaDia;
                                        const address1 = "api/v1/reservas/update/fechaSalida/" + reserva.id + "/" + fechaEntradaDef;
                                        let request1 = await fetch(address1, {
                                           method: 'PUT'
                                        });
                                        if(request1.ok){
                                            alert('La fecha de salida de la reserva #' + reserva.id + " se ha modificado correctamente.");
                                            location.reload();
                                        }else{
                                            alert('No se ha podido modificar la fecha de salida de la reserva #' + reserva.id + '.');
                                        }
                                }else{
                                   alert('No se ha podido modificar la fecha de salida de la reserva #' + reserva.id + ' debido a que es inferior a la fecha de entrada.');
                                }
                           }else{
                                alert('No se ha podido modificar la fecha de salida de la reserva #' + reserva.id + ' debido a que ha introducido una fecha no válida.');
                           }
                     }
                 }
            }else{
                alert("La reserva con ID: " + reservaId + " no ha podido ser modificada debido a que no existe.");
            }
        }
    }catch (err){
       console.error(err.message);
    }
    return false;
}

getReservas();