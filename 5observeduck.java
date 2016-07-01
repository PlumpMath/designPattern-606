

//首先需要一个鸭子接口
public interface QuackObservable {
  public void registerObserver(Observer observer);
  public void notifyObservers();
}

//重写第一个的鸭子接口继承Obsevable接口
public interface Quackable extends QuackObservable{
  public void quack();
}

//现在我们必须确定所有实现Quackable的具体类都能够扮演QuackObservable的角色。
public class Observable implements QuackObservable {
  ArrayList observers = new ArrayList();
  QuackObservable duck;

  public Observable(QuackObservable duck) {
    this.duck = duck;
  }

  public void registerObserver(Observer observer) {
    observers.add(observer);
  }
  public void notifyObservers() {
    Iterator iterator = observers.iterator();
    while(iterator.hasNext()) {
      Observer observer = (Observer) iterator.next();
      observer.update(duck);
    }
  }
}

//整合Observable 和Quackable类；
//MallardDuck 实现
public class MallardDuck implements Quackable {
  Observable observable;

  public MallarDuck() {
    observable = new Observable(this);
  }

  public void quack() {
    System.out.println("Quack");
    notifyObservers();
  }
  public void registerObserver(Observer observer) {
    observable.registerObserver(observer);
  }

  public void notifyObservers() {
    observable.notifyObservers();
  }
}
//QucakConter装饰者怎么写？？？

public class QuackCounter implements Quackable {
  Quackable duck;
  static int numberOfQuacks;
  //观察者用的
  //Observalbe observable;

  public QuackCounter (Quackable duck) {
    this.duck  = duck;
    //observable = new Observable();
  }

  public void quack() {
    duck.quack();//委托给正在装饰的Quackable对象
    numberOfQuacks++;//叫声次数加1
  }
  public static int getQuacks() {
    return numberOfQuacks;
  }

  public void registerObserver(Observer observer) {
    duck.registerObserver(observer);
  }
  public void notifyObservers() {
    duck.notifyObservers();
  }
}
//观察整个群是怎样的呢
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
  public void registerObserver(Observer observer){
    Iterator iterator = quackers.iterator();
    while(iterator.hasNext()) {
      Quackable quacker = (Quackable) iterator.next();
      quacker.registerObserver(observer);
    }
  }
  public void notifyObservers() { //不需要一个个通知，自己就通知了？？
    //Iterator iterator = quackers.iterator();
  //while(iterator.hasNext()) {
  //    Quackable quacker = (Quackable) iterator.next();
  //    quacker.notifyObservers();
  //  }
  }
}




//现在开始Obsrever接口
public interface Observer {
  public void update(QuackObservable duck);
}
//实现一个观察者
public class Quackologist implements Observer {
  public void update(QuackObservable duck) {
    System.out.println("Quackologist:" + duck + "just quacked");
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

    System.out.println("\nDuck Simulator:With Observer");
    Quackologist quacklogist = new Quackologist();
    simulate(flockOfDuck);

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
