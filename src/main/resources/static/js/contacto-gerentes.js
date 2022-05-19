async function getContactos(){
    try{
        const address = "api/v1/contactos";
        let request = await fetch(address, {
          method: 'GET'
        });
        if(request.ok){
           var contactos = await request.json();
           for(var contacto of contactos){
                var div = document.createElement('div');
                var header = document.createElement("header");
                h4 = document.createElement("h4");
                h4.textContent = "Mensaje #" + contacto.numero;
                header.appendChild(h4);
                var table = document.createElement('table');
                var tableBody = document.createElement('tbody');
                table.appendChild(tableBody);

                for(var i = 0; i < 4; i++){
                  var tr = document.createElement('tr');
                  tableBody.appendChild(tr);
                  for(var j = 0; j < 2; j++){
                    if(j === 1 && i === 0){
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
                              var correo = document.createTextNode("Correo electrónico");
                              bold.appendChild(correo);
                              td.appendChild(bold);
                              td.appendChild(document.createTextNode(":"));
                           }else if(i == 2){
                              var bold = document.createElement('strong');
                              var nombre = document.createTextNode("Nombre");
                              bold.appendChild(nombre);
                              td.appendChild(bold);
                              td.appendChild(document.createTextNode(":"));
                           }else{
                              var bold = document.createElement('strong');
                              var mensaje = document.createTextNode("Contenido/información");
                              bold.appendChild(mensaje);
                              td.appendChild(bold);
                              td.appendChild(document.createTextNode(":"));
                           }
                        }else{
                           td.style.width = '500px';
                           td.style.textAlign = "right";
                           if(i == 1){
                              var bold = document.createElement('strong');
                              var correo = document.createTextNode(contacto.correo);
                              bold.appendChild(correo);
                              td.appendChild(bold);
                           }else if(i == 2){
                              var bold = document.createElement('strong');
                              var nombre = document.createTextNode(contacto.nombre);
                              bold.appendChild(nombre);
                              td.appendChild(bold);
                           }else{
                              var mensaje = document.createTextNode(contacto.mensaje);
                              td.appendChild(mensaje);
                           }
                        }
                        tr.appendChild(td);
                        if(i === 0 && j === 0){
                           td.setAttribute('colSpan', '2');
                        }
                    }
                  }
                }
                table.className = "contacto-gerentes-table";
                div.appendChild(table);
                div.style.marginBottom = "30px";
                document.getElementById("tabla").appendChild(div);
           }
           var div2 = document.createElement('div');
           var eliminar = document.createElement('button');
           eliminar.setAttribute('onclick',"eliminarContacto();");
           eliminar.innerHTML = "Eliminar mensaje";
           eliminar.className = "secondary-btn";
           div2.appendChild(eliminar);
           div2.style.textAlign = "center";
           document.getElementById("tabla").appendChild(div2);
        }
    }catch (err){
       console.error(err.message);
    }
    return false;
}


async function eliminarContacto(){
    try{
        var contactoNumero = prompt("Introduzca el número del mensaje que desea borrar.", "");
        if(contactoNumero){
            if(confirm("¿Está seguro que desea borrar el mensaje #" + contactoNumero + "? Esta acción no se podrá deshacer.")){
                const address = "api/v1/contactos/delete/" + contactoNumero;
                let request = await fetch(address, {
                    method: 'DELETE'
                });
                if(request.ok){
                    alert("El mensaje #" + contactoNumero + " se ha borrado correctamente.");
                    location.reload();
                }else{
                    alert("El mensaje #" + contactoNumero + " no ha podido ser borrado debido a que no existe.");
                }
            }
        }
    }catch (err){
       console.error(err.message);
    }
    return false;
}

getContactos();