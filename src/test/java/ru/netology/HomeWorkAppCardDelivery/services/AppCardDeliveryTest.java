package ru.netology.HomeWorkAppCardDelivery.services;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

//import java.util.concurrent.locks.Condition;

import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Selenide.*;
//import static com.codeborne.selenide.Selenide.open;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class AppCardDeliveryTest {
    private String generateDate(long addDays, String pattern) {
        return LocalDate.now().plusDays(addDays).format(DateTimeFormatter.ofPattern(pattern));
    }

    @Test
    public void successFillingForm() {
        open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("Смоленск");
        String planningDate = generateDate(10, "dd.MM.yyyy");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME),Keys.DELETE);
        $("[data-test-id='date'] input").setValue(planningDate);
        $("[data-test-id='name'] input").setValue("Сергей Сергеевич Милош-Барош");
        $("[data-test-id='phone'] input").setValue("+79500000000");
        $("[data-test-id='agreement']").click();
        $("button.button").click();
        $(".notification__content")
                .shouldBe(Condition.visible, Duration.ofSeconds(15))
                .shouldHave(Condition.exactText("Встреча успешно забронирована на " +  planningDate));
    }

}