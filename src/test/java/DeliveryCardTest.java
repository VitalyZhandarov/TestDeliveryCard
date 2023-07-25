import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class DeliveryCardTest {

    @Test
    public void shouldReturnSuccessDeliveryCard () {

        LocalDate currentDate = LocalDate.now().plusDays(3);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String date = currentDate.format(formatter);

        open("http://localhost:9999");
        $("[data-test-id=city] [placeholder='Город']").setValue("Москва");
        $("[data-test-id=date] [class='input__box'] [placeholder='Дата встречи']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] [class='input__box'] [placeholder='Дата встречи']").setValue(date);
        $("[data-test-id=name] [name='name']").setValue("Жандаров Виталий");
        $("[data-test-id=phone] [type='tel']").setValue("+79292222222");
        $("[data-test-id=agreement]").click();
        $("[role=button] .button__text").click();
        $(withText("Успешно!")).shouldBe(Condition.visible, Duration.ofMillis(15000));
        $("[data-test-id=notification] .notification__content").shouldHave(Condition.exactText("Встреча успешно забронирована на " + date));


    }

}
