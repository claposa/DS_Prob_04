public class Lock extends CodeHandler {

  public Lock(CodeHandler nextHandler) {
    super(nextHandler);
  }

  @Override
  public void handleCode(String code, Door door) {
    if (!door.isLocked()) {
      if (door.getNumTrials() >= 3) {
        door.lock();
      } else {
        super.handleCode(code, door);
      }
    } else {
      super.handleCode(code, door);
    }
  }
}