#from gtts import gTTS

# Texto que deseas convertir en audio
#texto = "Hola, este es un ejemplo de cómo convertir texto en un archivo de audio MP3."

# Crear un objeto gTTS con velocidad personalizada y configuración de voz
#tts = gTTS(texto, lang='es', slow=False, tld='com')

# Guardar el audio en un archivo MP3
#tts.save("salida.mp3")

#print("Texto convertido y guardado como salida.mp3")
import requests
import os
from gtts import gTTS

# Crear la carpeta 'video-ES' si no existe
output_folder = 'video-ES'
if not os.path.exists(output_folder):
    os.makedirs(output_folder)

# Lista para almacenar descripciones de Pokémon
pokemon_descriptions = []

def get_pokemon_data(url):
    while url:
        response = requests.get(url)
        data = response.json()

        for result in data['results']:
            pokemon_url = result['url']
            pokemon_response = requests.get(pokemon_url)
            pokemon_data = pokemon_response.json()

            # Obtener el número de la Pokédex
            dex_number = pokemon_data['id']

            # Obtener el nombre del Pokémon
            name = pokemon_data['name'].capitalize()

            # Obtener los tipos del Pokémon
            types = ', '.join([t['type']['name'] for t in pokemon_data['types']])

            # Obtener la URL de la especie de Pokémon
            species_url = pokemon_data['species']['url']
            species_response = requests.get(species_url)
            species_data = species_response.json()

            # Buscar la descripción en español
            description = next((entry['flavor_text'] for entry in species_data['flavor_text_entries'] if entry['language']['name'] == 'es'), None)

            if description:
                # Crear una descripción y agregarla a la lista
                full_description = f"{name} es un Pokémon de tipo {types}. {description}"
                pokemon_descriptions.append((dex_number, full_description))

        # Obtener la URL de la próxima página o detenerse si no hay más páginas
        url = data['next']

# URL de la API de Pokémon
url = 'https://pokeapi.co/api/v2/pokemon/'

# Obtener datos de Pokémon y descripciones en español
get_pokemon_data(url)

# Guardar descripciones en archivos de audio MP3
for dex_number, description in pokemon_descriptions:
    # Crear el archivo MP3
    tts = gTTS(text=description, lang='es')
    mp3_filename = os.path.join(output_folder, f"{dex_number}.mp3")
    tts.save(mp3_filename)
    print(f"Descargado: {mp3_filename}")

