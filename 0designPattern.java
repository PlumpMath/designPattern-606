package headfirst.proxy.virtualproxy
//都是鸭子
//interface
public interface Quackable{
  public void quack();
}

//具体实现of Quackable

public class MallarDuck implements Quackable {
  public void quack(){
    System.out.println("Quack");
  }
}
public class RedheadDuck implements Quackable {
  public void quack(){
    System.out.println("Quack");
  }
}

//鸭鸣器
public class DuckCall implements Quackable {
  public void quack() {
    System.out.println("Squeak");
  }
}
//橡皮鸭
public class RubberDuck implements Quackable {
  public void quack(){
    System.out.println("Squeak");
  }
}

//模拟器
public class DuckSimulator {
  public static void main(String[] args) {
    DuckSimulator simulator = new DuckSimulator();
    simulator.simulate();
  }

  void simulate() {
    Quackable mallardDuck = new mallardDuck();
    Quackable redheadDuck = new RedheadDuck();
    Quackable duckCall  = new DuckCall();
    Quackable rubberDuck  = new RubberDuck();

    //鹅鹅鹅创建
    Quackable gooseDuck = new GooseAdapter(new Goose());

    System.out.println("\nDuck simulator");

    simulate(mallardDuck);
    simulate(redheadDuck);
    simulate(duckCall);
    simulate(rubberDuck);

    //鹅鹅鹅叫
    simulate(gooseDuck);
  }

  void simulate(Quackable duck) {
    duck.quack();
  }
}
