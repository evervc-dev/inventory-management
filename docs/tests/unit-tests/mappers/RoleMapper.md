# Casos de Prueba para RoleMapper

Este documento detalla los casos de prueba ejecutados para la clase `RoleMapper`, encargada de la conversión entre entidades `Role` y sus respectivos DTOs (`RoleRequestDto`, `RoleResponseDto`).

---

### Caso de Prueba: Mapeo de Entidad a DTO

**Nombre:** Mapeo de Entidad a DTO  
**Código:** TC-RM-001  
**Versión:** 1.0  
**Fecha de Ejecución:** 11 de Julio del 2026  
**Descripción:** Verifica que un objeto `Role` (entidad) se mapea correctamente a un `RoleResponseDto`.

**Ambiente de Pruebas:**
- **Precondiciones:**
  - El `RoleMapper` debe estar inicializado correctamente.
- **Postcondiciones:**
  - No hay cambios persistentes en el sistema.

**Desarrollo de la Prueba:**
- **Datos de Entrada:**
  - Objeto `Role` con ID `1L` y nombre "ADMIN".
- **Acciones o Pasos a Seguir:**
  1. Inicializar el `RoleMapper` (realizado en `@BeforeEach`).
  2. Crear un `RoleResponseDto` esperado con ID `1L` y nombre "ADMIN".
  3. Crear un objeto `Role` con ID `1L` y nombre "ADMIN".
  4. Invocar el método `toResponseDto()` del `roleMapper` con el objeto `Role`.
  5. Comparar el `RoleResponseDto` resultante con el esperado.

**Resultados:**
- **Resultado Esperado:** El `RoleResponseDto` resultante es igual al `RoleResponseDto` esperado (ID `1L`, nombre "ADMIN").
- **Resultado Obtenido:** La prueba se ejecutó correctamente y el resultado obtenido coincide con el esperado.

