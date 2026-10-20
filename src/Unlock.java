public class Unlock extends CodeHandler {
  private String unlockCode;

  public Unlock(String code, CodeHandler nextHandler) {
    super(nextHandler);
    this.unlockCode = code;
  }

  @Override
  public void handleCode(String code, Door door) {
    if (door.isLocked()) {
      if (unlockCode != null && unlockCode.equals(code)) {
        door.resetState();
      }
    } else {
      super.handleCode(code, door);
    }
  }
}