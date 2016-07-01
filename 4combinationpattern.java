
//组合模式允许我们想对待单个对象一样对待对象集合，还有什么模式能比组合模式创建一群
//quackable 更好的呢

public class Flock implements Quackable {
  ArrayList quackers = new ArrayList();

  public void add(Quackable quacker) {
    quackers.add(quacker);
  }

  public void quack() {
    Iterator iterator = quackers.iterator();
    while(iterator.hasNext()) {
      Quackable quacker = (Quackable) iterator.next();
      quacker.quack();
    }
  }
}


//模拟器
//更新模拟器，以便创建被装饰的鸭子
//有抽象工厂的模拟器
//采用组合模式
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
    Flock flockOfDucks = new Flock(); //创建一个flock
    flockOfDucks.add(redheadDuck);
    flockOfDucks.add(duckCall);
    flockOfDucks.add(rubberDuck);
    flockOfDucks.add(gooseDuck);
    //创建一个新的绿头鸭群
    Flock flockOfMallards = new Flock();
    Quackable mallardOne = duckFactory.createMallardDuck();
    Quackable mallardTwo = duckFactory.createMallardDuck();
    Quackable mallardThree = duckFactory.createMallardDuck();
    Quackable mallardFour duckFactory.createMallardDuck();
    flockOfMallards.add(mallardOne);
    flockOfMallards.add(mallardTwo);
    flockOfMallards.add(mallardThree);
    flockOfMallards.add(mallardFour);
    //将其加入主群；
    flockOfDucks.add(flockOfMallards);

    System.out.println("\nDuck Simulator: Whole Flock Simulation");
    simulate(flockOfDuck);  //测试整群鸭子

    System.out.println("\nDuck Simulator :Mallard flock simulation");
    simulate(flockOfMallards);

    System.out.println("\nThe ducks quacked "+ QuackCounter.getQuacks() + "times");

    //下一步鸭子观察者想要观察个别鸭子的行为，这该怎么做了现在，我想想



/*
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
*/
  void simulate(Quackable duck) {
    duck.quack();
  }
}
