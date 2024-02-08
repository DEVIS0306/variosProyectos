import os
import shutil
import subprocess

# Rutas de los archivos
ruta_origen = r"C:\Users\User\Documents\uenlinea\ear-module\target\uenlinear.ear"
ruta_destino = r"C:\Oracle\Middleware\Oracle_Home\user_projects\domains\base_domain\servers\AdminServer\upload\uenlinear.ear"
ruta_weblogic = r"C:\Oracle\Middleware\Oracle_Home\user_projects\domains\base_domain"

# Copiar y reemplazar el archivo
if os.path.isfile(ruta_origen):
    shutil.copy2(ruta_origen, ruta_destino)
    print("Archivo copiado y reemplazado con éxito.")
else:
    print("El archivo de origen no existe.")

# Cerrar el proceso starWebLogic.cmd si está abierto
try:
    subprocess.call(["taskkill", "/F", "/IM", "cmd.exe", "/FI", "WindowTitle eq starWebLogic.cmd*"])
    print("Proceso cerrado con éxito.")
except Exception as e:
    print(f"Hubo un error al cerrar el proceso: {e}")

# Ejecutar el comando starWebLogic.cmd
try:
    subprocess.call([os.path.join(ruta_weblogic, "starWebLogic.cmd")])
    print("Comando ejecutado con éxito.")
except Exception as e:
    print(f"Hubo un error al ejecutar el comando: {e}")
