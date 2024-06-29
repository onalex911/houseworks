//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        String lang = "ru";
        String word = "mother";
        String tranlation;
        //ru, en
        // слово

        switch (lang){
            case "ru":
                switch(word){
                    case "mother" -> tranlation = "мама";
                    case "father" -> tranlation = "папа";
                    case "sister" -> tranlation = "сестра";
                    case "brother" -> tranlation = "брат";
                    case "dog" -> tranlation = "собака";
                    default: tranlation = "Нет такого слова в английском словаре";
                }
                break;
            case "en":
                switch(word){
                    case "мама" -> tranlation = "mother";
                    case "папа" -> tranlation = "father";
                    case "сестра" -> tranlation = "sister";
                    case "брат" -> tranlation = "brother";
                    case "собака" -> tranlation = "dog";
                    default: tranlation = "Нет такого слова в русском словаре";
                }
                break;
            default: System.out.println("Neizvestnyj yazyk");

        }
    }
}