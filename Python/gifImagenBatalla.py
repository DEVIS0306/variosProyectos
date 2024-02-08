import os
import requests

# Crear la carpeta si no existe
if not os.path.exists('pokemonGif'):
    os.makedirs('pokemonGif')

# URL base para los sprites animados
base_url = 'https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-v/black-white/animated/'

# Descargar los sprites de los primeros 151 Pokémon
for i in range(650, 1008):
    response = requests.get(base_url + str(i) + '.gif')
    
    # Guardar el sprite en un archivo
    with open('pokemonGif/' + str(i) + '.gif', 'wb') as f:
        f.write(response.content)
