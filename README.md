# 🚀 LOGISTPULSE - Smart Inventory

**Universidad San Francisco de Quito** · Colegio de Ciencias e Ingenierías

Este proyecto implementa la primera fase del sistema de reservas de stock para **LOGISTPULSE**. Utiliza patrones de diseño de comportamiento para garantizar que las peticiones (`POST /reservations`) sean seguras, auditables y cumplan con los *guardrails* del sistema.

## 🧠 Patrones de Diseño Implementados

Para esta entrega, se implementaron los dos primeros patrones del flujo arquitectónico:

### 1. Command (Comando)
**Propósito:** Convertir una petición en un objeto auditable con toda su información.
*   **Invoker (`ReservationController`):** Recibe la petición HTTP y dispara el comando. No sabe cómo se procesa la reserva.
*   **Command (`ReserveStockCommand`):** Encapsula la petición. Orquesta el flujo llamando a la cadena de validación y al receptor.
*   **Receiver (`InventoryReceiver`):** Contiene la lógica de negocio real (la que modificaría la base de datos).
*   *Nota:* Preparado para guardar los comandos en un `Ledger` para auditoría futura.

### 2. Chain of Responsibility (Cadena de Responsabilidad)
**Propósito:** Pasar una solicitud por una cadena de manejadores. Cada uno decide si la procesa o la reenvía.
*   Se implementó como **Guardrail** (mencionado en la diapositiva). Tanto humanos como agentes de IA pasan por la misma validación.
*   **Validadores:** `SkuValidator` -> `StockValidator`. Si un eslabón falla, lanza una excepción y frena la reserva con un motivo claro.

---

## 📂 Estructura del Proyecto

El proyecto está organizado siguiendo las mejores prácticas de Spring Boot y Maven:

```text
src/main/java/com/logistpulse/inventory/
├── api/          # El Invoker (Controlador REST)
├── chain/        # Patrón Chain (Manejadores de validación)
├── command/      # Patrón Command (Interfaz y Comando Concreto)
├── domain/       # Modelos de datos (Records)
└── receiver/     # Lógica de negocio (InventoryReceiver)
