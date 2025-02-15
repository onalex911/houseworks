package FactoryMethod;

//абстрактный класс для создания продуктов. Он нужен для того, чтобы определить "шаблон" для создания продуктов
//почему это не интерфейс - 
public abstract class NationFactory {
    public abstract Nation createNation();
}