**Estado:** ![Estado](https://img.shields.io/badge/PASS-Green.svg)

**Evidencia:**
[Insertar captura de pantalla o resultado de la prueba aquí]

---

### Caso de Prueba: Mapeo de Entidad Nula a DTO

**Nombre:** Mapeo de Entidad Nula a DTO  
**Código:** TC-RM-002  
**Versión:** 1.0  
**Fecha de Ejecución:** 11 de Julio del 2026  
**Descripción:** Verifica que el mapeo de un objeto `Role` nulo a `RoleResponseDto` resulta en un valor nulo.

**Ambiente de Pruebas:**
- **Precondiciones:**
  - El `RoleMapper` debe estar inicializado correctamente.
- **Postcondiciones:**
  - No hay cambios persistentes en el sistema.

**Desarrollo de la Prueba:**
- **Datos de Entrada:**
  - Objeto `Role` con valor `null`.
- **Acciones o Pasos a Seguir:**
  1. Inicializar el `RoleMapper` (realizado en `@BeforeEach`).
  2. Invocar el método `toResponseDto()` del `roleMapper` con un valor `null`.
  3. Afirmar que el resultado es `null`.

**Resultados:**
- **Resultado Esperado:** El resultado del mapeo es `null`.
- **Resultado Obtenido:** La prueba se ejecutó correctamente y el resultado obtenido coincide con el esperado.

**Estado:** ![Estado](https://img.shields.io/badge/PASS-Green.svg)

**Evidencia:**
[Insertar captura de pantalla o resultado de la prueba aquí]

---

### Caso de Prueba: Mapeo de Entidad con ID Nulo a DTO

**Nombre:** Mapeo de Entidad con ID Nulo a DTO  
**Código:** TC-RM-003  
**Versión:** 1.0  
**Fecha de Ejecución:** 11 de Julio del 2026  
**Descripción:** Verifica el comportamiento del mapeo cuando un objeto `Role` tiene un ID nulo.

**Ambiente de Pruebas:**
- **Precondiciones:**
  - El `RoleMapper` debe estar inicializado correctamente.
- **Postcondiciones:**
  - No hay cambios persistentes en el sistema.

**Desarrollo de la Prueba:**
- **Datos de Entrada:**
  - Objeto `Role` con ID `null` y nombre "GUEST".
- **Acciones o Pasos a Seguir:**
  1. Inicializar el `RoleMapper` (realizado en `@BeforeEach`).
  2. Crear un `Role` con ID `null` y nombre "GUEST".
  3. Crear un `RoleResponseDto` esperado con ID `null` y nombre "GUEST".
  4. Invocar el método `toResponseDto()` del `roleMapper` con el objeto `Role` con ID nulo.
  5. Comparar el `RoleResponseDto` resultante con el esperado.

**Resultados:**
- **Resultado Esperado:** El `RoleResponseDto` resultante es igual al `RoleResponseDto` esperado (ID `null`, nombre "GUEST").
- **Resultado Obtenido:** La prueba se ejecutó correctamente y el resultado obtenido coincide con el esperado.

**Estado:** ![Estado](https://img.shields.io/badge/PASS-Green.svg)

**Evidencia:**
[Insertar captura de pantalla o resultado de la prueba aquí]

---

### Caso de Prueba: Mapeo de Entidad con Nombre Nulo a DTO

**Nombre:** Mapeo de Entidad con Nombre Nulo a DTO  
**Código:** TC-RM-004  
**Versión:** 1.0  
**Fecha de Ejecución:** 11 de Julio del 2026  
**Descripción:** Verifica el comportamiento del mapeo cuando un objeto `Role` tiene un nombre nulo.

**Ambiente de Pruebas:**
- **Precondiciones:**
  - El `RoleMapper` debe estar inicializado correctamente.
- **Postcondiciones:**
  - No hay cambios persistentes en el sistema.

**Desarrollo de la Prueba:**
- **Datos de Entrada:**
  - Objeto `Role` con ID `1L` y nombre `null`.
- **Acciones o Pasos a Seguir:**
  1. Inicializar el `RoleMapper` (realizado en `@BeforeEach`).
  2. Crear un `Role` con ID `1L` y nombre `null`.
  3. Crear un `RoleResponseDto` esperado con ID `1L` y nombre `null`.
  4. Invocar el método `toResponseDto()` del `roleMapper` con el objeto `Role` con nombre nulo.
  5. Comparar el `RoleResponseDto` resultante con el esperado.

**Resultados:**
- **Resultado Esperado:** El `RoleResponseDto` resultante es igual al `RoleResponseDto` esperado (ID `1L`, nombre `null`).
- **Resultado Obtenido:** La prueba se ejecutó correctamente y el resultado obtenido coincide con el esperado.

**Estado:** ![Estado](https://img.shields.io/badge/PASS-Green.svg)

**Evidencia:**
[Insertar captura de pantalla o resultado de la prueba aquí]

---

### Caso de Prueba: Mapeo de DTO a Entidad

**Nombre:** Mapeo de DTO a Entidad  
**Código:** TC-RM-005  
**Versión:** 1.0  
**Fecha de Ejecución:** 11 de Julio del 2026  
**Descripción:** Verifica que un objeto `RoleRequestDto` se mapea correctamente a un objeto `Role` (entidad).

**Ambiente de Pruebas:**
- **Precondiciones:**
  - El `RoleMapper` debe estar inicializado correctamente.
- **Postcondiciones:**
  - No hay cambios persistentes en el sistema.

**Desarrollo de la Prueba:**
- **Datos de Entrada:**
  - `RoleRequestDto` con nombre "ADMIN".
- **Acciones o Pasos a Seguir:**
  1. Inicializar el `RoleMapper` (realizado en `@BeforeEach`).
  2. Crear un objeto `Role` esperado con ID `null` y nombre "ADMIN".
  3. Crear un `RoleRequestDto` con nombre "ADMIN".
  4. Invocar el método `toCreateEntity()` del `roleMapper` con el `RoleRequestDto`.
  5. Comparar el objeto `Role` resultante con el esperado.

**Resultados:**
- **Resultado Esperado:** El objeto `Role` resultante es igual al `Role` esperado (ID `null`, nombre "ADMIN").
- **Resultado Obtenido:** La prueba se ejecutó correctamente y el resultado obtenido coincide con el esperado.

**Estado:** ![Estado](https://img.shields.io/badge/PASS-Green.svg)

**Evidencia:**
[Insertar captura de pantalla o resultado de la prueba aquí]

---

### Caso de Prueba: Mapeo de DTO Nulo a Entidad

**Nombre:** Mapeo de DTO Nulo a Entidad  
**Código:** TC-RM-006  
**Versión:** 1.0  
**Fecha de Ejecución:** 11 de Julio del 2026  
**Descripción:** Verifica que el mapeo de un `RoleRequestDto` nulo a un objeto `Role` resulta en un valor nulo.

**Ambiente de Pruebas:**
- **Precondiciones:**
  - El `RoleMapper` debe estar inicializado correctamente.
- **Postcondiciones:**
  - No hay cambios persistentes en el sistema.

**Desarrollo de la Prueba:**
- **Datos de Entrada:**
  - `RoleRequestDto` con valor `null`.
- **Acciones o Pasos a Seguir:**
  1. Inicializar el `RoleMapper` (realizado en `@BeforeEach`).
  2. Invocar el método `toCreateEntity()` del `roleMapper` con un valor `null`.
  3. Afirmar que el resultado es `null`.

**Resultados:**
- **Resultado Esperado:** El resultado del mapeo es `null`.
- **Resultado Obtenido:** La prueba se ejecutó correctamente y el resultado obtenido coincide con el esperado.

**Estado:** ![Estado](https://img.shields.io/badge/PASS-Green.svg)

**Evidencia:**
[Insertar captura de pantalla o resultado de la prueba aquí]

---

### Caso de Prueba: Mapeo de DTO Inválido a Entidad

**Nombre:** Mapeo de DTO Inválido a Entidad  
**Código:** TC-RM-007  
**Versión:** 1.0  
**Fecha de Ejecución:** 11 de Julio del 2026  
**Descripción:** Verifica el comportamiento del mapeo cuando un `RoleRequestDto` tiene un nombre nulo.

**Ambiente de Pruebas:**
- **Precondiciones:**
  - El `RoleMapper` debe estar inicializado correctamente.
- **Postcondiciones:**
  - No hay cambios persistentes en el sistema.

**Desarrollo de la Prueba:**
- **Datos de Entrada:**
  - `RoleRequestDto` con nombre `null`.
- **Acciones o Pasos a Seguir:**
  1. Inicializar el `RoleMapper` (realizado en `@BeforeEach`).
  2. Crear un `RoleRequestDto` con nombre `null`.
  3. Crear un objeto `Role` esperado con ID `null` y nombre `null`.
  4. Invocar el método `toCreateEntity()` del `roleMapper` con el `RoleRequestDto` inválido.
  5. Comparar el objeto `Role` resultante con el esperado.

**Resultados:**
- **Resultado Esperado:** El objeto `Role` resultante es igual al `Role` esperado (ID `null`, nombre `null`).
- **Resultado Obtenido:** La prueba se ejecutó correctamente y el resultado obtenido coincide con el esperado.

**Estado:** ![Estado](https://img.shields.io/badge/PASS-Green.svg)

**Evidencia:**
[Insertar captura de pantalla o resultado de la prueba aquí]

---

### Caso de Prueba: Mapeo de Lista de Roles a Lista de DTOs de Respuesta

**Nombre:** Mapeo de Lista de Roles a Lista de DTOs de Respuesta  
**Código:** TC-RM-008  
**Versión:** 1.0  
**Fecha de Ejecución:** 11 de Julio del 2026  
**Descripción:** Verifica que una lista de objetos `Role` se mapea correctamente a una lista de `RoleResponseDto`.

**Ambiente de Pruebas:**
- **Precondiciones:**
  - El `RoleMapper` debe estar inicializado correctamente.
- **Postcondiciones:**
  - No hay cambios persistentes en el sistema.

**Desarrollo de la Prueba:**
- **Datos de Entrada:**
  - Lista de `Role` con dos roles: (1L, "ADMIN") y (2L, "USER").
- **Acciones o Pasos a Seguir:**
  1. Inicializar el `RoleMapper` (realizado en `@BeforeEach`).
  2. Crear una lista de `Role` con (1L, "ADMIN") y (2L, "USER").
  3. Crear una lista de `RoleResponseDto` esperados con los mismos datos.
  4. Invocar el método `toRolesDto()` del `roleMapper` con la lista de `Role`.
  5. Comparar la lista de `RoleResponseDto` resultante con la lista esperada.

**Resultados:**
- **Resultado Esperado:** La lista de `RoleResponseDto` resultante es igual a la lista esperada.
- **Resultado Obtenido:** La prueba se ejecutó correctamente y el resultado obtenido coincide con el esperado.

**Estado:** ![Estado](https://img.shields.io/badge/PASS-Green.svg)

**Evidencia:**
[Insertar captura de pantalla o resultado de la prueba aquí]

---

### Caso de Prueba: Mapeo de Lista de Roles Nula a Lista de DTOs de Respuesta

**Nombre:** Mapeo de Lista de Roles Nula a Lista de DTOs de Respuesta  
**Código:** TC-RM-009  
**Versión:** 1.0  
**Fecha de Ejecución:** 11 de Julio del 2026  
**Descripción:** Verifica que el mapeo de una lista de `Role` nula a una lista de `RoleResponseDto` resulta en un valor nulo.

**Ambiente de Pruebas:**
- **Precondiciones:**
  - El `RoleMapper` debe estar inicializado correctamente.
- **Postcondiciones:**
  - No hay cambios persistentes en el sistema.

**Desarrollo de la Prueba:**
- **Datos de Entrada:**
  - Lista de `Role` con valor `null`.
- **Acciones o Pasos a Seguir:**
  1. Inicializar el `RoleMapper` (realizado en `@BeforeEach`).
  2. Invocar el método `toRolesDto()` del `roleMapper` con un valor `null`.
  3. Afirmar que el resultado es `null`.

**Resultados:**
- **Resultado Esperado:** El resultado del mapeo es `null`.
- **Resultado Obtenido:** La prueba se ejecutó correctamente y el resultado obtenido coincide con el esperado.

**Estado:** ![Estado](https://img.shields.io/badge/PASS-Green.svg)

**Evidencia:**
[Insertar captura de pantalla o resultado de la prueba aquí]

---

### Caso de Prueba: Mapeo de Lista de Roles con Elementos Nulos a Lista de DTOs de Respuesta

**Nombre:** Mapeo de Lista de Roles con Elementos Nulos a Lista de DTOs de Respuesta  
**Código:** TC-RM-010  
**Versión:** 1.0  
**Fecha de Ejecución:** 11 de Julio del 2026  
**Descripción:** Verifica que el mapeo de una lista de `Role` que contiene elementos nulos a una lista de `RoleResponseDto` resulta en una lista con elementos nulos correspondientes.

**Ambiente de Pruebas:**
- **Precondiciones:**
  - El `RoleMapper` debe estar inicializado correctamente.
- **Postcondiciones:**
  - No hay cambios persistentes en el sistema.

**Desarrollo de la Prueba:**
- **Datos de Entrada:**
  - Lista de `Role` con dos elementos `null`.
- **Acciones o Pasos a Seguir:**
  1. Inicializar el `RoleMapper` (realizado en `@BeforeEach`).
  2. Crear una lista de `Role` con dos elementos `null`.
  3. Crear una lista de `RoleResponseDto` esperados con dos elementos `null`.
  4. Invocar el método `toRolesDto()` del `roleMapper` con la lista de `Role` con elementos nulos.
  5. Comparar la lista de `RoleResponseDto` resultante con la lista esperada.

**Resultados:**
- **Resultado Esperado:** La lista de `RoleResponseDto` resultante es igual a la lista esperada (una lista con dos elementos `null`).
- **Resultado Obtenido:** La prueba se ejecutó correctamente y el resultado obtenido coincide con el esperado.

**Estado:** ![Estado](https://img.shields.io/badge/PASS-Green.svg)

**Evidencia:**
[Insertar captura de pantalla o resultado de la prueba aquí]

---

### Caso de Prueba: Mapeo de Lista de Roles con Valores Nulos a Lista de DTOs de Respuesta

**Nombre:** Mapeo de Lista de Roles con Valores Nulos a Lista de DTOs de Respuesta  
**Código:** TC-RM-011  
**Versión:** 1.0  
**Fecha de Ejecución:** 11 de Julio del 2026  
**Descripción:** Verifica que el mapeo de una lista de `Role` que contiene un objeto `Role` válido y un elemento nulo a una lista de `RoleResponseDto` resulta en una lista con el DTO correspondiente y un elemento nulo.

**Ambiente de Pruebas:**
- **Precondiciones:**
  - El `RoleMapper` debe estar inicializado correctamente.
- **Postcondiciones:**
  - No hay cambios persistentes en el sistema.

**Desarrollo de la Prueba:**
- **Datos de Entrada:** Lista de `Role` con un rol (1L, "ADMIN") y un elemento `null`.
- **Acciones o Pasos a Seguir:**
  1. Inicializar el `RoleMapper` (realizado en `@BeforeEach`).
  2. Crear una lista de `Role` con un rol (1L, "ADMIN") y un elemento `null`.
  3. Crear una lista de `RoleResponseDto` esperados con un DTO (1L, "ADMIN") y un elemento `null`.
  4. Invocar el método `toRolesDto()` del `roleMapper` con la lista de `Role` con valores nulos.
  5. Comparar la lista de `RoleResponseDto` resultante con la lista esperada.

**Resultados:**
- **Resultado Esperado:** La lista de `RoleResponseDto` resultante es igual a la lista esperada (una lista con un DTO (1L, "ADMIN") y un elemento `null`).
- **Resultado Obtenido:** La prueba se ejecutó correctamente y el resultado obtenido coincide con el esperado.

**Estado:** ![Estado](https://img.shields.io/badge/PASS-Green.svg)

**Evidencia:**
[Insertar captura de pantalla o resultado de la prueba aquí]