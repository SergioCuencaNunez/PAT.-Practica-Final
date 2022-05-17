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
               for(var reserva of reservas){
                    var div = document.createElement('div');
                    var div2 = document.createElement('div');
                    var header = document.createElement("header");
                    var modificar = document.createElement('a');
                    var cancelar = document.createElement('a');
                    h4 = document.createElement("h4");
                    h4.textContent = "Reserva #" + reserva.id;
                    modificar.setAttribute('onclick',"funcion();");
                    modificar.innerText = "Modificar";
                    cancelar.setAttribute('onclick',"funcion();");
                    cancelar.innerText = "Cancelar";
                    header.appendChild(h4);
                    var table = document.createElement('table');
                    var tableBody = document.createElement('tbody');
                    table.appendChild(tableBody);

                    for(var i = 0; i < 9; i++){
                      var tr = document.createElement('tr');
                      tableBody.appendChild(tr);
                      for(var j = 0; j < 3; j++){
                        if((i === 2 || i === 3 || i === 4 || i === 5 || i === 6 || i === 7) && j === 2){
                            break;
                        }else if((j === 1 || j === 2) && i === 0){
                            break;
                        }else if((j === 1 || j === 2) && i === 8){
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
                               }else if( i == 7){
                                  var bold = document.createElement('strong');
                                  var entrada = document.createTextNode("salida");
                                  td.appendChild(document.createTextNode("Fecha de "));
                                  bold.appendChild(entrada);
                                  td.appendChild(bold);
                                  td.appendChild(document.createTextNode(":"));
                               }else{
                                  modificar.className = "secondary-btn";
                                  cancelar.className = "secondary-btn";
                                  //td.appendChild(modificar);
                                  div2.appendChild(modificar);
                                  div2.appendChild(cancelar);
                                  div2.style.textAlign = "center";
                                  td.appendChild(div2);

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
                                    img.src = "img/room/habs/princesa1.png";
                                    td.appendChild(img);
                                }else if(reserva.tipo == 'Suite'){
                                    var img = document.createElement('img');
                                    img.src = "img/room/habs/princesa2.png";
                                    td.appendChild(img);
                                }else{
                                    var img = document.createElement('img');
                                    img.src = "img/room/habs/princesa3.png";
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
                            if(i === 8 && j === 0){
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
            }
       }
    }catch (err){
       console.error(err.message);
    }
    return false;
}

getReservas();

