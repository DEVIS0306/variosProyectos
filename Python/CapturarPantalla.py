import pyautogui
import pytesseract
from googletrans import Translator

# Permitir al usuario seleccionar una región de la pantalla
print("Selecciona un área de la pantalla.")
region = pyautogui.screenshot(region=(pyautogui.position().x, pyautogui.position().y, 800, 600))
region.save("region.png")

# Utilizar pytesseract para reconocer el texto en la captura de pantalla
text = pytesseract.image_to_string(region)

# Traducir el texto a español
translator = Translator()
translated_text = translator.translate(text, src='en', dest='es').text

# Imprimir el texto en inglés y su traducción al español
print("Texto en inglés:")
print(text)
print("Texto traducido al español:")
print(translated_text)
