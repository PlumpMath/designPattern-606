
//工厂生产鸭子

public abstract class AbstractDuckFactory {
  public abstract Quackable createMallardDuck();
  public abstract Quackable createRedheadDuck();
  public abstract Quackable createDuckCall();
  public abstract Quackable createRubberDuck();
}
//鹅鹅鹅工厂
public abstract class AbstractGooseFactory {
  public abstract Qucakabke createGooseDuck();
}
public class GooseFactory extends AbstractGooseFactory {
  public Quackable createGooseDuck() {
    return new GooseAdapter(new Goose());
  }
}

//创建一个工厂，此工厂创建没有装饰者的鸭子：

public class DuckFactory extends AbstractDuckFactory {
  public Quackable createMallardDuck() {
    return new MallarDuck();
  }
  public Quackable createRedheadDuck() {
    return new RedheadDuck();
  }
  public Quackable createDuckCall() {
    return new DuckCall();
  }
  public Quackable createRubberDuck()  {
    return new RubberDuck();
  }
}
//创建真正的工厂
public class CountingDuckFactory extends AbstractDuckFactory {
  public Quackable createMallardDuck() {
    return new QuackCounter(new MallarDuck());
  }

  public Quackable createRedheadDuck() {
    return new QuackCounter(new RedheadDuck());
  }
  public Quackable createDuckCall() {
    return new QuackCounter(new DuckCall());
  }
  public Quackable createRubberDuck() {
    return new QuackCounter(new RubberDuck());
  }
}

//模拟器
//更新模拟器，以便创建被装饰的鸭子
//有抽象工厂的模拟器
public class DuckSimulator {
  public static void main(String[] args) {
    DuckSimulator simulator = new DuckSimulator();

    //newline创建抽象工厂，准备把它传入simulate()方法；
    AbstractDuckFactory duckFactory = new CountingDuckFactory();


    //传入一个参数，利用它来创建鸭子；而不是直接实例化鸭子；
    simulator.simulate(duckFactory);
  }

  void simulate(AbstractDuckFactory duckFactory) {
    //Quackable mallardDuck = new QuackCounter(new MallarDuck());
    //Quackable redheadDuck = new QuackCounter(new RedheadDuck());
    //Quackable duckCall  = new QuackCounter(new DuckCall());
    //Quackable rubberDuck  = new QuackCounter(new Goose());
    //下一步将创建和装饰鸭子的部分包装起来，利用工厂模式才行
    Quackable mallardDuck = duckFactory.createMallardDuck();
    Quackable redheadDuck = duckFactory.createRedheadDuck();
    Quackable duckCall = duckFactory.createDuckCall();
    Quackable rubberDuck = duckFactory.createRubberDuck();
    //为什么要个别管理鸭子呢，这里需要改进，采用组合模式？？？？怎么采用




    //鹅鹅鹅创建
    Quackable gooseDuck = new GooseAdapter(new Goose());

    System.out.println("\nDuck simulator");

    simulate(mallardDuck);
    simulate(redheadDuck);
    simulate(duckCall);
    simulate(rubberDuck);

    //鹅鹅鹅叫
    simulate(gooseDuck);

    System.out.println("The ducks quacked " + QuackCounter.getQuacks() + " times");
  }

  void simulate(Quackable duck) {
    duck.quack();
  }
}
