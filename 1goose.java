public class Goose {
  public void honk(){
    System.out.println("Honk");
  }
}

//鹅鹅鹅适配器
public class GooseAdapter implements Quackable {
  Goose goose;

  public GooseAdapter(Goose goose) {
    this.goose = goose;
  }
  public void quack() {
    goose.honk();
  }
}
//master branch has changed
