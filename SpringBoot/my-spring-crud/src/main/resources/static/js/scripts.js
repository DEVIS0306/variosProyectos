function confirmarEliminacion(id) {
    if (confirm('¿Estás seguro de querer eliminar a esta persona?')) {
        window.location.href = '/eliminar/' + id;
    }
}