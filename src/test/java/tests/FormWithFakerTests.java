package tests;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class FormWithFakerTests extends TestBase {

    @Test
    void fillFormTest() {
        Faker faker = new Faker();

        String firstName = faker.name().firstName(),
                lastName = faker.name().lastName(),
                email = faker.internet().emailAddress(),
                gender = "Male",
                mobile = faker.phoneNumber().subscriberNumber(10),
                dayOfBirth = "30",
                monthOfBirth = "November",
                yearOfBirth = "1993",
                subject1 = "Arts",
                subject2 = "English",
                hobby1 = "Sports",
                hobby2 = "Reading",
                hobby3 = "Music",
                picture = "cuppy.jpg",
                currentAddress = faker.address().fullAddress(),
                state = "Uttar Pradesh",
                city = "Merrut";

        open("https://demoqa.com/automation-practice-form");

//      set main
        $("#firstName").val(firstName);
        $("#lastName").val(lastName);
        $("#userEmail").val(email);
        $("#genterWrapper").$(byText(gender)).click();
        $("#userNumber").val(mobile);

//      set date
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption(monthOfBirth);
        $(".react-datepicker__year-select").selectOption(yearOfBirth);
        $(".react-datepicker__day--0" + dayOfBirth).click();

//      set subject
        $("#subjectsInput").val(subject1);
        $(".subjects-auto-complete__menu-list").$(byText(subject1)).click();
        $("#subjectsInput").val(subject2);
        $(".subjects-auto-complete__menu-list").$(byText(subject2)).click();

//      set hobbies
        $("#hobbiesWrapper").$(byText(hobby1)).click();
        $("#hobbiesWrapper").$(byText(hobby2)).click();
        $("#hobbiesWrapper").$(byText(hobby3)).click();

//      upload file
        $("#uploadPicture").uploadFromClasspath(picture);

//      set address
        $("#currentAddress").val(currentAddress);
        $("#state").click();
        $("#stateCity-wrapper").$(byText(state)).click();
        $("#city").click();
        $("#stateCity-wrapper").$(byText(city)).click();

        $("#submit").click();
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));

//      asserts
        $(".modal-content").shouldHave(text(firstName + " " + lastName),
                text(email),
                text(gender),
                text(mobile),
                text(dayOfBirth + " " + monthOfBirth + "," + yearOfBirth),
                text(currentAddress),
                text(picture),
                text(subject1 + ", " + subject2),
                text(hobby1 + ", " + hobby2 + ", " + hobby3),
                text(state + " " + city));

    }
}
