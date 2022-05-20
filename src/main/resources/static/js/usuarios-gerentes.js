async function getUsuarios(){
    try{
        const address = "api/v1/usuarios";
        let request = await fetch(address, {
          method: 'GET'
        });
        if(request.ok){
           var usuarios = await request.json();
           for(var usuario of usuarios){
                //var div = document.createElement('div');
                var header = document.createElement("header");
                h4 = document.createElement("h4");
                h4.textContent = usuario.nombre + " " + usuario.apellido1 + " " + usuario.apellido2;
                header.appendChild(h4);
                var table = document.createElement('table');
                var tableBody = document.createElement('tbody');
                table.appendChild(tableBody);

                for(var i = 0; i < 5; i++){
                  var tr = document.createElement('tr');
                  tableBody.appendChild(tr);
                  for(var j = 0; j < 2; j++){
                    if(j === 1 && i === 0){
                        break;
                    }else{
                        var td = document.createElement('td');
                        if(j == 0){
                           td.style.width = '240px';
                           td.style.textAlign = "left";
                           if(i == 0){
                              td.appendChild(h4);
                           }else if(i == 1){
                              var bold = document.createElement('strong');
                              var nif = document.createTextNode("NIF");
                              bold.appendChild(nif);
                              td.appendChild(bold);
                              td.appendChild(document.createTextNode(" del usuario:"));
                           }else if(i == 2){
                              var bold = document.createElement('strong');
                              var nombre = document.createTextNode("Correo electrónico");
                              bold.appendChild(nombre);
                              td.appendChild(bold);
                              td.appendChild(document.createTextNode(":"));
                           }else if(i == 3){
                              var bold = document.createElement('strong');
                              var cumpleanos = document.createTextNode("cumpleaños");
                              td.appendChild(document.createTextNode("Fecha de "));
                              bold.appendChild(cumpleanos);
                              td.appendChild(bold);
                              td.appendChild(document.createTextNode(":"));
                           }else{
                              var bold = document.createElement('strong');
                              var rol = document.createTextNode("Tipo");
                              bold.appendChild(rol);
                              td.appendChild(bold);
                              td.appendChild(document.createTextNode(" de usuario:"));
                           }
                        }else{
                           td.style.width = '240px';
                           td.style.textAlign = "right";
                           if(i == 1){
                              var bold = document.createElement('strong');
                              var nif = document.createTextNode(usuario.nif);
                              bold.appendChild(nif);
                              td.appendChild(bold);
                           }else if(i == 2){
                              var bold = document.createElement('strong');
                              var correo = document.createTextNode(usuario.correo);
                              bold.appendChild(correo);
                              td.appendChild(bold);
                           }else if(i == 3){
                              var cumpleanosDia = usuario.cumpleanos.substr(8,9);
                              var cumpleanosMesAnt = usuario.cumpleanos.substr(5,2);
                              var cumpleanosAno = usuario.cumpleanos.substr(0,4);
                              var meses = ["Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"];
                              var cumpleanosMes = meses[parseInt(cumpleanosMesAnt) - 1]
                              var bold = document.createElement('strong');
                              var cumpleanos = document.createTextNode(cumpleanosDia + " " + cumpleanosMes + ", " + cumpleanosAno);
                              bold.appendChild(cumpleanos);
                              td.appendChild(bold);
                           }else{
                               var roles = {
                                     'admin': "Administrador",
                                     'cliente': "Cliente"
                               };
                              var bold = document.createElement('strong');
                              var rol = document.createTextNode(roles[usuario.rol]);
                              bold.appendChild(rol);
                              td.appendChild(bold);
                           }
                        }
                        tr.appendChild(td);
                        if(i === 0 && j === 0){
                           td.setAttribute('colSpan', '2');
                        }
                    }
                  }
                }
                table.className = "usuario-gerentes-table";
                //div.appendChild(table);
                table.style.marginBottom = "30px";
                //table.style.marginLeft = "15px";
                //table.style.marginRight = "15px";
                //div.className= "usuario-gerentes-div";*/
                document.getElementById("tabla").appendChild(table);
           }
           var div = document.createElement('div');
           var eliminar = document.createElement('button');
           eliminar.setAttribute('onclick',"eliminarUsuario();");
           eliminar.innerHTML = "Eliminar usuario";
           eliminar.className = "secondary-btn";
           div.appendChild(eliminar);
           div.style.textAlign = "center";
           div.style.marginBottom = "30px";
           document.getElementById("boton").appendChild(div);
        }
    }catch (err){
       console.error(err.message);
    }
    return false;
}

async function eliminarUsuario(){
    try{
        var nif = prompt("Introduzca el NIF del usuario del que desea borrar la cuenta.", "");
        if(nif){
            if(confirm("¿Está seguro que desea borrar la cuenta del usuario con NIF " + nif + "? Esta acción no se podrá deshacer.")){
                const address = "api/v1/usuarios/delete/" + nif;
                let request = await fetch(address, {
                    method: 'DELETE'
                });
                if(request.ok){
                    alert("La cuenta del usuario con NIF " + nif + " se ha borrado correctamente.");
                    location.reload();
                }else{
                    alert("La cuenta del usuario con NIF " + nif + " no ha podido ser borrada debido a que no existe.");
                }
            }
        }
    }catch (err){
       console.error(err.message);
    }
    return false;
}

getUsuarios();