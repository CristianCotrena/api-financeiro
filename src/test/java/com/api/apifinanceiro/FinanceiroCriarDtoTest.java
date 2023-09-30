package com.api.apifinanceiro;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import com.api.apifinanceiro.models.enums.CargosEnum;
import java.lang.reflect.Field;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class FinanceiroCriarDtoTest {

  @Test
  @DisplayName("1 - Teste de criação de FinanceiroDto")
  void testDtoClasses() {
    Map<String, Map<String, String>> expectedMap = Map.of(
        "com.api.apifinanceiro.dto.FinanceiroDto", Map.of(
            "id", String.class.getName()
        ),
        "com.api.apifinanceiro.dto.FinanceiroCriarDto", Map.of(
            "idFuncionario", UUID.class.getName(),
            "cargo", CargosEnum.class.getName(),
            "dataAdmissao", ZonedDateTime.class.getName(),
            "salario", Double.class.getName(),
            "clt", String.class.getName(),
            "matricula", String.class.getName(),
            "status", Integer.class.getName()
        )
    );

    try {
      for (String className : expectedMap.keySet()) {
        Class<?> classToTest = Class.forName(className);
        assertEquals(
            expectedMap.get(className),
            Arrays.stream(classToTest.getDeclaredFields())
                .filter(field -> !field.getName().equals("serialVersionUID")) // Filtra o campo serialVersionUID
                .collect(Collectors.toMap(Field::getName, (f) -> f.getType().getName())),
            "Os atributos precisam estar definidos e com os tipos corretos para a classe " + className
        );
      }
    } catch (ClassNotFoundException e) {
      fail("As classes de DTO precisam existir: " + e.getMessage());
    }
  }
}
