public class Client {
  public static void main(String[] args) throws IOException {
    String openCode = "1111";
    String fireAlarmCode = "2222";
    String unlockCode = "3333";
    CodeHandler chain1 =
        new Log(new Unlock(unlockCode,
            new FireAlarm(fireAlarmCode,
                new Open(openCode,
                    new Lock(null)))));
    CodeHandler chain2 = new Log(new Open(openCode, null));
    CodeHandler chain3 =
        new Log(
            new FireAlarm(code_fire_alarm,
                new Open(code_open, null)));
    Door d1 = new Door("d1", chain1);
    d1.processCode("1111"); // opens
    d1.processCode("2222"); // opens and fires alarm
    d1.processCode("1234"); // first trial
    d1.processCode("4321"); // second trial
    d1.processCode("5555"); // third trial, gets locked
    d1.processCode("6666"); // invalid unlock code
    d1.processCode("7777"); // invalid unlock code
    d1.processCode("1111"); // invalid unlock code
    d1.processCode("3333"); // valid unlock code, now can be opened or fire alarm
    d1.processCode("2222"); // opens and fires alarm
  // change behaviour of door d1 at run time
    d1.setCodeHandler(chain2);
    BufferedReader stdin = new BufferedReader(
        new InputStreamReader(System.in));
    while (true) {
      System.out.print("Input code : ");
      String inputCode = stdin.readLine();
      d1.processCode(inputCode);
    }
  }
}