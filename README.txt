# Tragamonedas MVC con Concurrencia 🎰

Un juego de tragamonedas implementado en Java usando el patrón Model-View-Controller (MVC) y técnicas de concurrencia.

## Características Principales
- ✅ Patrón MVC bien definido
- 🌀 Concurrencia con SwingWorker y ExecutorService
- 🎮 Interfaz gráfica con Swing
- 🍒 Símbolos estilo emoji
- 💰 Sistema de apuestas y créditos
- 🔄 Actualización en tiempo real de la UI

## Requisitos
- Java JDK 11 o superior
- Maven (opcional)
- Sistema operativo con soporte para emojis

## Instalación y Ejecución

### Método 1: Ejecutar directamente
```bash
# Compilar
javac -d . src/com/mycompany/tragmone/*.java

# Ejecutar
java com.mycompany.tragmone.TragMone

bash
mvn clean compile exec:java

src/
  com/mycompany/tragmone/
    TragMone.java         # Clase principal
    Symbol.java           # Modelo: Representación de símbolos
    Reel.java             # Modelo: Lógica de rodillos
    SlotMachine.java      # Modelo: Núcleo del juego
    SlotMachineViewGUI.java # Vista: Interfaz gráfica
    SlotMachineController.java # Controlador: Mediador MVC
    GameThread.java       # Concurrencia: Hilos de ejecución

Implementación de Concurrencia
SwingWorker: Para operaciones de larga duración (simulación de giro)

ExecutorService: Pool de hilos para gestión de tareas

SwingUtilities.invokeLater: Actualizaciones seguras de la UI

// Ejemplo de implementación concurrente
ExecutorService executor = Executors.newFixedThreadPool(2);
executor.execute(new GameThread(controller));

