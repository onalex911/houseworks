package hw2406;
//        3)Создайте класс UserInputHandler с методом validateInput(String input), который выбрасывает InvalidUserInputException,
//        если ввод не удовлетворяет определенным условиям (например, пустая строка или неверный формат).
public class UserInputHandler {

    public void validateInput(String input) throws InvalidUserInputException {
        String msg = null;
        if(input.trim().equals(""))
            msg = "введено пустое значение";
        else if(!input.matches("^[a-zA-Z0-9._-]+@[a-zA-Z0-9._-]+$"))
            msg = "введенное значение не соответствует формату адреса электронной почты";

        if(msg != null)
            throw new InvalidUserInputException("ОШИБКА ввода логина",msg);
    }
}
