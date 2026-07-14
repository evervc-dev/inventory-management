# Casos de Prueba para UserMapper

Este documento detalla los casos de prueba ejecutados para la clase `UserMapper`, encargada de la conversión entre entidades `User` y sus respectivos DTOs (`UserRequestDto`, `UserResponseDto`).

---

### Caso de Prueba: Mapeo de Entidad a DTO

**Nombre:** Mapeo de Entidad a DTO  
**Código:** TC-UM-001  
**Versión:** 1.0  
**Fecha de Ejecución:** 14 de Julio del 2026  
**Descripción:** Verifica que un objeto `User` (entidad) con todos sus campos se mapea correctamente a un `UserResponseDto`.

**Ambiente de Pruebas:**
- **Precondiciones:**
  - El `UserMapper` y su dependencia `RoleMapper` deben estar inicializados correctamente.
- **Postcondiciones:**
  - No hay cambios persistentes en el sistema.

**Desarrollo de la Prueba:**
- **Datos de Entrada:**
  - Objeto `User` con todos sus atributos, incluyendo una lista de `Role`.
- **Acciones o Pasos a Seguir:**
  1. Inicializar el `UserMapper` (realizado en `@BeforeEach`).
  2. Crear un objeto `User` con datos completos.
  3. Invocar el método `toResponseDto()` del `userMapper` con el objeto `User`.
  4. Comparar el `UserResponseDto` resultante con un DTO esperado.

**Resultados:**
- **Resultado Esperado:** El `UserResponseDto` resultante es igual al DTO esperado, incluyendo la lista de roles mapeada.
- **Resultado Obtenido:** La prueba se ejecutó correctamente y el resultado obtenido coincide con el esperado.

