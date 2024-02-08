function generarTabla() {
    var posicion = parseInt(document.getElementById("posicion").value, 10) - 1;
  
    var carta = document.getElementById("carta").value;
    var tabla = document.getElementById("tabla");
  
    // Ocultar el formulario
    var form = document.getElementsByTagName("form")[0];
    form.style.display = "none";
  
    // Mezclar cartas
    var cartas = shuffleArray(obtenerCartas(carta,posicion));
  
    // Generar tabla
    for (var i = 0; i < 52; i++) {
      var row = tabla.insertRow();
      var idCell = row.insertCell(0);
      var nameCell = row.insertCell(1);
      idCell.innerHTML = i + 1;
  
      if (i == posicion) {
        nameCell.innerHTML = carta;
      } else {
        var cartaSeleccionada = cartas[i];
        if (cartaSeleccionada == carta) {
          // Si la carta seleccionada ya está en la tabla, obtener la siguiente carta
          if (i < 51) {
            cartaSeleccionada = cartas[i+1];
          } else {
            cartaSeleccionada = cartas[i-1];
          }
        }
        nameCell.innerHTML = cartaSeleccionada;
      }
    }
  
    // Ajustar el ancho de la tabla al 100% de la página
    tabla.style.width = "100%";
  }
  
  function obtenerCartas(cartaElegida, posicionSelecionada) {
    var valores = ["A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"];
    var palos = ["♥", "♦", "♣", "♠"];
    var cartas = [];
  
    for (var i = 0; i < palos.length; i++) {
      for (var j = 0; j < valores.length; j++) {
        cartas.push(valores[j] + palos[i]);
      }
    }
  
    // Remover la carta elegida del arreglo
    cartas = cartas.filter(function(carta) {
      return carta != cartaElegida;
    });
  
    // Agregar la carta elegida en la posición indicada
    cartas.splice(posicionSelecionada, 0, cartaElegida);
  
    return cartas;
  }
  
  function shuffleArray(array) {
    for (var i = array.length - 1; i > 0; i--) {
      var j = Math.floor(Math.random() * (i + 1));
      var temp = array[i];
      array[i] = array[j];
      array[j] = temp;
    }
    return array;
  }
  