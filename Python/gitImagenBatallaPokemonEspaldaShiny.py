import os
import requests

# Crear la carpeta si no existe
if not os.path.exists('pokemonGifShinyBack'):
    os.makedirs('pokemonGifShinyBack')

# URL base para los sprites animados shiny de espalda
base_url = 'https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-v/black-white/animated/back/shiny/'

# Descargar los sprites de los primeros 649 Pokémon
for i in range(1, 650):
    response = requests.get(base_url + str(i) + '.gif')
    
    # Guardar el sprite en un archivo
    with open('pokemonGifShinyBack/' + str(i) + '.gif', 'wb') as f:
        f.write(response.content)