**Estado:** ![Estado](https://img.shields.io/badge/PASS-Green.svg)

**Evidencia:**
<!-- Espacio para la captura de evidencia -->

---

### Caso de Prueba: Mapeo de Entidad Nula a DTO

**Nombre:** Mapeo de Entidad Nula a DTO  
**Código:** TC-UM-002  
**Versión:** 1.0  
**Fecha de Ejecución:** 14 de Julio del 2026  
**Descripción:** Verifica que el mapeo de un objeto `User` nulo a `UserResponseDto` resulta en un valor nulo.

**Ambiente de Pruebas:**
- **Precondiciones:**
  - El `UserMapper` debe estar inicializado correctamente.
- **Postcondiciones:**
  - No hay cambios persistentes en el sistema.

**Desarrollo de la Prueba:**
- **Datos de Entrada:**
  - Objeto `User` con valor `null`.
- **Acciones o Pasos a Seguir:**
  1. Invocar el método `toResponseDto()` del `userMapper` con un valor `null`.
  2. Afirmar que el resultado es `null`.

**Resultados:**
- **Resultado Esperado:** El resultado del mapeo es `null`.
- **Resultado Obtenido:** La prueba se ejecutó correctamente y el resultado obtenido coincide con el esperado.

**Estado:** ![Estado](https://img.shields.io/badge/PASS-Green.svg)

**Evidencia:**
<!-- Espacio para la captura de evidencia -->

---

### Caso de Prueba: Mapeo de Entidad con Campos Opcionales Nulos a DTO

**Nombre:** Mapeo de Entidad con Campos Opcionales Nulos a DTO  
**Código:** TC-UM-003  
**Versión:** 1.0  
**Fecha de Ejecución:** 14 de Julio del 2026  
**Descripción:** Verifica que un `User` con campos opcionales (`address`, `birthDate`, `roles`) nulos se mapea correctamente a un `UserResponseDto`.

**Ambiente de Pruebas:**
- **Precondiciones:**
  - El `UserMapper` debe estar inicializado correctamente.
- **Postcondiciones:**
  - No hay cambios persistentes en el sistema.

**Desarrollo de la Prueba:**
- **Datos de Entrada:**
  - Objeto `User` con `address`, `birthDate` y `roles` nulos.
- **Acciones o Pasos a Seguir:**
  1. Crear un objeto `User` con los campos mencionados como nulos.
  2. Invocar el método `toResponseDto()` del `userMapper`.
  3. Comparar el `UserResponseDto` resultante con un DTO esperado que también tenga esos campos nulos.

**Resultados:**
- **Resultado Esperado:** El `UserResponseDto` resultante tiene los campos `address`, `birthDate` y `roles` como nulos.
- **Resultado Obtenido:** La prueba se ejecutó correctamente y el resultado obtenido coincide con el esperado.

**Estado:** ![Estado](https://img.shields.io/badge/PASS-Green.svg)

**Evidencia:**
<!-- Espacio para la captura de evidencia -->

---

### Caso de Prueba: Mapeo de Lista de Entidades a Lista de DTOs

**Nombre:** Mapeo de Lista de Entidades a Lista de DTOs  
**Código:** TC-UM-004  
**Versión:** 1.0  
**Fecha de Ejecución:** 14 de Julio del 2026  
**Descripción:** Verifica que una lista de objetos `User` se mapea correctamente a una lista de `UserResponseDto`.

**Ambiente de Pruebas:**
- **Precondiciones:**
  - El `UserMapper` debe estar inicializado correctamente.
- **Postcondiciones:**
  - No hay cambios persistentes en el sistema.

**Desarrollo de la Prueba:**
- **Datos de Entrada:**
  - `List<User>` con dos entidades `User` válidas.
- **Acciones o Pasos a Seguir:**
  1. Crear una lista de `User`.
  2. Invocar el método `toResponseDtoList()` del `userMapper`.
  3. Comparar la lista de `UserResponseDto` resultante con una lista esperada.

**Resultados:**
- **Resultado Esperado:** La lista de `UserResponseDto` resultante es igual a la lista esperada.
- **Resultado Obtenido:** La prueba se ejecutó correctamente y el resultado obtenido coincide con el esperado.

**Estado:** ![Estado](https://img.shields.io/badge/PASS-Green.svg)

**Evidencia:**
<!-- Espacio para la captura de evidencia -->

---

### Caso de Prueba: Mapeo de Lista de Entidades Nula a Lista de DTOs

**Nombre:** Mapeo de Lista de Entidades Nula a Lista de DTOs  
**Código:** TC-UM-005  
**Versión:** 1.0  
**Fecha de Ejecución:** 14 de Julio del 2026  
**Descripción:** Verifica que el mapeo de una lista de `User` nula resulta en un valor nulo.

**Ambiente de Pruebas:**
- **Precondiciones:**
  - El `UserMapper` debe estar inicializado correctamente.
- **Postcondiciones:**
  - No hay cambios persistentes en el sistema.

**Desarrollo de la Prueba:**
- **Datos de Entrada:**
  - `List<User>` con valor `null`.
- **Acciones o Pasos a Seguir:**
  1. Invocar el método `toResponseDtoList()` del `userMapper` con `null`.
  2. Afirmar que el resultado es `null`.

**Resultados:**
- **Resultado Esperado:** El resultado del mapeo es `null`.
- **Resultado Obtenido:** La prueba se ejecutó correctamente y el resultado obtenido coincide con el esperado.

**Estado:** ![Estado](https://img.shields.io/badge/PASS-Green.svg)

**Evidencia:**
<!-- Espacio para la captura de evidencia -->

---

### Caso de Prueba: Mapeo de Lista de Entidades con Elementos Nulos

**Nombre:** Mapeo de Lista de Entidades con Elementos Nulos  
**Código:** TC-UM-006  
**Versión:** 1.0  
**Fecha de Ejecución:** 14 de Julio del 2026  
**Descripción:** Verifica que una lista de `User` que contiene elementos nulos se mapea a una lista de `UserResponseDto` con los elementos nulos correspondientes.

**Ambiente de Pruebas:**
- **Precondiciones:**
  - El `UserMapper` debe estar inicializado correctamente.
- **Postcondiciones:**
  - No hay cambios persistentes en el sistema.

**Desarrollo de la Prueba:**
- **Datos de Entrada:**
  - `List<User>` con un objeto `User` válido y un elemento `null`.
- **Acciones o Pasos a Seguir:**
  1. Crear una lista de `User` con un elemento válido y otro nulo.
  2. Invocar el método `toResponseDtoList()` del `userMapper`.
  3. Comparar la lista resultante con una lista esperada que contenga un DTO y un `null`.

**Resultados:**
- **Resultado Esperado:** La lista resultante contiene un `UserResponseDto` y un elemento `null`.
- **Resultado Obtenido:** La prueba se ejecutó correctamente y el resultado obtenido coincide con el esperado.

**Estado:** ![Estado](https://img.shields.io/badge/PASS-Green.svg)

**Evidencia:**
<!-- Espacio para la captura de evidencia -->

---

### Caso de Prueba: Mapeo de DTO de Creación a Entidad

**Nombre:** Mapeo de DTO de Creación a Entidad  
**Código:** TC-UM-007  
**Versión:** 1.0  
**Fecha de Ejecución:** 14 de Julio del 2026  
**Descripción:** Verifica que un `UserRequestDto` se mapea correctamente a una entidad `User`, ignorando `id` y `roles`, y estableciendo `enabled` a `true`.

**Ambiente de Pruebas:**
- **Precondiciones:**
  - El `UserMapper` debe estar inicializado correctamente.
- **Postcondiciones:**
  - No hay cambios persistentes en el sistema.

**Desarrollo de la Prueba:**
- **Datos de Entrada:**
  - `UserRequestDto` con todos sus campos.
- **Acciones o Pasos a Seguir:**
  1. Crear un `UserRequestDto`.
  2. Invocar el método `toEntityFromCreate()` del `userMapper`.
  3. Verificar que los campos del `User` resultante coinciden con los del DTO, `id` y `roles` son nulos, y `enabled` es `true`.

**Resultados:**
- **Resultado Esperado:** El `User` resultante tiene los datos del DTO, con `id` y `roles` nulos, y `enabled` como `true`.
- **Resultado Obtenido:** La prueba se ejecutó correctamente y el resultado obtenido coincide con el esperado.

**Estado:** ![Estado](https://img.shields.io/badge/PASS-Green.svg)

**Evidencia:**
<!-- Espacio para la captura de evidencia -->

---

### Caso de Prueba: Mapeo de DTO de Creación Nulo a Entidad

**Nombre:** Mapeo de DTO de Creación Nulo a Entidad  
**Código:** TC-UM-008  
**Versión:** 1.0  
**Fecha de Ejecución:** 14 de Julio del 2026  
**Descripción:** Verifica que el mapeo de un `UserRequestDto` nulo a una entidad `User` resulta en un valor nulo.

**Ambiente de Pruebas:**
- **Precondiciones:**
  - El `UserMapper` debe estar inicializado correctamente.
- **Postcondiciones:**
  - No hay cambios persistentes en el sistema.

**Desarrollo de la Prueba:**
- **Datos de Entrada:**
  - `UserRequestDto` con valor `null`.
- **Acciones o Pasos a Seguir:**
  1. Invocar el método `toEntityFromCreate()` del `userMapper` con `null`.
  2. Afirmar que el resultado es `null`.

**Resultados:**
- **Resultado Esperado:** El resultado del mapeo es `null`.
- **Resultado Obtenido:** La prueba se ejecutó correctamente y el resultado obtenido coincide con el esperado.

**Estado:** ![Estado](https://img.shields.io/badge/PASS-Green.svg)

**Evidencia:**
<!-- Espacio para la captura de evidencia -->

---

### Caso de Prueba: Mapeo de DTO de Creación con Campos Nulos a Entidad

**Nombre:** Mapeo de DTO de Creación con Campos Nulos a Entidad  
**Código:** TC-UM-009  
**Versión:** 1.0  
**Fecha de Ejecución:** 14 de Julio del 2026  
**Descripción:** Verifica que un `UserRequestDto` con campos opcionales nulos (`address`, `birthDate`) se mapea correctamente a una entidad `User`.

**Ambiente de Pruebas:**
- **Precondiciones:**
  - El `UserMapper` debe estar inicializado correctamente.
- **Postcondiciones:**
  - No hay cambios persistentes en el sistema.

**Desarrollo de la Prueba:**
- **Datos de Entrada:**
  - `UserRequestDto` con `address` y `birthDate` nulos.
- **Acciones o Pasos a Seguir:**
  1. Crear un `UserRequestDto` con campos nulos.
  2. Invocar el método `toEntityFromCreate()` del `userMapper`.
  3. Verificar que los campos `address` y `birthDate` en el `User` resultante son nulos.

**Resultados:**
- **Resultado Esperado:** El `User` resultante tiene los campos `address` y `birthDate` nulos.
- **Resultado Obtenido:** La prueba se ejecutó correctamente y el resultado obtenido coincide con el esperado.

**Estado:** ![Estado](https://img.shields.io/badge/PASS-Green.svg)

**Evidencia:**
<!-- Espacio para la captura de evidencia -->