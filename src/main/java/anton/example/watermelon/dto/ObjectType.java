package anton.example.watermelon.dto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ObjectType {
    TEST("Тест"),
    SAMPLE("Проба"),
    REQUEST("Заявка");

    private final String meaning;

    @Override
    public String toString() {
        return meaning;
    }
}
