public class Lock extends CodeHandler {
  private String lockCode;
  public Lock(CodeHandler nextHandler) {
    super(nextHandler);
  }

  @Override
  public void handleCode(String code, Door door)
  {
    if (!door.isLocked())
    {
      door.incrementNumTrials();
      if (door.getNumTrials() >= 3)
      {
        door.lock();
      }
      else {
        System.out.println("Invalid unlock code");
        super.handleCode(code, door);
      }
    }
    else
    {
      super.handleCode(code, door);
    }
  }
}