//统计呱呱叫的次数
//装饰者模式

public class QuackCounter implements Quackable {
  Quackable duck;
  static int numberOfQuacks;

  public QuackCounter (Quackable duck) {
    this.duck  = duck;
  }

  public void quack() {
    duck.quack();//委托给正在装饰的Quackable对象
    numberOfQuacks++;//叫声次数加1
  }
  public static int getQuacks() {
    return numberOfQuacks;
  }
}

//模拟器
//更新模拟器，以便创建被装饰的鸭子
public class DuckSimulator {
  public static void main(String[] args) {
    DuckSimulator simulator = new DuckSimulator();
    simulator.simulate();
  }

  void simulate() {
    Quackable mallardDuck = new QuackCounter(new MallarDuck());
    Quackable redheadDuck = new QuackCounter(new RedheadDuck());
    Quackable duckCall  = new QuackCounter(new DuckCall());
    Quackable rubberDuck  = new QuackCounter(new Goose());
    //下一步将创建和装饰鸭子的部分包装起来，利用工厂模式才行

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
