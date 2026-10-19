public class FireAlarm extends CodeHandler {
  private String codeFireAlarm;

  public FireAlarm(String code, CodeHandler nextHandler) {
    super(nextHandler);
    this.codeFireAlarm = code;
  }

  @Override
  public void handleCode(String code, Door door) {
    if (!door.isLocked()) {
      if (codeFireAlarm != null && codeFireAlarm.equals(code)) {
        door.resetState();
        door.fireAlarm(); // <--- Crida al mètode de la porta
        door.open();
      } else {
        super.handleCode(code, door);
      }
    } else {
      super.handleCode(code, door);
    }
  }
}