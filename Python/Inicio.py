#import os

# Define la carpeta donde quieres buscar las imágenes
#carpeta = './Battlers'

# Busca todos los archivos en la carpeta que contengan la palabra 'shadow' en su nombre
#for archivo in os.listdir(carpeta):
#    if 'shadow' in archivo:
        # Construye la ruta completa al archivo
 #       ruta_archivo = os.path.join(carpeta, archivo)
        
        # Elimina el archivo
  #      os.remove(ruta_archivo)






import os
import shutil

# Define la carpeta donde quieres buscar las imágenes
#carpeta = './Battlers'
# Define la carpeta donde quieres guardar las imágenes seleccionadas
#carpeta_destino = './Pokedex'

# Crea la carpeta de destino si no existe
#if not os.path.exists(carpeta_destino):
 #   os.makedirs(carpeta_destino)

# Busca todos los archivos en la carpeta que cumplan con los criterios especificados
#for archivo in os.listdir(carpeta):
 #   nombre, extension = os.path.splitext(archivo)
    # Verifica si el nombre del archivo solo contiene números y tiene la extensión .png

# Nombre del archivo de salida
# Nombre del archivo de salida
archivo_salida = "inserts.txt"

# Abrir el archivo para escribir
with open(archivo_salida, "w") as archivo:
    # Iterar desde 1 hasta 1008
    for pokemon_id in range(1, 1009):
        # Crear una cadena de inserción SQL con PokemonID, UsuarioID y Visto
        insercion = f"INSERT INTO Pokedex (UsuarioID, PokemonID, Visto) VALUES (1, {pokemon_id}, true);\n"
        
        # Escribir la inserción en el archivo
        archivo.write(insercion)

print(f"Se han generado las inserciones en '{archivo_salida}'.")
