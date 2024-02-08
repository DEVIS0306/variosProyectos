import os
import requests
from PIL import Image
from io import BytesIO

# Crear la carpeta 'imagenPokemonShinyDeEspalda' si no existe
output_folder = 'imagenPokemonShinyDeEspalda'
if not os.path.exists(output_folder):
    os.makedirs(output_folder)

# URL de la API de Pokémon
url = 'https://pokeapi.co/api/v2/pokemon/'

while url:
    response = requests.get(url)
    data = response.json()

    for result in data['results']:
        pokemon_url = result['url']
        pokemon_response = requests.get(pokemon_url)
        pokemon_data = pokemon_response.json()

        # Obtener el número de la Pokédex y el nombre del Pokémon
        dex_number = pokemon_data['id']
        name = pokemon_data['name']

        # Obtener la URL de la imagen del Pokémon Shiny de espalda
        image_url = pokemon_data['sprites']['back_shiny']
        if image_url:  # Solo descargar si el Pokémon tiene una imagen Shiny de espalda
            image_response = requests.get(image_url)

            # Guardar la imagen en un archivo .png con el número de la Pokédex como nombre
            image = Image.open(BytesIO(image_response.content))
            image.save(os.path.join(output_folder, f"{dex_number}.png"))

            print(f"Descargada la imagen Shiny de espalda de {name} con el número de Pokédex {dex_number}")

    # Obtener la URL de la próxima página o detenerse si no hay más páginas
    url = data['next']
