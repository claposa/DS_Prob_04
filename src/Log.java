import java.time.LocalDateTime;

public class Log extends CodeHandler {

  public Log(CodeHandler nextHandler) {
    super(nextHandler);
  }

  @Override
  public void handleCode(String code, Door door) {
    // 1. Primer deixem que la cadena processi el codi (s'incrementin intents, es bloquegi, etc.)
    super.handleCode(code, door);

    // 2. Després imprimim el registre amb l'estat actualitzat de la porta
    String isLockedStr = door.isLocked() ? "is locked, " : "";
    int trialsLeft = 3 - door.getNumTrials();

    System.out.println("door " + door.getId() + " " + isLockedStr +
        "entered code " + code +
        ", trials left " + trialsLeft +
        ", at " + LocalDateTime.now());
  }
}